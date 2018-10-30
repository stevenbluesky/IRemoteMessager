package cn.com.isurpass.iremotemessager.framework;

import java.util.List;

import cn.com.isurpass.iremotemessager.vo.EventData;

public interface IMessageParser<T> 
{
	List<T> parse(EventData data, T targetdata);
}
