package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.dao.PhoneUserAttributeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liwenxiang
 * Date:2018/11/15
 * Time:16:24
 */
@Service
public class PhoneUserAttributeService {
    @Autowired
    private PhoneUserAttributeDao puad;
}
