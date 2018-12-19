package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.SpringUtil;
import cn.com.isurpass.iremotemessager.domain.NotificationSetting;
import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.JPushMessageData;
import cn.com.isurpass.iremotemessager.vo.JPushNotificationData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 按告警状态
 * @author jwzh
 */
@Component("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationByWarningStatusMethodDecision")
@Scope("prototype")
public class JPushNotificationByWarningStatusMethodDecision extends MethodDecisionBase {
    private Integer warningstatus;
    private InnerNotification innerNotification;

    @Override
    public void setMsgInfo(EventData data, List<User> msguser) {
        super.setMsgInfo(data, msguser);
        warningstatus = data.getEventparameters().getInteger("warningstatus");

        innerNotification = (InnerNotification) SpringUtil.getBean("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationByWarningStatusMethodDecision$InnerNotification");

        innerNotification.setMsgInfo(data, msguser);
    }

    @Override
    public List<JPushMessageData> getJPushMessageData() {
        return innerNotification.getJPushMessageData();
    }

    @Override
    public List<JPushNotificationData> getJPushNotificationData() {
        return innerNotification.getJPushNotificationData();
    }

    @Component
    @Scope("prototype")
    private class InnerNotification extends JPushNotificationMethodDecision {
        @Override
        protected boolean issettingvalid(NotificationSetting ns) {
            return IRemoteUtils.isNotBlank(JPushNotificationByWarningStatusMethodDecision.this.warningstatus);
        }
    }
}
