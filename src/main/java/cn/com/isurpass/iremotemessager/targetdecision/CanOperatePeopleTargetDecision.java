package cn.com.isurpass.iremotemessager.targetdecision;

import cn.com.isurpass.iremotemessager.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.domain.*;
import cn.com.isurpass.iremotemessager.service.UserShareDeviceService;
import cn.com.isurpass.iremotemessager.service.UserShareService;
import cn.com.isurpass.iremotemessager.service.ZwaveDeviceShareService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class CanOperatePeopleTargetDecision extends FamilyandFriendsTargetDecision {
    @Resource
    protected ZwaveDeviceShareService zwaveDeviceShareService;

    @Override
    protected List<User> descision()
    {
        List<User> lst = super.descision();

        if (eventparameters.containsKey("phoneuserid")) {
            return lst;
        }
        if (eventparameters.containsKey("zwavedeviceid") || eventparameters.containsKey("zwavesubdeviceid")) {
            ZWaveDevice zwavedevice = (ZWaveDevice) domainobjects.get("zwavedevice");

            return getDeviceUsersByIdAndType(lst, zwavedevice.getZwavedeviceid(), IRemoteConstantDefine.DEVICE_TYPE_ZWAVE_DEVICE);
        }
        if (eventparameters.containsKey("infrareddeviceid")) {
            InfraredDevice infrareddevice = (InfraredDevice) domainobjects.get("infrareddevice");

            return getDeviceUsersByIdAndType(lst, infrareddevice.getInfrareddeviceid(), IRemoteConstantDefine.DEVICE_TYPE_INFRARED_DEVICE);
        }
        if (eventparameters.containsKey("cameraid")) {
            Camera camera = (Camera) domainobjects.get("cameraid");

            return getDeviceUsersByIdAndType(lst, camera.getCameraid(), IRemoteConstantDefine.DEVICE_TYPE_CAMERA);
        }
        if (eventparameters.containsKey("deviceid")) {
            return getDeviceUsers(lst);
        }

        return lst;
    }

    private List<User> getDeviceUsers(List<User> lst) {
        User user = (User) domainobjects.get("user");
        if (user == null) {
            return lst;
        }

        List<Integer> userId = zwaveDeviceShareService.findSpecifyUserIdsByDeivceid(eventparameters.getString("deviceid"));
        return userservice.findByPhoneuseridIn(userId);
    }

    private List<User> getDeviceUsersByIdAndType(List<User> lst, Integer id, int type) {
        User user = (User) domainobjects.get("user");
        if (user == null) {
            return lst;
        }
        List<Integer> specifyUserIds = zwaveDeviceShareService.findToUserIdListById(id, type);
        List<User> userList = userservice.findByPhoneuseridIn(specifyUserIds);
        lst.addAll(userList);
        return lst;
    }
}
