package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.vo.JPushNotificationData;
import cn.com.isurpass.iremotemessager.vo.MailData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationMailMethodDecision")
public class JPushNotificationMailMethodDecision extends MethodDecisionBase {
    @Override
    public List<JPushNotificationData> getJPushNotificationData() {
        InnerNotification innerNotification = new InnerNotification();
        return innerNotification.getJPushNotificationData();
    }

    @Override
    public List<MailData> getMailData() {
        InnerMail innerMail = new InnerMail();
        return innerMail.getMailData();
    }

    private class InnerNotification extends JPushNotificationMethodDecision {
    }

    private class InnerMail extends MailMethodDecision {
    }
}
