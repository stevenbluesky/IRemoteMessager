package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.JPushNotificationData;
import cn.com.isurpass.iremotemessager.vo.MailData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationMailMethodDecision")
public class JPushNotificationMailMethodDecision extends MethodDecisionBase {
    @Resource
    private InnerMail innerMail;
    @Resource
    private InnerNotification innerNotification;

    @Override
    public void setMsgInfo(EventData data, List<User> msguser) {
        super.setMsgInfo(data, msguser);
        innerMail.setMsgInfo(data, msguser);
        innerNotification.setMsgInfo(data, msguser);
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
    private class InnerNotification extends JPushNotificationMethodDecision {
    }

    @Component
    private class InnerMail extends MailMethodDecision {
    }
}
