package cn.com.isurpass.iremotemessager.framework;

import java.util.List;

import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.SendResult;

public interface IMessageSender<T> 
{
	List<SendResult> send(EventData data,T targetdata);
}
