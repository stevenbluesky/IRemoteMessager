package cn.com.isurpass.iremotemessager.service;

import javax.annotation.Resource;

import cn.com.isurpass.iremotemessager.util.IRemoteUtils;
import org.springframework.stereotype.Component;

import cn.com.isurpass.iremotemessager.dao.ZWaveDeviceDao;
import cn.com.isurpass.iremotemessager.domain.ZWaveDevice;

import java.util.*;

@Component
public class ZWaveDeviceService {
    @Resource
    private ZWaveDeviceDao dao;

    public ZWaveDevice findById(Integer zwavedeviceid) {
        return dao.findById(zwavedeviceid).orElse(null);
    }

    public Set<Integer> getUserIdWhichZwaveDeviceInThisDevice(Map<Integer, Integer> zwavedeviceidsMap, String deviceid) {
        HashSet<Integer> userIdSet = new HashSet<>();

        if (IRemoteUtils.isBlank(zwavedeviceidsMap)) {
            return userIdSet;
        }
        for (Map.Entry<Integer, Integer> zwaveDeviceidUserIdEntry : zwavedeviceidsMap.entrySet()) {
            if (!userIdSet.contains(zwaveDeviceidUserIdEntry.getValue())) {
                ZWaveDevice zWaveDevice = dao.findById(zwaveDeviceidUserIdEntry.getKey()).orElse(null);
                if (zWaveDevice != null && zWaveDevice.getDeviceid().equals(deviceid)) {
                    userIdSet.add(zwaveDeviceidUserIdEntry.getValue());
                }
            }
        }
        return userIdSet;
    }
}
