package cn.com.isurpass.iremotemessager.messageparser;

import com.alibaba.fastjson.JSONObject;

import cn.com.isurpass.iremotemessager.vo.EventData;

public class MessageParser
{
	private String message ;
	private EventData data;
	
	public MessageParser(String message,EventData data)
	{
		super();
		this.message = message;
		this.data = data;

	}
	public String getMessage()
	{
//		if( message != null )
//		{
//			for ( String key  : parameter.keySet())
//			{
//				String v = parameter.getString(key);
//				if ( v == null )
//					v = "" ;
//				message = message.replaceAll( String.format("\\$\\{%s\\}", key), v );
//			}
//		}
		return message;
	}
	
	public String getMessageforLog()
	{
//		if( message != null && parameter != null )
//		{
//			for ( String key  : parameter.keySet())
//			{
//				if ( "password".equals(key) || "code".equals(key))
//					message = message.replaceAll( String.format("\\$\\{%s\\}", key), "*********");
//				else 
//					message = message.replaceAll( String.format("\\$\\{%s\\}", key), parameter.getString(key));
//			}
//		}
		return message;
	}
	
}
