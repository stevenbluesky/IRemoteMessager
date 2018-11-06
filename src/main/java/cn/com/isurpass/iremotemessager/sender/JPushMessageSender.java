package cn.com.isurpass.iremotemessager.sender;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.com.isurpass.iremotemessager.framework.IMessageSender;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.JPushMessageData;
import cn.com.isurpass.iremotemessager.vo.SendResult;

@Component("cn.com.isurpass.iremotemessager.sender.JPushMessageSender")
public class JPushMessageSender implements IMessageSender<JPushMessageData> 
{

	@Override
	public List<SendResult> send(EventData data, JPushMessageData targetdata)
	{
		PushMessage.push(targetdata.getPayload(), data.getPlatform());
		return null;
	}

}
