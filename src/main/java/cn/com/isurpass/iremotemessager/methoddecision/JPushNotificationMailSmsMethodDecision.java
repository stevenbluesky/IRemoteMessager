package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.SpringUtil;
import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.vo.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationMailSmsMethodDecision")
@Scope("prototype")
public class JPushNotificationMailSmsMethodDecision extends MethodDecisionBase {
    private InnerSms innerSms;
    private InnerMail innerMail;
    private InnerNotification innerNotification;

    @Override
    public void setMsgInfo(EventData data, List<User> msguser) {
        super.setMsgInfo(data, msguser);

        innerMail = (InnerMail) SpringUtil.getBean("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationMailSmsMethodDecision$InnerMail");
        innerSms = (InnerSms) SpringUtil.getBean("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationMailSmsMethodDecision$InnerSms");
        innerNotification = (InnerNotification) SpringUtil.getBean("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationMailSmsMethodDecision$InnerNotification");

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
    @Scope("prototype")
    private class InnerSms extends SmsMethodDecision{
    }

    @Component
    @Scope("prototype")
    private class InnerNotification extends JPushNotificationMethodDecision {
    }

    @Component
    @Scope("prototype")
    private class InnerMail extends MailMethodDecision {
    }
}
