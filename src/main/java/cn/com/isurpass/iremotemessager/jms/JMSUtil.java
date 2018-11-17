package cn.com.isurpass.iremotemessager.jms;

import cn.com.isurpass.iremotemessager.framework.EventProcessor;
import com.alibaba.fastjson.JSON;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnection;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

public class JMSUtil {

	private static Log log = LogFactory.getLog(JMSUtil.class);
	private static PooledConnection conn;
	private static PooledConnectionFactory poolFactory;
	private static ThreadLocal<List<String[]>> messageholde = new ThreadLocal<List<String[]>>();
    
    /*public static void init()
    {  
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
        
        try {  
        	poolFactory = new PooledConnectionFactory(connectionFactory);  
        	
            conn = (PooledConnection) poolFactory.createConnection();  
            conn.start();  
             
        } catch (JMSException e) {
           log.error(e.getMessage() , e);
           System.exit(1);
        }  
    }  */
    
    public static void close()
    {
    	try {
    		conn.close();
			conn.stop();
			poolFactory.clear();
			poolFactory.stop();
//			JSMTaskManager.shutdown();
		} catch (JMSException e) {
			log.error(e.getMessage(), e);
		}
    }
    
    public static void sendmessage(String dest , Object obj)
    {
    	sendmessage(dest , JSON.toJSONString(obj));
    }
    
	public static void sendmessage(String dest  , String message)
	{
		if ( StringUtils.isBlank(dest) || StringUtils.isBlank(message))
		{
		    log.error(dest);
		    log.error(message);
			log.error("invalid parameter");
			return;
		}
		List<String[]> lst = messageholde.get();
		if ( lst == null )
		{
			 lst = new ArrayList<String[]>();
			 messageholde.set(lst);
		}
		lst.add(new String[]{dest , message});
	}
	
	public static void commitmessage()
	{
		List<String[]> lst = messageholde.get(); 
		if ( lst == null || lst.size() == 0 )
			return ;
		
		try {
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			for ( String[] sa : lst)
			{
				if ( sa == null || sa.length != 2 || StringUtils.isBlank(sa[0]) || StringUtils.isBlank(sa[1]))
					continue;
				Destination destination = session.createTopic(sa[0]);
				MessageProducer producer = session.createProducer(destination);
				
				TextMessage tm = session.createTextMessage(sa[1]);
				producer.send(tm);
			}
            
            session.close();
		} catch (JMSException e) {
			 log.error(e.getMessage(), e);
		}  
		lst.clear();
	}
	
	public static void registMessageCosumer(String dest , MessageListener messagelistener)
	{
		try {			
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createTopic(dest);
			
			MessageConsumer consumer = session.createConsumer(destination);
			
			consumer.setMessageListener(messagelistener);
			
			//session.close();    
		} catch (JMSException e) {
			 log.error(e.getMessage(), e);
		}  
	}
	
	public static void registMessageCosumer(String dest , Class<EventProcessor> runner)
	{
		TextMessageBaseListener l = new TextMessageBaseListener(runner);
		registMessageCosumer( dest , l);
	}
	
	public static <T> T parseObject(Message message , Class<T> clazz)
	{
		if ( !( message instanceof TextMessage) )
			return null;
		try
		{
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			
			return JSON.parseObject(text, clazz);
		}
		catch(Throwable t)
		{
			log.error(t.getMessage(), t);
		}
		return null ;
	}
}
