package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.SpringUtil;
import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
import cn.com.isurpass.iremotemessager.domain.NotificationSetting;
import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.service.NotificationSettingService;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.JPushMessageData;
import cn.com.isurpass.iremotemessager.vo.JPushNotificationData;
import cn.com.isurpass.iremotemessager.vo.MailData;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 按 notificationsetting.app 和 notificationsetting.mail 值推送
 * @author jwzh
 */
@Component("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationByAppMailSettingMethodDecision")
@Scope("prototype")
public class JPushNotificationByAppMailSettingMethodDecision extends MethodDecisionBase {
    private InnerNotification innerNotification;
    private InnerMail innerMail;

    @Override
    public void setMsgInfo(EventData data, List<User> msguser) {
        super.setMsgInfo(data, msguser);

        innerNotification = (InnerNotification) SpringUtil.getBean("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationByAppMailSettingMethodDecision$InnerNotification");
        innerMail = (InnerMail) SpringUtil.getBean("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationByAppMailSettingMethodDecision$InnerMail");

        innerNotification.setMsgInfo(data, msguser);
        innerMail.setMsgInfo(data, msguser);
    }

    @Override
    public List<JPushMessageData> getJPushMessageData() {
        return innerNotification.getJPushMessageData();
    }

    @Override
    public List<JPushNotificationData> getJPushNotificationData() {
        return innerNotification.getJPushNotificationData();
    }

    @Override
    public List<MailData> getMailData() {
        return innerMail.getMailData();
    }

    @Component
    @Scope("prototype")
    private class InnerNotification extends JPushNotificationMethodDecision {
        @Override
        protected boolean issettingvalid(NotificationSetting ns) {
            return (ns != null && IRemoteUtils.isNotBlank(ns.getApp()));
        }
    }

    @Component
    @Scope("prototype")
    private class InnerMail extends MailMethodDecision {
        @Resource
        private NotificationSettingService notificationSettingService;

        @Override
        protected boolean issettingvalid(User user) {
            NotificationSetting setting = notificationSettingService.findByPhoneuseridAndType(
                    user.getPhoneuserid(),
                    IRemoteConstantDefine.WARNING_SETTING_TYPE_MAIL);

            return (setting != null && StringUtils.isNotBlank(setting.getMail()));
        }
    }
}
