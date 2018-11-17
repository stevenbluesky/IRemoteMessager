package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.dao.MsgEventTypeDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MsgEventTypeService {
    @Resource
    private MsgEventTypeDao msgEventTypeDao;

    public List<String> findAllEventCode() {
        return msgEventTypeDao.findAllEventCode();
    }
}
