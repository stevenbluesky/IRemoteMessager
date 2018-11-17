package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.dao.MsgEventGroupDao;
import cn.com.isurpass.iremotemessager.dao.MsgEventGroupEventDao;
import cn.com.isurpass.iremotemessager.dao.MsgEventTypeDao;
import cn.com.isurpass.iremotemessager.dao.MsgProcessClassDao;
import cn.com.isurpass.iremotemessager.domain.*;
import cn.com.isurpass.iremotemessager.vo.EventGroupVo;
import cn.com.isurpass.iremotemessager.vo.EventtypeVo;
import cn.com.isurpass.iremotemessager.vo.MessageTemplateVo;
import com.google.common.collect.Lists;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.*;

@Service
public class MsgEventGroupService {
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

    public MsgEventGroupEvent findByEventCodeAndPlatform(String eventCode, Integer platform) {
        return msgEventGroupEventDao.findByMsgEventType_EventcodeAndPlatform(eventCode, platform);
    }

    public MsgProcessClass findMsgPushTargetDecision(String eventCode, Integer platform) {
        Integer pushTargetDecisionId = msgEventGroupEventDao.findMsgPushTargetDecisionId(eventCode, platform);
        if (pushTargetDecisionId == null) {
            return null;
        }

        return msgProcessClassDao.findById(pushTargetDecisionId).orElse(null);
    }

    public MsgProcessClass findMsgPushMethod(String eventCode, Integer platform) {
        Integer pushTargetDecisionId = msgEventGroupEventDao.findMsgPushMethodId(eventCode, platform);
        if (pushTargetDecisionId == null) {
            return null;
        }

        return msgProcessClassDao.findById(pushTargetDecisionId).orElse(null);
    }

    public String findMsgPushTargetDecisionClassName(String eventCode, Integer platform) {
        MsgProcessClass msgPushTargetDecision = findMsgPushTargetDecision(eventCode, platform);
        if (msgPushTargetDecision == null) {
            String className = msgDefaultProcessClassService.findDefaultTargetDesicionName(platform, eventCode);
            if (className == null) {
                className = msgDefaultProcessClassService.findDefaultTargetDesicionName(IRemoteConstantDefine.DEFAULT_PLATFORM, eventCode);
                if (className == null) {
                    className = msgDefaultProcessClassService.findDefaultTargetDesicionName(platform, IRemoteConstantDefine.DEFAULT_EVENT_CODE);
                    if (className == null) {
                        className = msgDefaultProcessClassService.findDefaultTargetDesicionName(IRemoteConstantDefine.DEFAULT_PLATFORM, IRemoteConstantDefine.DEFAULT_EVENT_CODE);
                    }
                }
            }
            return className;
        }

        return  msgPushTargetDecision.getClassname();
    }

    public String findMsgPushMethodClassName(String eventCode, Integer platform) {
        MsgProcessClass pushMethod = findMsgPushMethod(eventCode, platform);
        if (pushMethod == null) {
            String className = msgDefaultProcessClassService.findDefaultPushMethod(platform, eventCode);
            if (className == null) {
                className = msgDefaultProcessClassService.findDefaultPushMethod(IRemoteConstantDefine.DEFAULT_PLATFORM, eventCode);
                if (className == null) {
                    className = msgDefaultProcessClassService.findDefaultPushMethod(platform, IRemoteConstantDefine.DEFAULT_EVENT_CODE);
                    if (className == null) {
                        className = msgDefaultProcessClassService.findDefaultPushMethod(IRemoteConstantDefine.DEFAULT_PLATFORM, IRemoteConstantDefine.DEFAULT_EVENT_CODE);
                    }
                }
            }
            return className;
        }

        return  pushMethod.getClassname();
    }

    public Map<String, Object> listEventGroup(Pageable pageable, EventGroupVo eventgroup) {
        Map<String, Object> map = new HashMap<>();
        int platform = eventgroup.getPlatform()==null?0:eventgroup.getPlatform();
        String eventgroupname = eventgroup.getEventgroupname();
        List<MsgEventGroup> eventgrouplist = new ArrayList<>();
        List<EventGroupVo> eventgrouplistvo = new ArrayList<>();
        long count = 0 ;
        if(platform==0){
            eventgrouplist = eventgroupdao.findByEventgroupnameContaining(eventgroupname,pageable);
            count = eventgroupdao.countByEventgroupnameContaining(eventgroupname);
        }else {
            eventgrouplist = eventgroupdao.findByPlatformAndEventgroupnameContaining(platform, eventgroupname, pageable);
            count = eventgroupdao.countByPlatformAndEventgroupnameContaining(platform, eventgroupname);
        }
        eventgrouplistvo =  eventgrouplistdbtransfer2vo(eventgrouplist);
        map.put("total",count);
        map.put("rows", eventgrouplistvo);
        return map;
    }

    private List<EventGroupVo> eventgrouplistdbtransfer2vo(List<MsgEventGroup> eventgrouplist) {
        List<EventGroupVo> eventgrouplistvo = new ArrayList<>();
        for(MsgEventGroup e : eventgrouplist){
            EventGroupVo eventgroupvo = new EventGroupVo();
            eventgroupvo.setMsgeventgroupid(e.getMsgeventgroupid());
            eventgroupvo.setPlatform(e.getPlatform());
            eventgroupvo.setEventgroupname(e.getEventgroupname());
            eventgroupvo.setDescription(e.getDecription());
            StringBuffer sb = new StringBuffer();
            for(MsgEventGroupEvent ee : e.getMsgEventGroupEvents()){
                sb.append(ee.getEventcode()+", ");
            }
            String eventcodestr = sb.toString();
            eventgroupvo.setEventliststr(eventcodestr.contains(", ")==true?eventcodestr.substring(0,eventcodestr.lastIndexOf(", ")):eventcodestr);
            eventgrouplistvo.add(eventgroupvo);
        }
        return eventgrouplistvo;
    }

