package cn.com.isurpass.iremotemessager.jms;

import cn.com.isurpass.iremotemessager.SpringUtil;
import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
import cn.com.isurpass.iremotemessager.framework.EventProcessor;
import cn.com.isurpass.iremotemessager.service.MsgEventTypeService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnection;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.*;
import java.util.Collection;
import java.util.Iterator;

public class JMSUtil {

    private static Log log = LogFactory.getLog(JMSUtil.class);
    private static final TextMessageBaseListener textMessageBaseListener = new TextMessageBaseListener(EventProcessor.class);
    private static final String BROKER_URL = "failover://tcp://192.168.5.145:61616";
    private static final String ACTIVEMQ_USER_NAME = "jwzh";
    private static final String ACTIVEMQ_PASSWORD = "JWZH1109testsvr";
    private static PooledConnection conn;
    private static PooledConnectionFactory poolFactory;
    private static Session session;

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
