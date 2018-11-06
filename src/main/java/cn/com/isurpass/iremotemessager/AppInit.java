package cn.com.isurpass.iremotemessager;

import java.util.Calendar;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.com.isurpass.iremotemessager.framework.EventProcessor;
import cn.com.isurpass.iremotemessager.sender.PushMessage;
import cn.com.isurpass.iremotemessager.vo.EventData;

@Component
public class AppInit implements /*ApplicationRunner,*/ Runnable
{
	private static Thread thread;
	
	@Override
	public void run()
	{
		PushMessage.initPushClient(0, "gmoZmRV0fBsazwNQW9mKWax8LBnbgF7QPP9pDHkHpSg=", "meLVvQ27XPIXH9uxxI4Q6kRPNxJySMzdfiXmhD3o5zc=");
		EventData data = new EventData();
		data.setPlatform(9);
		data.setEventtype("devicestatus");
		data.setEventparameters(new JSONObject());
		data.getEventparameters().put("zwavedeviceid", 15951);
		data.getEventparameters().put("deviceid", "iRemote8005000000006");
		data.getEventparameters().put("reporttime", Calendar.getInstance().getTimeInMillis());
		data.getEventparameters().put("operator", "18071272118");
		
		EventProcessor ep = SpringUtil.getBean(EventProcessor.class);
		ep.setEventdata(data);
		ep.run();
	}

//	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		if ( thread == null )
		{
			thread = new Thread(this);
			thread.start();
		}
	}

}
