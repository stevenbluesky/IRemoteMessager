package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.dao.UserShareDao;
import cn.com.isurpass.iremotemessager.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserShareService {
    @Resource
    private UserShareDao userShareDao;

    public List<UserShare> findFriends(Integer phoneuserid) {
        if (phoneuserid == null) {
            return new ArrayList<>();
        }

        return userShareDao.findByShareuseridAndSharedevicetypeAndSharetypeAndStatus(
                phoneuserid,
                IRemoteConstantDefine.USER_SHARE_DEVICE_TYPE_ALL,
                IRemoteConstantDefine.USER_SHARE_TYPE_NORMAL,
                IRemoteConstantDefine.USER_SHARE_STATUS_NORMAL);
    }

    public UserShare find(Integer userShareId) {
        return userShareDao.findById(userShareId).orElse(null);
    }
}
