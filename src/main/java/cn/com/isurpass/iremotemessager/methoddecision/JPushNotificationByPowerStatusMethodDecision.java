package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.domain.Gateway;
import cn.com.isurpass.iremotemessager.domain.NotificationSetting;
import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.service.GatewayService;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.JPushMessageData;
import cn.com.isurpass.iremotemessager.vo.JPushNotificationData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 按电源供电状态
 * @author jwzh
 */
@Component("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationByPowerStatusMethodDecision")
public class JPushNotificationByPowerStatusMethodDecision extends MethodDecisionBase {
    private String deviceid;
    private InnerNotification innerNotification = new InnerNotification();

    @Override
    public void setMsgInfo(EventData data, List<User> msguser) {
        deviceid = data.getEventparameters().getString("deviceid");
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

    private class InnerNotification extends JPushNotificationMethodDecision {
        @Resource
        private GatewayService gatewayService;

        @Override
        protected boolean issettingvalid(NotificationSetting ns) {
            if (deviceid != null) {
                Gateway gateway = gatewayService.findById(deviceid);

                if (gateway != null) {
                    if (gateway.getPowertype() == IRemoteConstantDefine.REMOTE_POWER_TYPE_BATTERY) {
                        return true;
                    }
                }
            }

            return false;
        }
    }
}
