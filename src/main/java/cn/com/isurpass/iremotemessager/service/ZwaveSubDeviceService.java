package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.dao.ZwaveSubDeviceDao;
import cn.com.isurpass.iremotemessager.domain.ZWaveSubDevice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ZwaveSubDeviceService {
    @Resource
    private ZwaveSubDeviceDao zwaveSubDeviceDao;

    public ZWaveSubDevice findById(Integer id) {
        return zwaveSubDeviceDao.findById(id).orElse(new ZWaveSubDevice());
    }
}
