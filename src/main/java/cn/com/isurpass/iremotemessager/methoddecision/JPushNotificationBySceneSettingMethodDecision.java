package cn.com.isurpass.iremotemessager.methoddecision;

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
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 按情景设置
 * @author jwzh
 */
@Component("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationBySceneSettingMethodDecision")
public class JPushNotificationBySceneSettingMethodDecision extends MethodDecisionBase {
    private Integer scenedbid;
    private InnerNotification innerNotification = new InnerNotification();
    private Scene scene;

    @Override
    public void setMsgInfo(EventData data, List<User> msguser) {
        scenedbid = data.getEventparameters().getInteger("scenedbid");
        super.setMsgInfo(data, msguser);

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

    @Override
    public List<MailData> getMailData() {
        InnerMail innerMail = new InnerMail();

        if (scene != null && scene.getScenenotification() != null) {
            if (StringUtils.isNotBlank(scene.getScenenotification().getMail())) {
                innerMail.setGlobalCheck(true);
            }
        }
        return innerMail.getMailData();
    }

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

    private class InnerMail extends MailMethodDecision {
    }
}
