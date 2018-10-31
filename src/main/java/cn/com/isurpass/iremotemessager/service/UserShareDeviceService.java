package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.dao.UserShareDeviceDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserShareDeviceService {
    @Resource
    private UserShareDeviceDao userShareDeviceDao;
    @Resource
    private ZwaveDeviceShareService zwaveDeviceShareService;

}
