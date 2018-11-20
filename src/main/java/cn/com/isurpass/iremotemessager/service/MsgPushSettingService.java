package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.dao.MsgPushSettingDao;
import cn.com.isurpass.iremotemessager.domain.MsgPushSetting;
import cn.com.isurpass.iremotemessager.domain.MsgPushSettingDtl;
import cn.com.isurpass.iremotemessager.dao.*;
import cn.com.isurpass.iremotemessager.domain.*;
import cn.com.isurpass.iremotemessager.vo.PushSettingVo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class MsgPushSettingService {
    @Resource
    private MsgEventGroupDao eventgroupdao;
    @Resource
    private MsgPushSettingDao msgPushSettingDao;
    @Resource
    private MsgProcessClassDao msgProcessClassDao;
    @Resource
    private MsgDefaultProcessClassService msgDefaultProcessClassService;
    @Resource
    private MsgEventTypeDao eventdao;
    @Resource
    private MsgPushSettingDtlDao msgPushSettingDtlDao;

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
            pushsettingvo.setPushtargetclass(p.getMsgPushTargetDecision().getName());
            pushsettingvo.setPushmethodclass(p.getMsgPushMethod().getName());
            pushsettingvo.setPushclass(getPushClassStr(p));
            pushsettinglistvo.add(pushsettingvo);
        }
        return pushsettinglistvo;
    }
    private String getPushClassStr(MsgPushSetting p){
        StringBuffer sb = new StringBuffer();
        List<MsgPushSettingDtl> dtllist = p.getMsgPushSettingDtlList();
        if(dtllist!=null&&dtllist.size()>0){
            for(MsgPushSettingDtl d : dtllist){
                sb.append(d.getMsgProcessClass().getName()+", ");
            }
        }
        String pushclassstr = sb.toString();
        return pushclassstr.contains(", ")==true?pushclassstr.substring(0,pushclassstr.lastIndexOf(", ")):pushclassstr;
    }
    @Transactional
    public void deletePushSettings(String[] ids) {
        if(ids!=null&&ids.length>0){
            for(String id:ids){
                MsgPushSetting byMsgpushsettingid = msgPushSettingDao.findByMsgpushsettingid(Integer.parseInt(id));
                byMsgpushsettingid.getMsgPushSettingDtlList().clear();
                byMsgpushsettingid.getMsgEventGroup().setMsgPushSetting(null);
                msgPushSettingDao.delete(byMsgpushsettingid);
                //msgPushSettingDao.deleteByMsgpushsettingid(Integer.parseInt(id));
            }
        }
    }

    public PushSettingVo findByMsgpushsettingid(Integer msgpushsettingid) {
        MsgPushSetting pushsetting = msgPushSettingDao.findByMsgpushsettingid(msgpushsettingid);
        PushSettingVo pushsettingvo = new PushSettingVo();
        pushsettingvo.setMsgpushsettingid(pushsetting.getMsgpushsettingid());
        pushsettingvo.setPlatform(pushsetting.getPlatform());
        pushsettingvo.setEventgroupname(pushsetting.getMsgEventGroup().getEventgroupname());
        pushsettingvo.setPushtargetclass(String.valueOf(pushsetting.getMsgPushTargetDecision().getMsgprocessclassid()));
        pushsettingvo.setPushmethodclass(String.valueOf(pushsetting.getMsgPushMethod().getMsgprocessclassid()));
        List<MsgPushSettingDtl> dtllist = pushsetting.getMsgPushSettingDtlList();
        if(dtllist!=null&&dtllist.size()>0){
            for(MsgPushSettingDtl d: dtllist){
                if(d.getType()==4&&d.getSubtype()<3){
                    pushsettingvo.setApppushclass(String.valueOf(d.getMsgProcessClass().getMsgprocessclassid()));
                }
                if(d.getType()==4&&d.getSubtype()==3){
                    pushsettingvo.setSmspushclass(String.valueOf(d.getMsgProcessClass().getMsgprocessclassid()));
                }
                if(d.getType()==4&&d.getSubtype()==4){
                    pushsettingvo.setEmailpushclass(String.valueOf(d.getMsgProcessClass().getMsgprocessclassid()));
                }
            }
        }
        return pushsettingvo;
    }

    public boolean addPushSettingData(PushSettingVo pushsettingvo) {
        MsgPushSetting pushsetting = new MsgPushSetting();
        String eventgroupid = pushsettingvo.getEventgroupname();
        String targetid = pushsettingvo.getPushtargetclass();
        String methodid = pushsettingvo.getPushmethodclass();

        MsgEventGroup eventgroup = eventgroupdao.findByMsgeventgroupid(Integer.parseInt(eventgroupid));
        MsgProcessClass targetclass = msgProcessClassDao.findByMsgprocessclassid(Integer.parseInt(targetid));
        MsgProcessClass methodclass = msgProcessClassDao.findByMsgprocessclassid(Integer.parseInt(methodid));

        List<MsgPushSettingDtl> dtllist = new ArrayList<>();
        String apppushclassid = pushsettingvo.getApppushclass();
        String smspushclassid = pushsettingvo.getSmspushclass();
        String emailpushclassid = pushsettingvo.getEmailpushclass();

        if(!"0".equals(apppushclassid)){
            MsgProcessClass appclass = msgProcessClassDao.findByMsgprocessclassid(Integer.parseInt(apppushclassid));
            MsgPushSettingDtl appdtl = new MsgPushSettingDtl();
            appdtl.setMsgProcessClass(appclass);
            appdtl.setMsgPushSetting(pushsetting);
            appdtl.setSubtype(appclass.getSubtype());
            appdtl.setType(appclass.getType());
            dtllist.add(appdtl);
        }
        if(!"0".equals(smspushclassid)){
            MsgProcessClass smsclass = msgProcessClassDao.findByMsgprocessclassid(Integer.parseInt(smspushclassid));
            MsgPushSettingDtl smsdtl = new MsgPushSettingDtl();
            smsdtl.setMsgProcessClass(smsclass);
            smsdtl.setMsgPushSetting(pushsetting);
            smsdtl.setSubtype(smsclass.getSubtype());
            smsdtl.setType(smsclass.getType());
            dtllist.add(smsdtl);
        }
        if(!"0".equals(emailpushclassid)){
            MsgProcessClass emailclass = msgProcessClassDao.findByMsgprocessclassid(Integer.parseInt(emailpushclassid));
            MsgPushSettingDtl emaildtl = new MsgPushSettingDtl();
            emaildtl.setMsgProcessClass(emailclass);
            emaildtl.setMsgPushSetting(pushsetting);
            emaildtl.setSubtype(emailclass.getSubtype());
            emaildtl.setType(emailclass.getType());
            dtllist.add(emaildtl);
        }
        pushsetting.setPlatform(pushsettingvo.getPlatform());
        pushsetting.setCreatetime(new Date());
        pushsetting.setMsgEventGroup(eventgroup);
        pushsetting.setMsgPushTargetDecision(targetclass);
        pushsetting.setMsgPushMethod(methodclass);
        pushsetting.setMsgPushSettingDtlList(dtllist);
        MsgPushSetting dbps = msgPushSettingDao.findByPlatformAndMsgEventGroup(pushsettingvo.getPlatform(),eventgroup);
        if(dbps!=null){
            return false;
        }
        msgPushSettingDao.save(pushsetting);
        return true;
    }

    @Transactional
    public boolean modifyPushSettingData(PushSettingVo pushsettingvo) {
        MsgPushSetting pushsetting = msgPushSettingDao.findByMsgpushsettingid(pushsettingvo.getMsgpushsettingid());
        String eventgroupid = pushsettingvo.getEventgroupname();
        String targetid = pushsettingvo.getPushtargetclass();
        String methodid = pushsettingvo.getPushmethodclass();

        MsgEventGroup eventgroup = eventgroupdao.findByMsgeventgroupid(Integer.parseInt(eventgroupid));
        MsgPushSetting dbps = msgPushSettingDao.findByPlatformAndMsgEventGroup(pushsettingvo.getPlatform(),eventgroup);
        if(dbps!=null&&dbps!=pushsetting){
            return false;
        }
        MsgProcessClass targetclass = msgProcessClassDao.findByMsgprocessclassid(Integer.parseInt(targetid));
        MsgProcessClass methodclass = msgProcessClassDao.findByMsgprocessclassid(Integer.parseInt(methodid));

        List<MsgPushSettingDtl> dtllist = new ArrayList<>();
        String apppushclassid = pushsettingvo.getApppushclass();
        String smspushclassid = pushsettingvo.getSmspushclass();
        String emailpushclassid = pushsettingvo.getEmailpushclass();

        if(!"0".equals(apppushclassid)){
            MsgProcessClass appclass = msgProcessClassDao.findByMsgprocessclassid(Integer.parseInt(apppushclassid));
            MsgPushSettingDtl appdtl = new MsgPushSettingDtl();
            appdtl.setMsgProcessClass(appclass);
            appdtl.setMsgPushSetting(pushsetting);
            appdtl.setSubtype(appclass.getSubtype());
            appdtl.setType(appclass.getType());
            dtllist.add(appdtl);
        }
        if(!"0".equals(smspushclassid)){
            MsgProcessClass smsclass = msgProcessClassDao.findByMsgprocessclassid(Integer.parseInt(smspushclassid));
            MsgPushSettingDtl smsdtl = new MsgPushSettingDtl();
            smsdtl.setMsgProcessClass(smsclass);
            smsdtl.setMsgPushSetting(pushsetting);
            smsdtl.setSubtype(smsclass.getSubtype());
            smsdtl.setType(smsclass.getType());
            dtllist.add(smsdtl);
        }
        if(!"0".equals(emailpushclassid)){
            MsgProcessClass emailclass = msgProcessClassDao.findByMsgprocessclassid(Integer.parseInt(emailpushclassid));
            MsgPushSettingDtl emaildtl = new MsgPushSettingDtl();
            emaildtl.setMsgProcessClass(emailclass);
            emaildtl.setMsgPushSetting(pushsetting);
            emaildtl.setSubtype(emailclass.getSubtype());
            emaildtl.setType(emailclass.getType());
            dtllist.add(emaildtl);
        }
        pushsetting.setPlatform(pushsettingvo.getPlatform());
        pushsetting.setMsgEventGroup(eventgroup);
        pushsetting.setMsgPushTargetDecision(targetclass);
        pushsetting.setMsgPushMethod(methodclass);
        pushsetting.getMsgPushSettingDtlList().clear();
        pushsetting.getMsgPushSettingDtlList().addAll(dtllist);

        msgPushSettingDao.save(pushsetting);
        return true;
    }
}
