package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.dao.UserShareDao;
import cn.com.isurpass.iremotemessager.domain.*;
import cn.com.isurpass.iremotemessager.util.IRemoteUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserShareService {
    @Resource
    private UserShareDao userShareDao;
    @Resource
    private ZWaveDeviceService zWaveDeviceService;
    @Resource
    private InfraredDeviceService infraredDeviceService;
    @Resource
    private CameraService cameraService;

    public List<UserShare> findFriends(Integer phoneuserid) {
        return userShareDao.findByShareuseridAndSharedevicetypeAndSharetypeAndStatus(
                phoneuserid,
                IRemoteConstantDefine.USER_SHARE_DEVICE_TYPE_ALL,
                IRemoteConstantDefine.USER_SHARE_TYPE_NORMAL,
                IRemoteConstantDefine.USER_SHARE_STATUS_NORMAL);
    }

    public List<UserShare> findSpecifyUser(Integer phoneuserid) {
        return userShareDao.findByShareuseridAndSharedevicetypeAndSharetypeAndStatus(
                phoneuserid,
                IRemoteConstantDefine.USER_SHARE_DEVICE_TYPE_SPECIFY,
                IRemoteConstantDefine.USER_SHARE_TYPE_NORMAL,
                IRemoteConstantDefine.USER_SHARE_STATUS_NORMAL);
    }

    /**
     *  获取他人userid, 他人表示:这个网关中设备设备级授权给的人
     * @param phoneuserid
     * @return
     */
    public List<Integer> findSpecifyUseridsByDeivceid(Integer phoneuserid, String deviceid) {
        List<UserShare> specifyUser = findSpecifyUser(phoneuserid);
        Map<Integer,Integer> zwavedeviceidsMap = new HashMap<>();
        Map<Integer,Integer> infrareddeviceidsMap = new HashMap<>();
        Map<Integer,Integer> cameraidsMap = new HashMap<>();
        Set<Integer> userIdSet = new HashSet<>();

        for (UserShare userShare : specifyUser) {
            for (UserShareDevice userShareDevice : userShare.getUserShareDevices()) {
                putIds(zwavedeviceidsMap, infrareddeviceidsMap, cameraidsMap, userShareDevice);
            }
        }

        userIdSet.addAll(zWaveDeviceService.getUserIdWhichZwaveDeviceInThisDevice(zwavedeviceidsMap, deviceid));
        userIdSet.addAll(infraredDeviceService.getUserIdWhichInfaredDeviceInThisDevice(infrareddeviceidsMap, deviceid));
        userIdSet.addAll(cameraService.getUserIdWhichCameraIdInThisDevice(cameraidsMap, deviceid));

        return new ArrayList<>(userIdSet);
    }

    private void putIds(Map<Integer,Integer> zwavedeviceids, Map<Integer,Integer> infrareddeviceids,Map<Integer,Integer> cameraids, UserShareDevice userShareDevice) {
        if (IRemoteUtils.isNotBlank(userShareDevice.getZwavedeviceid())) {
            zwavedeviceids.put(userShareDevice.getZwavedeviceid(), userShareDevice.getUserShare().getTouserid());
        } else if (IRemoteUtils.isNotBlank(userShareDevice.getCameraid())) {
            cameraids.put(userShareDevice.getCameraid(), userShareDevice.getUserShare().getTouserid());
        } else if (IRemoteUtils.isNotBlank(userShareDevice.getInfrareddeviceid())) {
            infrareddeviceids.put(userShareDevice.getInfrareddeviceid(), userShareDevice.getUserShare().getTouserid());
        }
    }
}
