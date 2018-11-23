package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.dao.OemProductorDao;
import cn.com.isurpass.iremotemessager.domain.OemProductor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OemProductorService {
    @Resource
    private OemProductorDao oemProductorDao;

    public Iterable<OemProductor> listOemPushKey(){
       return oemProductorDao.findAll();
    }
}
