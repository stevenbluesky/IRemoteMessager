package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.dao.*;
import cn.com.isurpass.iremotemessager.domain.*;
import cn.com.isurpass.iremotemessager.vo.EventGroupVo;
import cn.com.isurpass.iremotemessager.vo.EventtypeVo;
import cn.com.isurpass.iremotemessager.vo.PushSettingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class MsgPushSettingService {
    @Resource
    private MsgEventGroupEventDao msgEventGroupEventDao;
    @Resource
    private MsgProcessClassDao msgProcessClassDao;
    @Resource
    private MsgDefaultProcessClassService msgDefaultProcessClassService;
    @Resource
    private MsgEventGroupDao eventgroupdao;
    @Resource
    private MsgEventTypeDao eventdao;

    @Autowired
    private MsgPushSettingDao msgPushSettingDao;

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

    public Map<String, Object> listPushSetting(Pageable pageable, PushSettingVo pushsettingvo) {
        Map<String, Object> map = new HashMap<>();
        List<MsgEventGroup> eventgroup = eventgroupdao.findByEventgroupnameContaining(pushsettingvo.getEventgroupname());
        List<MsgPushSetting> pushsettinglist = null;
        long count = 0;
        int platform = pushsettingvo.getPlatform()==null?888888:pushsettingvo.getPlatform();
        if(platform==888888){
            pushsettinglist = msgPushSettingDao.findByMsgEventGroupIn(eventgroup,pageable);
            count = msgPushSettingDao.countByMsgEventGroupIn(eventgroup);
        }else{
            pushsettinglist = msgPushSettingDao.findByPlatformAndMsgEventGroupIn(pushsettingvo.getPlatform(),eventgroup,pageable);
            count = msgPushSettingDao.countByPlatformAndMsgEventGroupIn(pushsettingvo.getPlatform(),eventgroup);
        }

        map.put("total",count);
        map.put("rows", pushsettinglistdbtransfer2vo(pushsettinglist));
        return map;
    }
    private List<PushSettingVo> pushsettinglistdbtransfer2vo(List<MsgPushSetting> pushsettinglist) {
        List<PushSettingVo> pushsettinglistvo = new ArrayList<>();
        for(MsgPushSetting p : pushsettinglist){
            PushSettingVo pushsettingvo = new PushSettingVo();
            pushsettingvo.setMsgpushsettingid(p.getMsgpushsettingid());
            pushsettingvo.setPlatform(p.getPlatform());
            pushsettingvo.setEventgroupname(p.getMsgEventGroup().getEventgroupname());
            pushsettingvo.setPushtargetclass(p.getMsgPushTargetDecision().getClassname());
            pushsettingvo.setPushmethodclass(p.getMsgPushMethod().getClassname());
//            pushsettingvo.setPushclass(p.getMsgPushSettingDtlList().getMsgProcessClass().getClassname());
            pushsettinglistvo.add(pushsettingvo);
        }
        return pushsettinglistvo;
    }
}
