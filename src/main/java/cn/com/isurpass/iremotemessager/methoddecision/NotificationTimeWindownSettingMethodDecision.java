package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.domain.NotificationSetting;
import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.JPushMessageData;
import cn.com.isurpass.iremotemessager.vo.JPushNotificationData;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Calendar;
import java.util.List;

/**
 * 按时间设置 推送
 * @author jwzh
 */
@Component("cn.com.isurpass.iremotemessager.methoddecision.NotificationTimeWindownSettingMethodDecision")
public class NotificationTimeWindownSettingMethodDecision extends MethodDecisionBase
{
	private InnerNotification innerNotification = new InnerNotification();

	@Override
	public void setMsgInfo(EventData data, List<User> msguser) {
		super.setMsgInfo(data, msguser);

		innerNotification.setMsgInfo(data, msguser);
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

	private class InnerNotification extends JPushNotificationMethodDecision{
		private Integer currenttime;

		@Override
		protected boolean issettingvalid(NotificationSetting ns)
		{
			if (ns == null || ns.getAthome() == 1) {
				return false;
			}

			if ( currenttime == null )			{
				Calendar d = Calendar.getInstance();

				currenttime = d.get(Calendar.HOUR_OF_DAY) * 3600 + d.get(Calendar.MINUTE) * 60 + d.get(Calendar.SECOND);
			}

			if (ns.getStartsecond() < ns.getEndsecond() && ns.getStartsecond() <= currenttime && currenttime <= ns.getEndsecond()) {
				return true;
			}
			if (ns.getEndsecond() < ns.getStartsecond() && (ns.getStartsecond() <= currenttime || ns.getEndsecond() >= currenttime)) {
				return true;
			}

			return false ;
		}
	}
}
