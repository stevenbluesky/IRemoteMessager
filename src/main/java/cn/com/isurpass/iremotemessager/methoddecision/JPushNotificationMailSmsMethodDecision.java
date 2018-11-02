package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.vo.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationMailSmsMethodDecision")
public class JPushNotificationMailSmsMethodDecision extends MethodDecisionBase {
    @Resource
    private InnerSms innerSms;
    @Resource
    private InnerMail innerMail;
    @Resource
    private InnerNotification innerNotification;

    @Override
    public void setMsgInfo(EventData data, List<User> msguser) {
        super.setMsgInfo(data, msguser);
        innerSms.setMsgInfo(data, msguser);
        innerMail.setMsgInfo(data, msguser);
        innerNotification.setMsgInfo(data, msguser);
    }

    @Override
    public List<SmsData> getSmsData() {
        return innerSms.getSmsData();
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
    private class InnerSms extends SmsMethodDecision{
    }

    @Component
    private class InnerNotification extends JPushNotificationMethodDecision {
    }

    @Component
    private class InnerMail extends MailMethodDecision {
    }
}
