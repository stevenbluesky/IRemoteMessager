package cn.com.isurpass.iremotemessager.sender;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.com.isurpass.iremotemessager.framework.IMessageSender;
import cn.com.isurpass.iremotemessager.messageparser.JPushNotificationParser;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.JPushNotificationData;
import cn.com.isurpass.iremotemessager.vo.SendResult;

@Component("cn.com.isurpass.iremotemessager.sender.JPushNotificationSender")
public class JPushNotificationSender implements IMessageSender<JPushNotificationData> 
{

	@Override
	public List<SendResult> send(EventData data, JPushNotificationData targetdata)
	{
		PushMessage.push(targetdata.getPayload(), data.getPlatform());
		return null;
	}

	@Override
	public String getMessageParserClassName()
	{
		return JPushNotificationParser.class.getName();
	}

}
