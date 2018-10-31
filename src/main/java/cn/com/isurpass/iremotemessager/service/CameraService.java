package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.dao.CameraDao;
import cn.com.isurpass.iremotemessager.domain.Camera;
import cn.com.isurpass.iremotemessager.util.IRemoteUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class CameraService {

    @Resource
    private CameraDao cameraDao;

    public Set<Integer> getUserIdWhichCameraIdInThisDevice(Map<Integer, Integer> cameraMap, String deviceid) {
        HashSet<Integer> userIdSet = new HashSet<>();

        if (!IRemoteUtils.isNotBlank(cameraMap)) {
            return userIdSet;
        }

        for (Map.Entry<Integer, Integer> zwaveDeviceidUserIdEntry : cameraMap.entrySet()) {
            if (!userIdSet.contains(zwaveDeviceidUserIdEntry.getValue())) {
                Camera camera = cameraDao.findById(zwaveDeviceidUserIdEntry.getKey()).orElse(null);
                if (camera != null && camera.getDeviceid().equals(deviceid)) {
                    userIdSet.add(zwaveDeviceidUserIdEntry.getValue());
                }
            }
        }
        return userIdSet;
    }
}
