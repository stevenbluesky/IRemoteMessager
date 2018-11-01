package cn.com.isurpass.iremotemessager.framework;

import java.util.List;

import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.vo.*;

public interface IMessageMethodDecision {

	void setMsgInfo(EventData data, List<User> msguser);
	List<JPushMessageData> getJPushMessageData();
	List<JPushNotificationData> getJPushNotificationData();
	List<MailData> getMailData();
	List<SmsData> getSmsData();
}
