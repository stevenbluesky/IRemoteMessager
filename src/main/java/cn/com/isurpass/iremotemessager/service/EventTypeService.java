package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.common.util.ExcelRead;
import cn.com.isurpass.iremotemessager.dao.MsgContentTemplateDao;
import cn.com.isurpass.iremotemessager.dao.MsgEventTypeDao;
import cn.com.isurpass.iremotemessager.domain.MsgContentTemplate;
import cn.com.isurpass.iremotemessager.domain.MsgEventType;
import cn.com.isurpass.iremotemessager.jms.JMSUtil;
import cn.com.isurpass.iremotemessager.vo.EventtypeVo;
import cn.com.isurpass.iremotemessager.vo.ExportMessageTemplateVo;
import cn.com.isurpass.iremotemessager.vo.MessageTemplateVo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * @author liwenxiang
 * Date:2018/11/6
 * Time:17:29
 */
@Service
public class EventTypeService {

    @Resource
    private MsgEventTypeDao eventtypedao;

    @Resource
    private MsgContentTemplateDao msgdao;

    public MsgEventType findByMsgEventTypeId(Integer msgeventtypeid){
        MsgEventType event = eventtypedao.findByMsgeventtypeid(msgeventtypeid);
        return event;
    }
    
    public void saveeventtypedata(EventtypeVo eventtype){
        MsgEventType msgEventType = new MsgEventType();
        msgEventType.setEventtypename(eventtype.getEventname());
        msgEventType.setEventcode(eventtype.getEventcode());
        msgEventType.setDecription(eventtype.getDescription());
        msgEventType.setCreatetime(new Date());

        eventtypedao.save(msgEventType);

        JMSUtil.regist(Arrays.asList(eventtype.getEventcode()));
    }

    public Map<String, Object> listEventType(Pageable pageable , EventtypeVo eventtype){
        Map<String, Object> map = new HashMap<>();
        String eventname = eventtype.getEventname() ;
        String eventcode = eventtype.getEventcode() ;
        List<MsgEventType> eventlist = new ArrayList<>();
        List<EventtypeVo> eventlistvo = new ArrayList<>();
        eventlist = eventtypedao.findByEventtypenameContainingAndEventcodeContaining(eventname,eventcode,pageable);
        long count = eventtypedao.countByEventtypenameContainingAndEventcodeContaining(eventname,eventcode);
        eventlistvo = eventlistdbtransfer2vo(eventlist);
        map.put("total",count);
        map.put("rows", eventlistvo);
        return map;
    }

    private List<EventtypeVo> eventlistdbtransfer2vo(List<MsgEventType> eventlist) {
        List<EventtypeVo> eventlistvo = new ArrayList<>();
        for(MsgEventType e : eventlist){
            EventtypeVo eventvo = new EventtypeVo();
            eventvo.setMsgeventtypeid(e.getMsgeventtypeid());
            eventvo.setEventname(e.getEventtypename());
            eventvo.setEventcode(e.getEventcode());
            eventvo.setCreatetime(e.getCreatetime());
            eventvo.setDescription(e.getDecription());
            eventlistvo.add(eventvo);
        }
        return eventlistvo;
    }

    public Map<String, Object> listMessageTemplate(Pageable pageable, Integer msgeventtypeid) {
        Map<String, Object> map = new HashMap<>();
        List<MsgContentTemplate> msglist = new ArrayList<>();
        List<MessageTemplateVo> msglistvo = new ArrayList<>();
        msglist = msgdao.findByMsgEventType_Msgeventtypeid(msgeventtypeid,pageable);
        long count = msgdao.countByMsgEventType_Msgeventtypeid(msgeventtypeid);
        msglistvo = msglistdbtransfer2vo(msglist);
        map.put("total",count);
        map.put("rows", msglistvo);
        return map;
    }

    private List<MessageTemplateVo> msglistdbtransfer2vo(List<MsgContentTemplate> msglist) {
        List<MessageTemplateVo> msglistvo = new ArrayList<>();
        for(MsgContentTemplate m : msglist){
            MessageTemplateVo msgvo = new MessageTemplateVo();
            msgvo.setMsgcontenttemplateid(m.getMsgcontenttemplateid());
            msgvo.setPlatform(m.getPlatform());
            msgvo.setType(m.getType());
            msgvo.setLanguage(m.getLanguage());
            msgvo.setContenttemplate(m.getContenttemplate());
            msgvo.setCreatetime(m.getCreatetime());
            msglistvo.add(msgvo);
        }
        return msglistvo;
    }

    public void updateeventtypedata(EventtypeVo eventtype) {
        MsgEventType event = eventtypedao.findByMsgeventtypeid(eventtype.getMsgeventtypeid());
        event.setEventtypename(eventtype.getEventname());
        event.setEventcode(eventtype.getEventcode());
        event.setDecription(eventtype.getDescription());
        eventtypedao.save(event);
    }

    @Transactional
    public void deleteEvents(String[] ids) {
        if(ids!=null&&ids.length>0){
            for(String id:ids){
                eventtypedao.deleteById(Integer.parseInt(id));
            }
        }
    }

