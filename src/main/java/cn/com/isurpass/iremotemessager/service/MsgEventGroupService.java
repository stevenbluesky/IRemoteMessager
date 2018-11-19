package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.dao.MsgEventGroupDao;
import cn.com.isurpass.iremotemessager.dao.MsgEventGroupEventDao;
import cn.com.isurpass.iremotemessager.dao.MsgEventTypeDao;
import cn.com.isurpass.iremotemessager.dao.MsgProcessClassDao;
import cn.com.isurpass.iremotemessager.domain.*;
import cn.com.isurpass.iremotemessager.vo.EventGroupVo;
import cn.com.isurpass.iremotemessager.vo.EventtypeVo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    private MsgEventGroupDao msgEventGroupDao;
    @Resource
    private MsgEventTypeDao eventdao;

    public Map<Integer, String> listAllEventGroup(Integer platform){
        if (platform == null) {
            platform = IRemoteConstantDefine.DEFAULT_PLATFORM;
        }
        Map<Integer, String> eventGroupMap = new HashMap<>();

        List<MsgEventGroup> msgEventGroupList = msgEventGroupDao.findByPlatform(platform);
        for (MsgEventGroup msgEventGroup : msgEventGroupList) {
            eventGroupMap.put(msgEventGroup.getMsgeventgroupid(), msgEventGroup.getEventgroupname());
        }

        return eventGroupMap;
    }

    public Map<String, Object> listEventGroup(Pageable pageable, EventGroupVo eventgroup) {
        Map<String, Object> map = new HashMap<>();
        int platform = eventgroup.getPlatform()==null?888888:eventgroup.getPlatform();
        String eventgroupname = eventgroup.getEventgroupname();
        List<MsgEventGroup> eventgrouplist = new ArrayList<>();
        List<EventGroupVo> eventgrouplistvo = new ArrayList<>();
        long count = 0 ;
        if(platform==888888){
            eventgrouplist = msgEventGroupDao.findByEventgroupnameContaining(eventgroupname,pageable);
            count = msgEventGroupDao.countByEventgroupnameContaining(eventgroupname);
        }else {
            eventgrouplist = msgEventGroupDao.findByPlatformAndEventgroupnameContaining(platform, eventgroupname, pageable);
            count = msgEventGroupDao.countByPlatformAndEventgroupnameContaining(platform, eventgroupname);
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
        MsgEventGroup meg = msgEventGroupDao.findByPlatformAndEventgroupname(platform,eventgroupname);
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
        msgEventGroupDao.save(msgEventGroup);
    }

    public MsgEventGroup findByMsgEventGroupId(Integer msgeventgroupid) {
        return msgEventGroupDao.findByMsgeventgroupid(msgeventgroupid);
    }

    public void updateeventtypedata(EventGroupVo eventgroup) {
        MsgEventGroup dbgroup = msgEventGroupDao.findByMsgeventgroupid(eventgroup.getMsgeventgroupid());
        dbgroup.setPlatform(eventgroup.getPlatform());
        dbgroup.setEventgroupname(eventgroup.getEventgroupname());
        dbgroup.setDecription(eventgroup.getDescription());
        msgEventGroupDao.save(dbgroup);
    }
    @Transactional
    public void deleteEventGroups(String[] ids) {
        if(ids!=null&&ids.length>0){
            for(String id:ids){
                msgEventGroupDao.deleteByMsgeventgroupid(Integer.parseInt(id));
            }
        }
    }
    public Map<String, Object> listEventGroupEvent(Pageable pageable, Integer msgeventgroupid) {
        MsgEventGroup eventgroup = msgEventGroupDao.findByMsgeventgroupid(msgeventgroupid);

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
        MsgEventGroup eventgroup = msgEventGroupDao.findByMsgeventgroupid(msgeventgroupid);
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
        MsgEventGroup eventgroup = msgEventGroupDao.findByMsgeventgroupid(msgeventgroupid);
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
