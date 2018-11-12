package cn.com.isurpass.iremotemessager.init;

import cn.com.isurpass.iremotemessager.framework.EventProcessor;
import cn.com.isurpass.iremotemessager.jms.TextMessageBaseListener;
import cn.com.isurpass.iremotemessager.sender.PushMessage;
import cn.com.isurpass.iremotemessager.service.MsgEventTypeService;
import cn.com.isurpass.iremotemessager.targetdecision.OwnerTargetDecision;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;

@Component
public class AppInit implements ApplicationRunner, Runnable
{
	private static Thread thread;
	private static Log log = LogFactory.getLog(OwnerTargetDecision.class);

	private static final String BROKER_URL = "failover://tcp://192.168.5.144:61616";

	@Resource
	private MsgEventTypeService msgEventTypeService;

	@Override
	public void run()
	{
		initJPush();
		initJMS();
	}

	private void initJPush() {
		log.info("init JPush...");

		PushMessage.initPushClient(0, "gmoZmRV0fBsazwNQW9mKWax8LBnbgF7QPP9pDHkHpSg=", "meLVvQ27XPIXH9uxxI4Q6kRPNxJySMzdfiXmhD3o5zc=");
	}

	private void initJMS() {
		log.info("init JMS...");
		TextMessageBaseListener textMessageBaseListener = new TextMessageBaseListener(EventProcessor.class);

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
		try {
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			for (String s : msgEventTypeService.findAllEventCode()) {
				Destination topic = session.createTopic(s);

				MessageConsumer consumer = session.createConsumer(topic);
				consumer.setMessageListener(textMessageBaseListener);

			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		if ( thread == null )
		{
			thread = new Thread(this);
			thread.start();
		}
	}

}
