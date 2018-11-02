package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.common.constant.DeviceShareSource;
import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.dao.ZwaveDeviceShareDao;
import cn.com.isurpass.iremotemessager.domain.ZWaveDeviceShare;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ZwaveDeviceShareService {
    @Resource
    private ZwaveDeviceShareDao zwaveDeviceShareDao;

    public List<Integer> findSpecifyUserIdsByDeivceid(String deviceid) {
        if (deviceid == null) {
            return new ArrayList<>();
        }

        List<Integer> typeList = Arrays.asList(DeviceShareSource.thirdpart.getSource(), DeviceShareSource.phoneuser.getSource());

        List<ZWaveDeviceShare> zWaveDeviceShareList = zwaveDeviceShareDao.findByDeviceidAndShareowntypeIn(deviceid, typeList);

        ArrayList<Integer> userIds = new ArrayList<>();
        for (ZWaveDeviceShare zWaveDeviceShare : zWaveDeviceShareList) {
            userIds.add(zWaveDeviceShare.getTouserid());
        }

        return userIds;
    }

    public List<Integer> findToUserIdListById(Integer id, Integer type) {
        if (id == null) {
            return new ArrayList<>();
        }

        List<Integer> typeList = Arrays.asList(DeviceShareSource.thirdpart.getSource(), DeviceShareSource.phoneuser.getSource());
        List<ZWaveDeviceShare> zWaveDeviceShareList;

        if (type == IRemoteConstantDefine.DEVICE_TYPE_INFRARED_DEVICE) {
            zWaveDeviceShareList = zwaveDeviceShareDao.findByInfrareddeviceidAndShareowntypeIn(id, typeList);
        } else if (type == IRemoteConstantDefine.DEVICE_TYPE_CAMERA) {
            zWaveDeviceShareList = zwaveDeviceShareDao.findByAndCameraidAndShareowntypeIn(id, typeList);
        } else {
            zWaveDeviceShareList = zwaveDeviceShareDao.findByZwavedeviceidAndShareowntypeIn(id, typeList);
        }

        ArrayList<Integer> userIds = new ArrayList<>();
        for (ZWaveDeviceShare zWaveDeviceShare : zWaveDeviceShareList) {
            userIds.add(zWaveDeviceShare.getTouserid());
        }

        return userIds;
    }
}
