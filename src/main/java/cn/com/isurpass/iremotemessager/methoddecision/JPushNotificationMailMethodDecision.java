package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.SpringUtil;
import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.JPushNotificationData;
import cn.com.isurpass.iremotemessager.vo.MailData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationMailMethodDecision")
@Scope("prototype")
public class JPushNotificationMailMethodDecision extends MethodDecisionBase {
    private InnerMail innerMail;
    private InnerNotification innerNotification;

    @Override
    public void setMsgInfo(EventData data, List<User> msguser) {
        super.setMsgInfo(data, msguser);

        innerMail = (InnerMail) SpringUtil.getBean("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationMailMethodDecision$InnerMail");
        innerNotification = (InnerNotification) SpringUtil.getBean("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationMailMethodDecision$InnerNotification");

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
    @Scope("prototype")
    private class InnerNotification extends JPushNotificationMethodDecision {
    }

    @Component
    @Scope("prototype")
    private class InnerMail extends MailMethodDecision {
    }
}
