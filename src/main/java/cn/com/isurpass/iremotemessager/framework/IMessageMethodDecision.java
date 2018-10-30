package cn.com.isurpass.iremotemessager.framework;

import java.util.List;

import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.JPushMessageData;
import cn.com.isurpass.iremotemessager.vo.JPushNotificationData;
import cn.com.isurpass.iremotemessager.vo.MsgUser;

public interface IMessageMethodDecision {

	void setMsgInfo(EventData data, List<MsgUser> msguser);
	List<JPushMessageData> getJPushMessageData();
	List<JPushNotificationData> getJPushNotificationData();
}
