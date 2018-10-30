package cn.com.isurpass.iremotemessager.methoddecision;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.com.isurpass.iremotemessager.domain.NotificationSetting;
import cn.com.isurpass.iremotemessager.service.NotificationSettingService;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.JPushMessageData;
import cn.com.isurpass.iremotemessager.vo.JPushNotificationData;
import cn.com.isurpass.iremotemessager.vo.MsgUser;

@Component
public class NotificationTimeWindownSettingMethodDecision extends MethodDecisionBase
{
	private final static int NOTIFICATION_SETTING_TYPE_NOTIFICATION = 4 ;
	
	
	private List<NotificationSetting> notificationsettings ;
	private List<JPushMessageData> jpushmessagedata ;
	private List<JPushNotificationData> jpushnotificationdata = new ArrayList<>();;
	private Integer currenttime; 
	
	@Resource
	private NotificationSettingService service ;
	
	@Override
	public void setMsgInfo(EventData data, List<MsgUser> msguser)
	{
		super.setMsgInfo(data, msguser);
		
		queryUserNotificationSetting();
	}
	
	private void queryUserNotificationSetting()
	{
		List<Integer> pidl = new ArrayList<>();
		for ( MsgUser mu : super.msguser)
			pidl.add(mu.getPhoneuserid());
		
		notificationsettings = service.findByPhoneuserid(pidl);
		
		List<MsgUser> msglst = new ArrayList<>();
		
		for ( MsgUser mu : msguser)
		{
			NotificationSetting ns = findNotificationSetting( mu.getPhoneuserid() ,NOTIFICATION_SETTING_TYPE_NOTIFICATION );
			if ( issettingvalid(ns) )
				appendJPushNotificationData(jpushnotificationdata , mu , ns);
			else 
				msglst.add(mu);
		}

		if ( msglst.size() > 0 )
		{
			jpushmessagedata = new ArrayList<>();
			jpushmessagedata.add(super.createJPushMessageData(msglst));
		}
	}

	
	@Override
	public List<JPushMessageData> getJPushMessageData()
	{
		return jpushmessagedata;
	}

	@Override
	public List<JPushNotificationData> getJPushNotificationData()
	{
		return jpushnotificationdata;
	}


	private NotificationSetting findNotificationSetting(int phoneuserid,int type)
	{
		for ( NotificationSetting ns : notificationsettings)
		{
			if ( ns.getPhoneuserid() == phoneuserid 
				&&  ns.getNotificationtype() == type )
				return ns ;
		}
		return null ;
	}
	
	private boolean issettingvalid(NotificationSetting ns)
	{
		if (ns == null || ns.getAthome() == 1 )
			return false ;
		
		if ( currenttime == null )
		{
			Calendar d = Calendar.getInstance();
			
			currenttime = d.get(Calendar.HOUR_OF_DAY) * 3600 + d.get(Calendar.MINUTE) * 60 + d.get(Calendar.SECOND);
		}
		
		if ( ns.getStartsecond() < ns.getEndsecond() && ns.getStartsecond() <= currenttime && currenttime <= ns.getEndsecond() )
			return true ;
		if ( ns.getEndsecond() < ns.getStartsecond() && ( ns.getStartsecond() <= currenttime || ns.getEndsecond() >= currenttime))
			return true;
		
		return false ;
	}
}
