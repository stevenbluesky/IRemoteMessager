package cn.com.isurpass.iremotemessager.jms;

import cn.com.isurpass.iremotemessager.SpringUtil;
import cn.com.isurpass.iremotemessager.common.util.AES;
import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
import cn.com.isurpass.iremotemessager.common.util.Parameter;
import cn.com.isurpass.iremotemessager.framework.EventProcessor;
import cn.com.isurpass.iremotemessager.service.MsgEventTypeService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnection;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.*;
import java.util.Collection;
import java.util.Iterator;

public class JMSUtil {

    private static Log log = LogFactory.getLog(JMSUtil.class);
    private static final TextMessageBaseListener textMessageBaseListener = new TextMessageBaseListener(EventProcessor.class);
    private static String brokerUrl;
    private static String username;
    private static String password;
    private static PooledConnection conn;
    private static PooledConnectionFactory poolFactory;
    private static Session session;

    public static void init() {
        if (log.isInfoEnabled()) {
            log.info("init JMS...");
        }
        try {
            initParameter();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username,
                    password, brokerUrl);
            poolFactory = new PooledConnectionFactory(connectionFactory);
            conn = (PooledConnection) poolFactory.createConnection();

            conn.start();

            regist(SpringUtil.getBean(MsgEventTypeService.class).findAllEventCode());

        } catch (JMSException e) {
            log.error(e.getMessage(), e);
            System.exit(1);
        }
    }

    private static void initParameter() throws Exception{
        Parameter bean = SpringUtil.getBean(Parameter.class);
        if (IRemoteUtils.isAllNotBlank(bean.brokerUrl, bean.username, bean.password)) {
            brokerUrl = bean.brokerUrl;
            username = bean.username;
            password = AES.decrypt2Str(bean.password);
        } else {
            throw new Exception("please configure ActiveMQ's parameters in application.properties");
        }
    }

    /**
     * @param destList event code list
     */
    public static void regist(Collection<String> destList){
        try {
            if (session == null) {
                session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            }

            Iterator<String> iterator = destList.iterator();
            while (iterator.hasNext()) {
                String dest = iterator.next();

                Destination topic = session.createTopic(dest);
                MessageConsumer consumer  = session.createConsumer(topic);
                consumer.setMessageListener(textMessageBaseListener);
            }
        } catch (JMSException e) {
            try {
                session.close();
            } catch (JMSException e1) {
                log.warn(e.getMessage(), e);
            }
            log.warn(e.getMessage(), e);
        }
    }

    public static void commitMessage(String content, String topic){
        try {
            if (session == null) {
                session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            }
            Destination destination = session.createTopic(topic);
            MessageProducer producer = session.createProducer(destination);

            TextMessage textMessage = session.createTextMessage(content);
            producer.send(textMessage);
        } catch (JMSException e) {
            log.warn(e.getMessage(), e);
        }
    }

    public static void close(){
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
}
