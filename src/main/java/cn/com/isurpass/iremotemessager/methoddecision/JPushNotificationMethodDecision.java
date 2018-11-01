package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.domain.NotificationSetting;
import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.service.NotificationSettingService;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.JPushMessageData;
import cn.com.isurpass.iremotemessager.vo.JPushNotificationData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationMethodDecision")
public class JPushNotificationMethodDecision extends MethodDecisionBase {
    private List<NotificationSetting> notificationsettings ;
    private List<JPushMessageData> jpushmessagedata ;
    private List<JPushNotificationData> jpushnotificationdata = new ArrayList<>();

    @Resource
    private NotificationSettingService service ;

    @Override
    public void setMsgInfo(EventData data, List<User> msguser)
    {
        super.setMsgInfo(data, msguser);

        queryUserNotificationSetting();
    }

    private void queryUserNotificationSetting()
    {
        List<Integer> pidl = new ArrayList<>();
        for (User mu : super.msguser) {
            pidl.add(mu.getPhoneuserid());
        }

        notificationsettings = service.findByPhoneuserid(pidl);

        List<User> msglst = new ArrayList<>();

        for ( User mu : msguser)
        {
            NotificationSetting ns = findNotificationSetting( mu.getPhoneuserid() , IRemoteConstantDefine.NOTIFICATION_SETTING_TYPE_NOTIFICATION );
            if (issettingvalid(ns)) {
                appendJPushNotificationData(jpushnotificationdata, mu, ns);
            } else {
                msglst.add(mu);
            }
        }

        if ( msglst.size() > 0 )
        {
            jpushmessagedata = new ArrayList<>();
            jpushmessagedata.add(super.createJPushMessageData(msglst));
        }
    }


    @Override
    public List<JPushMessageData> getJPushMessageData()
    {
        return jpushmessagedata;
    }

    @Override
    public List<JPushNotificationData> getJPushNotificationData()
    {
        return jpushnotificationdata;
    }

    private NotificationSetting findNotificationSetting(int phoneuserid,int type)
    {
        for ( NotificationSetting ns : notificationsettings)
        {
            if (ns.getPhoneuserid() == phoneuserid
                    && ns.getNotificationtype() == type) {
                return ns;
            }
        }
        return null ;
    }

    /**
     * 返回 true 的时候将用户分配到 notification中, false 的时候分配到 message 中
     * @param ns
     * @return
     */
    protected boolean issettingvalid(NotificationSetting ns) {
        return true;
    }
}
