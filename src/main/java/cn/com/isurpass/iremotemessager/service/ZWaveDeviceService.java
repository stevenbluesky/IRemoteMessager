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
}
