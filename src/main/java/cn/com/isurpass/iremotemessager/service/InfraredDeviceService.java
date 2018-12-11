package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.dao.InfraredDeviceDao;
import cn.com.isurpass.iremotemessager.domain.InfraredDevice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InfraredDeviceService {
    @Resource
    private InfraredDeviceDao infraredDeviceDao;

    public InfraredDevice findById(Integer id) {
        return infraredDeviceDao.findById(id).orElse(new InfraredDevice());
    }
}
