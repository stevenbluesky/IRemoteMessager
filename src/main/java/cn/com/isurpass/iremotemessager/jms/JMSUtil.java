package cn.com.isurpass.iremotemessager.jms;

import cn.com.isurpass.iremotemessager.SpringUtil;
import cn.com.isurpass.iremotemessager.framework.EventProcessor;
import cn.com.isurpass.iremotemessager.service.MsgEventTypeService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnection;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import java.util.Collection;

public class JMSUtil {

	private static Log log = LogFactory.getLog(JMSUtil.class);
	private static PooledConnection conn;
	private static PooledConnectionFactory poolFactory;
    private static final TextMessageBaseListener textMessageBaseListener = new TextMessageBaseListener(EventProcessor.class);
    private static final String BROKER_URL = "failover://tcp://192.168.5.144:61616";
    private static final String ACTIVEMQ_USER_NAME = "jwzh908";
    private static final String ACTIVEMQ_PASSWORD = "jwzh@321";

    public static void init() {
        if (log.isInfoEnabled()) {
            log.info("init JMS...");
        }

        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_USER_NAME,
                    ACTIVEMQ_PASSWORD, BROKER_URL);
             poolFactory = new PooledConnectionFactory(connectionFactory);
            conn = (PooledConnection) poolFactory.createConnection();

            conn.start();

            regist(SpringUtil.getBean(MsgEventTypeService.class).findAllEventCode());

        } catch (JMSException e) {
            log.error(e.getMessage(), e);
            System.exit(1);
        }
    }

    public static void regist(Collection<String> destList){
        Session session = null;
        try {
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            for (String dest : destList) {
                regist(dest, session);
            }
        } catch (JMSException e) {
            log.warn(e.getMessage(), e);
        }finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    }

    private static void regist(String dest, Session session) throws JMSException {
            Destination topic = session.createTopic(dest);
            MessageConsumer consumer  = session.createConsumer(topic);
            consumer.setMessageListener(textMessageBaseListener);
    }

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
}
