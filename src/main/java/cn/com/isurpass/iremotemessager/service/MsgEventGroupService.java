package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.dao.MsgEventGroupDao;
import cn.com.isurpass.iremotemessager.domain.MsgEventGroup;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MsgEventGroupService {
    @Resource
    private MsgEventGroupDao msgEventGroupDao;

    public Map<Integer, String> listAllEventGroup(Integer platform){
        if (platform == null) {
            platform = IRemoteConstantDefine.DEFAULT_PLATFORM;
        }
        HashMap<Integer, String> eventGroupMap = new HashMap<>();

        List<MsgEventGroup> msgEventGroupList = msgEventGroupDao.findByPlatform(platform);
        for (MsgEventGroup msgEventGroup : msgEventGroupList) {
            eventGroupMap.put(msgEventGroup.getMsgeventgroupid(), msgEventGroup.getEventgroupname());
        }

        return eventGroupMap;
    }
}
