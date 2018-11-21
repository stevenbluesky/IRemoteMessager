package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.dao.MsgProcessClassDao;
import cn.com.isurpass.iremotemessager.domain.MsgProcessClass;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MsgProcessClassService {
    @Resource
    private MsgProcessClassDao msgProcessClassDao;

    public Map<String, String> listProcessClass(Integer type, Integer subType) {
        HashMap<String, String> processClassMap = new HashMap<>();
        List<MsgProcessClass> processClassList;
        if (subType != null) {
            processClassList = msgProcessClassDao.findByTypeAndSubtype(type, subType);
        } else {
            processClassList = msgProcessClassDao.findByType(type);
        }

        for (MsgProcessClass msgProcessClass : processClassList) {
            processClassMap.put(String.valueOf(msgProcessClass.getName()),String.valueOf(msgProcessClass.getMsgprocessclassid()));
        }

        return processClassMap;
    }
}