    public int checkNameAndCode(EventtypeVo eventtype) {
        MsgEventType eventname = eventtypedao.findByEventtypename(eventtype.getEventname());
        MsgEventType eventcode = eventtypedao.findByEventcode(eventtype.getEventcode());
        if(eventname!=null){
            return -1;
        }else if(eventcode!=null){
            return -2;
        }
        return 1;
    }


    public int addmsgtemplatedata(MessageTemplateVo messagetemplatevo) {
        MsgEventType event = eventtypedao.findByMsgeventtypeid(messagetemplatevo.getMsgeventtypeid());
        MsgContentTemplate template = msgdao.findByPlatformAndMsgEventTypeAndLanguageAndType(messagetemplatevo.getPlatform(),event,messagetemplatevo.getLanguage(),messagetemplatevo.getType());
        if(template!=null){
            return -1;
        }
        MsgContentTemplate msgContentTemplate = new MsgContentTemplate();
        msgContentTemplate.setPlatform(messagetemplatevo.getPlatform());
        msgContentTemplate.setType(messagetemplatevo.getType());
        msgContentTemplate.setMsgEventType(event);
        msgContentTemplate.setCreatetime(new Date());
        msgContentTemplate.setLastupdatetime(new Date());
        msgContentTemplate.setLanguage(messagetemplatevo.getLanguage());
        msgContentTemplate.setEventcode(event.getEventcode());
        msgContentTemplate.setContenttemplate(messagetemplatevo.getContenttemplate());
        msgdao.save(msgContentTemplate);
        return 1;
    }

    public int updatemsgdata(MessageTemplateVo messageTemplateVo) {
        MsgContentTemplate msg = msgdao.findByMsgcontenttemplateid(messageTemplateVo.getMsgcontenttemplateid());
        MsgContentTemplate template = msgdao.findByPlatformAndMsgEventTypeAndLanguageAndType(messageTemplateVo.getPlatform(),msg.getMsgEventType(),messageTemplateVo.getLanguage(),messageTemplateVo.getType());
        if(template!=null&&template!=msg){
            return -1;
        }
        msg.setContenttemplate(messageTemplateVo.getContenttemplate());
        msg.setLastupdatetime(new Date());
        msg.setType(messageTemplateVo.getType());
        msg.setPlatform(messageTemplateVo.getPlatform());
        msg.setLanguage(messageTemplateVo.getLanguage());
        msgdao.save(msg);
        return 1;
    }

    public MsgContentTemplate findByMsgTemplateTemplateId(Integer msgcontenttemplateid) {
        return msgdao.findByMsgcontenttemplateid(msgcontenttemplateid);
    }

    @Transactional
    public void deleteMsgs(String[] ids) {
        if(ids!=null&&ids.length>0){
            for(String id:ids){
                msgdao.deleteByMsgcontenttemplateid(Integer.parseInt(id));
            }
        }
    }

    public List<ExportMessageTemplateVo> exportMessageTemplate(MessageTemplateVo msgvo) {
        List<MsgContentTemplate> list = msgdao.findByPlatformAndLanguage(msgvo.getPlatform(),msgvo.getLanguage());
        List<ExportMessageTemplateVo> msglistvo = new ArrayList<>();
        if(list==null||list.size()==0){
            return null;
        }
        for(MsgContentTemplate m : list){
            ExportMessageTemplateVo msg = new ExportMessageTemplateVo();
            msg.setPlatform(m.getPlatform());
            msg.setMsgeventtypeid(m.getMsgEventType().getMsgeventtypeid());
            msg.setEventcode(m.getEventcode());
            msg.setContenttemplate(m.getContenttemplate());
            msg.setLanguage(m.getLanguage());
            msg.setType(m.getType());
            msglistvo.add(msg);
        }
        return msglistvo;
    }

    public boolean msgImport(String platform, MultipartFile file) {
        try {
            ExcelRead readExcel=new ExcelRead();
            List<ExportMessageTemplateVo> customerList = new ArrayList<>();
            List<ArrayList<String>> datalist = readExcel.readExcel(file);

            if(datalist == null||datalist.size()==0){
                return false;
            }

            ArrayList<String> eventCodeList = new ArrayList<>();
            for(List l : datalist){
                String eventCode;
                MsgContentTemplate m = new MsgContentTemplate();
                m.setPlatform(Integer.parseInt((String) l.get(0)));
                m.setMsgEventType(eventtypedao.findByEventcode((String) l.get(2)));
                m.setEventcode(eventCode = (String) l.get(2));
                m.setLanguage((String) l.get(3));
                m.setType(Integer.parseInt((String) l.get(4)));
                m.setContenttemplate((String) l.get(5));
                m.setCreatetime(new Date());
                m.setLastupdatetime(new Date());
                List<MsgContentTemplate> dblist = msgdao.findByEventcodeAndPlatformAndLanguageInAndType((String) l.get(2), Integer.parseInt((String) l.get(0)), (String) l.get(3), Integer.parseInt((String) l.get(4)));
                if(dblist!=null&&dblist.size()>0){
                    continue;
                }
                msgdao.save(m);
                eventCodeList.add(eventCode);
            }

            JMSUtil.regist(eventCodeList);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
