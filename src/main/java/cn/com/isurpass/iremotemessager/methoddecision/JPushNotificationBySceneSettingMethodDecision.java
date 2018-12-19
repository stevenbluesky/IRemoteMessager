package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.SpringUtil;
import cn.com.isurpass.iremotemessager.domain.NotificationSetting;
import cn.com.isurpass.iremotemessager.domain.Scene;
import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.service.SceneService;
import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
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
 * 按情景设置
 * @author jwzh
 */
@Component("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationBySceneSettingMethodDecision")
@Scope("prototype")
public class JPushNotificationBySceneSettingMethodDecision extends MethodDecisionBase {
    private Integer scenedbid;
    private Scene scene;
    private InnerNotification innerNotification;
    private InnerMail innerMail;

    @Override
    public void setMsgInfo(EventData data, List<User> msguser) {
        scenedbid = data.getEventparameters().getInteger("scenedbid");
        super.setMsgInfo(data, msguser);

        innerNotification = (InnerNotification) SpringUtil.getBean("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationBySceneSettingMethodDecision$InnerNotification");
        innerMail = (InnerMail) SpringUtil.getBean("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationBySceneSettingMethodDecision$InnerMail");

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
        if (scene != null && scene.getScenenotification() != null) {
            if (StringUtils.isNotBlank(scene.getScenenotification().getMail())) {
                innerMail.setGlobalCheck(true);
            }
        }
        return innerMail.getMailData();
    }

    @Component
    @Scope("prototype")
    private class InnerNotification extends JPushNotificationMethodDecision {
        @Resource
        private SceneService sceneService;

        @Override
        protected boolean issettingvalid(NotificationSetting ns) {
            if (IRemoteUtils.isNotBlank(scenedbid)) {
                scene = sceneService.findById(scenedbid);

                if (scene != null && scene.getScenenotification() != null) {
                    if (IRemoteUtils.isNotBlank(scene.getScenenotification().getApp())) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    @Component
    @Scope("prototype")
    private class InnerMail extends MailMethodDecision {
    }
}
