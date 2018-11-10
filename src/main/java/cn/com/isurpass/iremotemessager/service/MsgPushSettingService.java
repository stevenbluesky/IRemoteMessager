package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.dao.MsgPushSettingDao;
import cn.com.isurpass.iremotemessager.domain.MsgPushSetting;
import cn.com.isurpass.iremotemessager.domain.MsgPushSettingDtl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class MsgPushSettingService {
    @Resource
    private MsgPushSettingDao msgPushSettingDao;
    @Resource
    private MsgDefaultProcessClassService msgDefaultProcessClassService;

    public MsgPushSetting findPushSetting(String eventcode, Integer platform) {
        MsgPushSetting msgPushSetting = msgPushSettingDao.findMsgPushSetting(eventcode, platform);
        return msgPushSetting;
    }

    public Map<Integer, String> findParserMap(String eventcode, Integer platform) {
        return findMap(eventcode, platform, IRemoteConstantDefine.PROCESSOR_CLASS_TYPE_CONTENT_PARSER);
    }

    public Map<Integer, String> findSenderMap(String eventcode, Integer platform) {
        return findMap(eventcode, platform, IRemoteConstantDefine.PROCESSOR_CLASS_TYPE_PUSH);
    }

    public Map<Integer, String> findMap(String eventcode, Integer platform, int type) {
        MsgPushSetting pushSetting = findPushSetting(eventcode, platform);
        HashMap<Integer, String> pusherMap = new HashMap<>();
        if (pushSetting != null && pushSetting.getMsgPushSettingDtlList() != null && pushSetting.getMsgPushSettingDtlList().size() != 0) {
            for (MsgPushSettingDtl msgPushSettingDtl : pushSetting.getMsgPushSettingDtlList()) {
                if (msgPushSettingDtl.getType() == type
                        && msgPushSettingDtl.getMsgProcessClass() != null && msgPushSettingDtl.getMsgProcessClass().getClassname() != null) {
                    pusherMap.put(msgPushSettingDtl.getSubtype(), msgPushSettingDtl.getMsgProcessClass().getClassname());
                }
            }
        }

        for (int i = 1; i < 5; i++) {
            if (!pusherMap.containsKey(i) || pusherMap.get(i) == null) {
                pusherMap.put(i, msgDefaultProcessClassService.findDefaultProcessorClassNameBySubtype(platform, eventcode, type, i));
            }
        }

        return pusherMap;
    }
}
