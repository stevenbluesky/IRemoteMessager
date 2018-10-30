package cn.com.isurpass.iremotemessager.jpush;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.com.isurpass.iremotemessager.jpush.vo.Audience;
import cn.com.isurpass.iremotemessager.jpush.vo.Message;
import cn.com.isurpass.iremotemessager.jpush.vo.Notification;
import cn.com.isurpass.iremotemessager.jpush.vo.Payload;
import cn.com.isurpass.iremotemessager.vo.JPushNotificationData;

public class JPushHelper
{
	public static Payload createMessage(String[] alias ,  JSONObject extras)
	{
		Payload pl = new Payload();
		
		pl.setAudience(createAudience(alias));
		
		Message m = new Message();
		m.setMsg_content("");
		m.setExtras(extras);
		pl.setMessage(m);

		return pl ;
	}
	
	public static Payload createNotification(String[] alias , String message , JSONObject extras,JPushNotificationData targetdata)
	{
		Payload pl = new Payload();

		pl.setAudience(createAudience(alias));

		Notification n = new Notification();
		n.setAlert(message);
		n.getAndroid().setExtras(extras);
		n.getIos().setExtras(extras);
		n.getAndroid().setTitle("");

		if ( StringUtils.isNotBlank(targetdata.getIossound()))
			n.getIos().setSound(targetdata.getIossound());
		if ( targetdata.getAndroidbundlerid() != null )
			n.getAndroid().setBuilder_id(targetdata.getAndroidbundlerid());
		
		pl.setNotification(n);
		return pl ;
	}
	
	private static Audience createAudience(String[] alias)
	{
		Audience a = new Audience();
		a.setAlias(alias);
	
		return a ;
	}
}
