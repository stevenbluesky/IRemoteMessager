package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.dao.UserShareDao;
import cn.com.isurpass.iremotemessager.domain.*;
import cn.com.isurpass.iremotemessager.util.IRemoteUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserShareService {
    @Resource
    private UserShareDao userShareDao;

    public List<UserShare> findFriends(Integer phoneuserid) {
        return userShareDao.findByShareuseridAndSharedevicetypeAndSharetypeAndStatus(
                phoneuserid,
                IRemoteConstantDefine.USER_SHARE_DEVICE_TYPE_ALL,
                IRemoteConstantDefine.USER_SHARE_TYPE_NORMAL,
                IRemoteConstantDefine.USER_SHARE_STATUS_NORMAL);
    }
}
