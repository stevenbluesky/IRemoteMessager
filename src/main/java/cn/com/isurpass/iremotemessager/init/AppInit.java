package cn.com.isurpass.iremotemessager.init;

import cn.com.isurpass.iremotemessager.jms.JMSUtil;
import cn.com.isurpass.iremotemessager.sender.PushMessage;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.jms.Session;

@Component
public class AppInit implements ApplicationRunner, Runnable {
    private static Thread thread;

	@Override
	public void run() {
		PushMessage.initPushClient();
		JMSUtil.init();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if ( thread == null ){
			thread = new Thread(this);
			thread.start();
		}
	}

}
