package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.domain.NotificationSetting;
import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.service.NotificationSettingService;
import cn.com.isurpass.iremotemessager.vo.JPushNotificationData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationMethodDecision")
public class JPushNotificationMethodDecision extends MethodDecisionBase {
    @Resource
    private NotificationSettingService notificationSettingService;

    @Override
    public List<JPushNotificationData> getJPushNotificationData() {
        List<JPushNotificationData> jpushnotificationdata = new ArrayList<>();

        List<Integer> pidl = new ArrayList<>();
        for (User mu : super.msguser) {
            pidl.add(mu.getPhoneuserid());
        }

        List<NotificationSetting> notificationsettings = notificationSettingService.findByPhoneuserid(pidl);

        for (User user : msguser) {
            NotificationSetting notificationSetting = findNotificationSetting(user.getPhoneuserid(),
                    IRemoteConstantDefine.NOTIFICATION_SETTING_TYPE_NOTIFICATION, notificationsettings);
            appendJPushNotificationData(jpushnotificationdata, user, notificationSetting);
        }

        return jpushnotificationdata;
    }

    private NotificationSetting findNotificationSetting(int phoneuserid, int type, List<NotificationSetting> notificationsettings)
    {
        for ( NotificationSetting ns : notificationsettings){
            if (ns.getPhoneuserid() == phoneuserid
                    && ns.getNotificationtype() == type) {
                return ns;
            }
        }
        return null ;
    }
}
