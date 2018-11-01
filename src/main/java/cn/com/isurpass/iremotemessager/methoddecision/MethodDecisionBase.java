package cn.com.isurpass.iremotemessager.methoddecision;

import java.util.ArrayList;
import java.util.List;

import cn.com.isurpass.iremotemessager.domain.NotificationSetting;
import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.framework.IMessageMethodDecision;
import cn.com.isurpass.iremotemessager.vo.*;

public abstract class MethodDecisionBase implements IMessageMethodDecision 
{
	protected EventData data ;
	protected List<User> msguser;
	
	@Override
	public void setMsgInfo(EventData data, List<User> msguser)
	{
		this.data = data ;
		this.msguser = msguser;
	}
	
	protected JPushMessageData createJPushMessageData(List<User> mulst)
	{
		JPushMessageData pmd = new JPushMessageData();
		pmd.setPhoneusers(new ArrayList<>());
		pmd.setAliases(new String[mulst.size()]);
		
		int i = 0 ;
		for ( User mu : mulst)
		{
			pmd.getPhoneusers().add(mu);
			pmd.getAliases()[i++] = mu.getAlias();
		}
		
		return pmd;
	}
	
	protected void appendJPushNotificationData(List<JPushNotificationData> lst , User mu, NotificationSetting ns)
	{
		JPushNotificationData pnd = null ;
		for (  JPushNotificationData n : lst)
		{
			if ( isNotificationStyleEqual(n , ns) )
			{
				pnd = n;
				break;
			}
		}
		
		if ( pnd == null )
		{
			pnd = new JPushNotificationData();
			pnd.setPhoneusers(new ArrayList<>());
			pnd.setAliaseslist(new ArrayList<>());
			pnd.setAndroidbundlerid(ns.getBuilder_id());
			pnd.setIossound(ns.getSound());
			lst.add(pnd);
		}
		
		pnd.getPhoneusers().add(mu);
		pnd.getAliaseslist().add(mu.getAlias());
		
	}
	
	protected JPushMessageData createJPushNotificationData(List<User> mulst)
	{
		JPushMessageData pmd = new JPushMessageData();
		pmd.setPhoneusers(new ArrayList<>());
		pmd.setAliases(new String[mulst.size()]);
		
		int i = 0 ;
		for ( User mu : mulst)
		{
			pmd.getPhoneusers().add(mu);
			pmd.getAliases()[i++] = mu.getAlias();
		}
		
		return pmd;
	}
	
	protected boolean isNotificationStyleEqual(JPushNotificationData n , NotificationSetting ns)
	{
		if ( isObjectEqual(ns.getBuilder_id() ,  n.getAndroidbundlerid()) == false   )
			return false ;
		
		return isObjectEqual(ns.getSound() ,  n.getIossound()) ;
	}
	
	protected boolean isObjectEqual(Object o1 , Object o2)
	{
		if ( o1 != null )
			return o1.equals(o2);
		else if ( o2 != null )  // o1 != null && o2 == null , not equal 
			return false ;
		return true;  // both o1 and o2 are null , equal ;
	}

	@Override
	public List<JPushMessageData> getJPushMessageData() {
		return new ArrayList<>();
	}

	@Override
	public List<JPushNotificationData> getJPushNotificationData() {
		return new ArrayList<>();
	}

	@Override
	public List<MailData> getMailData() {
		return new ArrayList<>();
	}

	@Override
	public List<SmsData> getSmsData() {
		return new ArrayList<>();
	}
}
