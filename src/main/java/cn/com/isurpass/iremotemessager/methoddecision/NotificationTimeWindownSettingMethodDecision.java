package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.SpringUtil;
import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.domain.NotificationSetting;
import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.service.NotificationSettingService;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.JPushMessageData;
import cn.com.isurpass.iremotemessager.vo.JPushNotificationData;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

/**
 * 按时间设置 推送
 * @author jwzh
 */
@Component("cn.com.isurpass.iremotemessager.methoddecision.NotificationTimeWindownSettingMethodDecision")
@Scope("prototype")
public class NotificationTimeWindownSettingMethodDecision extends MethodDecisionBase
{
	private InnerNotification innerNotification;
	private InnerSms innerSms;

	@Override
	public void setMsgInfo(EventData data, List<User> msguser) {
		super.setMsgInfo(data, msguser);

		innerNotification = (InnerNotification) SpringUtil.getBean("cn.com.isurpass.iremotemessager.methoddecision.NotificationTimeWindownSettingMethodDecision$InnerNotification");
		innerSms = (InnerSms) SpringUtil.getBean("cn.com.isurpass.iremotemessager.methoddecision.NotificationTimeWindownSettingMethodDecision$InnerSms");

		innerNotification.setMsgInfo(data, msguser);
		innerSms.setMsgInfo(data, msguser);
	}

	@Override
	public List<JPushMessageData> getJPushMessageData()
	{
		return innerNotification.getJPushMessageData();
	}

	@Override
	public List<JPushNotificationData> getJPushNotificationData()
	{
		return innerNotification.getJPushNotificationData();
	}

	@Component
	@Scope("prototype")
	public class InnerNotification extends JPushNotificationMethodDecision{
		@Override
		protected boolean issettingvalid(NotificationSetting ns){
			return NotificationTimeWindownSettingMethodDecision.this.isSettingValid(ns);
		}
	}

	@Component
	@Scope("prototype")
	public class InnerSms extends SmsMethodDecision {
		@Resource
		private NotificationSettingService notificationSettingService;

		@Override
		protected boolean check(User user) {
			NotificationSetting setting = notificationSettingService.findByPhoneuseridAndType(
					user.getPhoneuserid(), IRemoteConstantDefine.NOTIFICATION_SETTING_TYPE_NOTIFICATION);
			return NotificationTimeWindownSettingMethodDecision.this.isSettingValid(setting);
		}
	}

	private boolean isSettingValid(NotificationSetting ns) {
		if (ns == null || ns.getAthome() == 1) {
			return false;
		}
		Calendar d = Calendar.getInstance();

		Integer currentTime = d.get(Calendar.HOUR_OF_DAY) * 3600 + d.get(Calendar.MINUTE) * 60 + d.get(Calendar.SECOND);

		if (ns.getStartsecond() < ns.getEndsecond() && ns.getStartsecond() <= currentTime && currentTime <= ns.getEndsecond()) {
			return true;
		}
		if (ns.getEndsecond() < ns.getStartsecond() && (ns.getStartsecond() <= currentTime || ns.getEndsecond() >= currentTime)) {
			return true;
		}

		return false ;
	}
}
