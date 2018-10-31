package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.dao.InfraredDeviceDao;
import cn.com.isurpass.iremotemessager.domain.InfraredDevice;
import cn.com.isurpass.iremotemessager.util.IRemoteUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class InfraredDeviceService {
    @Resource
    private InfraredDeviceDao infraredDeviceDao;

    public Set<Integer> getUserIdWhichInfaredDeviceInThisDevice(Map<Integer, Integer> infrareddeviceMap, String deviceid) {
        HashSet<Integer> userIdSet = new HashSet<>();

        if (!IRemoteUtils.isNotBlank(infrareddeviceMap)) {
            return userIdSet;
        }

        for (Map.Entry<Integer, Integer> zwaveDeviceidUserIdEntry : infrareddeviceMap.entrySet()) {
            if (!userIdSet.contains(zwaveDeviceidUserIdEntry.getValue())) {
                InfraredDevice infraredDevice = infraredDeviceDao.findById(zwaveDeviceidUserIdEntry.getKey()).orElse(null);
                if (infraredDevice != null && infraredDevice.getDeviceid().equals(deviceid)) {
                    userIdSet.add(zwaveDeviceidUserIdEntry.getValue());
                }
            }
        }
        return userIdSet;
    }
}
