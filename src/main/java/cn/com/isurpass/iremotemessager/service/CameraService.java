package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
import cn.com.isurpass.iremotemessager.dao.CameraDao;
import cn.com.isurpass.iremotemessager.domain.Camera;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CameraService {
    @Resource
    private CameraDao cameraDao;

    public Camera findById(Integer id) {
        return IRemoteUtils.isBlank(id)
                ? new Camera()
                :cameraDao.findById(id).orElse(new Camera());
    }
}
