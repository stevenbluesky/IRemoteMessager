package cn.com.isurpass.iremotemessager.sender;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.com.isurpass.iremotemessager.framework.IMessageSender;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.JPushNotificationData;
import cn.com.isurpass.iremotemessager.vo.SendResult;

@Component
public class JPushNotificationSender implements IMessageSender<JPushNotificationData> 
{

	@Override
	public List<SendResult> send(EventData data, JPushNotificationData targetdata)
	{
		PushMessage.push(targetdata.getPayload(), data.getPlatform());
		return null;
	}

}
