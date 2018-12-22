package cn.com.isurpass.iremotemessager.service;

import javax.annotation.Resource;

import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
import com.sun.corba.se.spi.ior.IdentifiableFactory;
import org.springframework.stereotype.Component;

import cn.com.isurpass.iremotemessager.dao.ZWaveDeviceDao;
import cn.com.isurpass.iremotemessager.domain.ZWaveDevice;

@Component
public class ZWaveDeviceService {
    @Resource
    private ZWaveDeviceDao dao;

    public ZWaveDevice findById(Integer zwavedeviceid) {
        return IRemoteUtils.isBlank(zwavedeviceid)
                ? new ZWaveDevice()
                :dao.findById(zwavedeviceid).orElse(new ZWaveDevice());
    }
}