    public int checkNameAndPlatform(EventGroupVo eventgroup) {
        int platform = eventgroup.getPlatform()==null?0:eventgroup.getPlatform();
        String eventgroupname = eventgroup.getEventgroupname();
        MsgEventGroup meg = eventgroupdao.findByPlatformAndEventgroupname(platform,eventgroupname);
        if(meg==null){
            return 1;
        }
        return -1;
    }

    public void saveeventgroupdata(EventGroupVo eventgroup) {
        MsgEventGroup msgEventGroup = new MsgEventGroup();
        msgEventGroup.setPlatform(eventgroup.getPlatform()==null?0:eventgroup.getPlatform());
        msgEventGroup.setEventgroupname(eventgroup.getEventgroupname());
        msgEventGroup.setDecription(eventgroup.getDescription());
        msgEventGroup.setCreatetime(new Date());
        eventgroupdao.save(msgEventGroup);
    }

    public MsgEventGroup findByMsgEventGroupId(Integer msgeventgroupid) {
        return eventgroupdao.findByMsgeventgroupid(msgeventgroupid);
    }

    public void updateeventtypedata(EventGroupVo eventgroup) {
        MsgEventGroup dbgroup = eventgroupdao.findByMsgeventgroupid(eventgroup.getMsgeventgroupid());
        dbgroup.setPlatform(eventgroup.getPlatform());
        dbgroup.setEventgroupname(eventgroup.getEventgroupname());
        dbgroup.setDecription(eventgroup.getDescription());
        eventgroupdao.save(dbgroup);
    }
    @Transactional
    public void deleteEventGroups(String[] ids) {
        if(ids!=null&&ids.length>0){
            for(String id:ids){
                eventgroupdao.deleteByMsgeventgroupid(Integer.parseInt(id));
            }
        }
    }
    public Map<String, Object> listEventGroupEvent(Pageable pageable, Integer msgeventgroupid) {
        MsgEventGroup eventgroup = eventgroupdao.findByMsgeventgroupid(msgeventgroupid);

        List<EventtypeVo> resultlist = new ArrayList<>();
        for(MsgEventGroupEvent m : eventgroup.getMsgEventGroupEvents()){
            EventtypeVo event = new EventtypeVo();
            MsgEventType et = m.getMsgEventType();
            event.setMsgeventtypeid(et.getMsgeventtypeid());
            event.setEventname(et.getEventtypename());
            event.setEventcode(et.getEventcode());
            event.setDescription(et.getDecription());
            resultlist.add(event);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total",resultlist.size());
        map.put("rows", resultlist);
        return map;
    }

    public void deleteEventGroupEvents(String[] ids) {
        if(ids!=null&&ids.length>0){
            for(String id:ids){
                MsgEventType event = eventdao.findByMsgeventtypeid(Integer.parseInt(id));
                MsgEventGroupEvent ege = msgEventGroupEventDao.findByMsgEventType(event);
                msgEventGroupEventDao.delete(ege);
            }
        }
    }
    public Map<String, Object> listAddEventGroupEvent(Pageable pageable, Integer msgeventgroupid,String eventname,String eventcode) {
        //Iterable<MsgEventType> all = eventdao.findAll();
        List<MsgEventType> alllist = eventdao.findByEventtypenameContainingAndEventcodeContaining(eventname,eventcode);
        MsgEventGroup eventgroup = eventgroupdao.findByMsgeventgroupid(msgeventgroupid);
        List<Integer> dbidlist = new ArrayList<>();

        for(MsgEventGroupEvent e:eventgroup.getMsgEventGroupEvents()){
            dbidlist.add(e.getMsgEventType().getMsgeventtypeid());
        }
        Iterator<MsgEventType> iterator = alllist.iterator();
        while (iterator.hasNext()) {
            MsgEventType next = iterator.next();
            if (dbidlist.contains(next.getMsgeventtypeid())) {
                iterator.remove();
            }
        }
        List<EventtypeVo> resultlist = new ArrayList<>();
        for(MsgEventType m : alllist){
            EventtypeVo event = new EventtypeVo();
            event.setMsgeventtypeid(m.getMsgeventtypeid());
            event.setEventname(m.getEventtypename());
            event.setEventcode(m.getEventcode());
            event.setDescription(m.getDecription());
            event.setCreatetime(m.getCreatetime());
            resultlist.add(event);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total",resultlist.size());
        map.put("rows", resultlist);
        return map;
    }

    public void addEventGroupEventToGroup(String[] ids, Integer msgeventgroupid) {
        MsgEventGroup eventgroup = eventgroupdao.findByMsgeventgroupid(msgeventgroupid);
        if(ids!=null&&ids.length>0){
            for(String id:ids){
                MsgEventGroupEvent eventge = new MsgEventGroupEvent();
                MsgEventType event = eventdao.findByMsgeventtypeid(Integer.parseInt(id));
                eventge.setPlatform(eventgroup.getPlatform());
                eventge.setMsgEventType(event);
                eventge.setMsgEventGroup(eventgroup);
                eventge.setCreatetime(new Date());
                eventge.setEventcode(event.getEventcode());
                msgEventGroupEventDao.save(eventge);
            }
        }
    }
}
