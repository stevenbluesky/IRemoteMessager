package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
import cn.com.isurpass.iremotemessager.dao.DeviceGroupDao;
import cn.com.isurpass.iremotemessager.domain.DeviceGroup;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DeviceGroupService {
    @Resource
    private DeviceGroupDao deviceGroupDao;

    public DeviceGroup findById(Integer id) {
        if (IRemoteUtils.isBlank(id)) {
            return new DeviceGroup();
        }
        return deviceGroupDao.findById(id).orElse(new DeviceGroup());
    }
}
