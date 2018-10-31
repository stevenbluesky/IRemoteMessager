package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.dao.UserShareDeviceDao;
import cn.com.isurpass.iremotemessager.domain.UserShareDevice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserShareDeviceService {
    @Resource
    private UserShareDeviceDao userShareDeviceDao;

    public List<Integer> findSpecifyUserIds(Integer phoneuserid, Integer deviceId) {
        List<UserShareDevice> specifyUser = findZwaveDeviceSpecifyUser(phoneuserid, deviceId);
        ArrayList<Integer> phoneuserids = new ArrayList<>();
        for (UserShareDevice userShareDevice : specifyUser) {
            phoneuserids.add(userShareDevice.getUserShare().getTouserid());
        }
        return phoneuserids;
    }

    public List<Integer> findSpecifyUserIds(Integer phoneuserid, Integer deviceId, int type) {
        List<UserShareDevice> specifyUser;

        if (type == IRemoteConstantDefine.DEVICE_TYPE_INFRARED_DEVICE) {
            specifyUser = findInfraredDeivceSpecifyUser(phoneuserid, deviceId);
        } else if (type == IRemoteConstantDefine.DEVICE_TYPE_CAMERA) {
            specifyUser = findCameraSpecifyUser(phoneuserid, deviceId);
        } else {
            specifyUser = findZwaveDeviceSpecifyUser(phoneuserid, deviceId);
        }

        ArrayList<Integer> phoneuserids = new ArrayList<>();
        for (UserShareDevice userShareDevice : specifyUser) {
            phoneuserids.add(userShareDevice.getUserShare().getTouserid());
        }
        return phoneuserids;
    }


    public List<UserShareDevice> findZwaveDeviceSpecifyUser(Integer phoneuserid, Integer deviceId) {
        List<UserShareDevice> userShareDeviceList = userShareDeviceDao.findByUserShare_ShareuseridAndZwavedeviceid(phoneuserid, deviceId);

        return userShareDeviceList;
    }

    public List<UserShareDevice> findInfraredDeivceSpecifyUser(Integer phoneuserid, Integer deviceId) {
        List<UserShareDevice> userShareDeviceList = userShareDeviceDao.findByUserShare_ShareuseridAndInfrareddeviceid(phoneuserid, deviceId);

        return userShareDeviceList;
    }

    public List<UserShareDevice> findCameraSpecifyUser(Integer phoneuserid, Integer id) {
        List<UserShareDevice> userShareDeviceList = userShareDeviceDao.findByUserShare_ShareuseridAndCameraid(phoneuserid, id);

        return userShareDeviceList;
    }

}
