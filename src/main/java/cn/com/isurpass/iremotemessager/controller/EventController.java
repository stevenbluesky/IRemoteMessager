package cn.com.isurpass.iremotemessager.controller;

import cn.com.isurpass.iremotemessager.common.util.ExportExcelUtil;
import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
import cn.com.isurpass.iremotemessager.common.util.JsonResult;
import cn.com.isurpass.iremotemessager.common.util.PageResult;
import cn.com.isurpass.iremotemessager.domain.MsgContentTemplate;
import cn.com.isurpass.iremotemessager.domain.MsgEventType;
import cn.com.isurpass.iremotemessager.service.EventTypeService;
import cn.com.isurpass.iremotemessager.vo.EventtypeVo;
import cn.com.isurpass.iremotemessager.vo.ExportMessageTemplateVo;
import cn.com.isurpass.iremotemessager.vo.MessageTemplateVo;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author liwenxiang
 * Date:2018/11/6
 * Time:16:00
 */
@Controller
@RequestMapping(value = "/event")
public class EventController {

    private EventTypeService eventtypeservice;

    @Autowired
    public void setEventtypeservice(EventTypeService eventtypeservice) {
        this.eventtypeservice = eventtypeservice;
    }

    @RequestMapping(value = "/addeventpage")
    public ModelAndView toaddeventpage(ModelAndView mv) {
        mv.setViewName("event/addevent");
        return mv;
    }

    @RequestMapping(value = "/addmsgtemplatepage")
    public ModelAndView toaddmsgtemplatepage(Integer msgeventtypeid, ModelAndView mv) {
        MsgEventType event = eventtypeservice.findByMsgEventTypeId(msgeventtypeid);
        if (event != null) {
            mv.addObject("event", event);
        }
        mv.setViewName("event/addmsgtemplate");
        return mv;
    }

    @RequestMapping(value = "/modifyeventpage")
    public ModelAndView toModifyEventPage(Integer msgeventtypeid, ModelAndView mv) {
        mv.setViewName("event/modifyevent");
        MsgEventType event = eventtypeservice.findByMsgEventTypeId(msgeventtypeid);
        if (event != null) {
            mv.addObject("event", event);
        }
        return mv;
    }

    @RequestMapping(value = "/messagetemplatepage")
    public ModelAndView toMessageTemplatePage(Integer msgeventtypeid, ModelAndView mv) {
        MsgEventType event = eventtypeservice.findByMsgEventTypeId(msgeventtypeid);
        if (event != null) {
            mv.addObject("event", event);
        }
        mv.setViewName("event/messagetemplatepage");
        return mv;
    }

    @RequestMapping(value = "/exportmsgtemplatepage")
    public ModelAndView toExportMessageTemplatePage(ModelAndView mv) {
        mv.setViewName("event/exportmsgtemplate");
        return mv;
    }
    @RequestMapping(value = "/importmsgtemplatepage")
    public ModelAndView toImportMessageTemplatePage(ModelAndView mv) {
        mv.setViewName("event/importmsgtemplate");
        return mv;
    }
    @RequestMapping(value = "/deleteevents")
    @ResponseBody
    public JsonResult deleteEvents(@RequestBody String[] ids) {
        try {
            eventtypeservice.deleteEvents(ids);
            return new JsonResult(1, "删除成功！");
        } catch (Exception e) {
            return new JsonResult(-1, "删除失败！");
        }
    }

    @RequestMapping(value = "/deletemsgs")
    @ResponseBody
    public JsonResult deleteMsgs(@RequestBody String[] ids) {
        try {
            eventtypeservice.deleteMsgs(ids);
            return new JsonResult(1, "删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(-1, "删除失败！");
        }
    }

    @RequestMapping(value = "/exportmessagetemplate")
    @ResponseBody
    public String exportMessageTemplate(MessageTemplateVo msgvo,HttpServletResponse response) {
        List<ExportMessageTemplateVo> dataset = eventtypeservice.exportMessageTemplate(msgvo);
        if(dataset==null||dataset.size()==0){
            return "无数据！";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        String sheetName = "消息模板_"+msgvo.getPlatform()+"_"+msgvo.getLanguage();
        String titleName = "消息模板_"+msgvo.getPlatform()+"_"+msgvo.getLanguage()+sdf.format(new Date());
        String fileName = "消息模板_"+msgvo.getPlatform()+"_"+msgvo.getLanguage()+sdf.format(new Date());
        int columnNumber = 5;
        int[] columnWidth = { 20, 30,10,10,80 };
        String[] columnName = { "platform", "eventcode" ,"language","type","contenttemplate"};
        try {
            HSSFWorkbook wb = new ExportExcelUtil().exportNoResponse(sheetName, titleName, columnNumber, columnWidth, columnName, dataset);
            if(wb==null){
                return "系统错误！";
            }
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "导出成功！";
    }

    /**
     * 发送响应流方法
     */
    private void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName+".xls");
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @RequestMapping(value = "/eventlistpage")
    public ModelAndView toeventlistpage(ModelAndView mv) {
        mv.setViewName("event/eventlist");
        return mv;
    }

    @RequestMapping(value = "/showeventlist")
    @ResponseBody
    public Map<String, Object> showeventlist(PageResult pr, EventtypeVo eventtype) {
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.DESC, "msgeventtypeid");
        return eventtypeservice.listEventType(pageable, eventtype);
    }

    @RequestMapping(value = "/showmessagetemplatelist")
    @ResponseBody
    public Map<String, Object> showmessagetemplatelist(PageResult pr, Integer msgeventtypeid) {
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.DESC, "msgcontenttemplateid");
        return eventtypeservice.listMessageTemplate(pageable, msgeventtypeid);
    }

    @RequestMapping(value = "/addeventdata")
    public ModelAndView saveeventdata(EventtypeVo eventtype, ModelAndView mv) {
        mv.setViewName("event/addevent");
        if (StringUtils.isBlank(eventtype.getEventname()) || StringUtils.isBlank(eventtype.getEventcode())) {
            mv.addObject("msg", "事件名称和事件代码不能为空！");
            return mv;
        }
        try {
            int checkresult = eventtypeservice.checkNameAndCode(eventtype);
            if (checkresult == 1) {
                eventtypeservice.saveeventtypedata(eventtype);
                mv.addObject("success", 1);
                mv.addObject("msg", "事件\"" + eventtype.getEventname() + "\"添加成功！");
            } else if (checkresult == -1) {
                mv.addObject("msg", "事件名称\"" + eventtype.getEventname() + "\"重复！");
            } else {
                mv.addObject("msg", "事件代码\"" + eventtype.getEventcode() + "\"重复！");
            }
        } catch (Exception e) {
            mv.addObject("msg", "事件\"" + eventtype.getEventname() + "\"添加失败！");
            return mv;
        }
        return mv;
    }

    @RequestMapping(value = "/modifyeventdata")
    @ResponseBody
    public JsonResult modifyeventdata(EventtypeVo eventtype) {
        if (IRemoteUtils.isBlank(eventtype.getMsgeventtypeid())) {
            return new JsonResult(-1, "修改失败！");
        } else if (StringUtils.isBlank(eventtype.getEventname()) || StringUtils.isBlank(eventtype.getEventcode())) {
            return new JsonResult(-1, "事件名称和事件代码不能为空！");
        }
        try {
            eventtypeservice.updateeventtypedata(eventtype);
            return new JsonResult(1, "修改成功！");
        } catch (Exception e) {
            return new JsonResult(-1, "修改失败！");
        }
    }

    @RequestMapping(value = "/modifymsgtemplatedata")
    @ResponseBody
    public JsonResult modifymsgtemplatedata(MessageTemplateVo msgvo) {
        if (IRemoteUtils.isBlank(msgvo.getMsgeventtypeid()) || IRemoteUtils.isBlank(msgvo.getMsgcontenttemplateid())) {
            return new JsonResult(-1, "修改失败！");
        } else if (StringUtils.isBlank(msgvo.getContenttemplate())) {
            return new JsonResult(-1, "消息模板不能为空！");
        }
        try {
            int result = eventtypeservice.updatemsgdata(msgvo);
            if(result==-1){
                return new JsonResult(-1, "事件+厂商+语言+类型不唯一！");
            }
            return new JsonResult(1, "修改成功！");
        } catch (Exception e) {
            return new JsonResult(-1, "修改失败！");
        }
    }

    @RequestMapping(value = "/modifymsgpage")
    public ModelAndView modifymsgpage(Integer msgcontenttemplateid, ModelAndView mv) {
        MsgContentTemplate msg = eventtypeservice.findByMsgTemplateTemplateId(msgcontenttemplateid);
        if (msg != null) {
            mv.addObject("msg", msg);
            MsgEventType event = eventtypeservice.findByMsgEventTypeId(msg.getMsgEventType().getMsgeventtypeid());
            if (event != null) {
                mv.addObject("event", event);
            }
        }
        mv.setViewName("event/modifymsgtemplate");
        return mv;
    }

    @RequestMapping(value = "/addmsgtemplatedata")
    @ResponseBody
    public JsonResult addmsgtemplatedata(MessageTemplateVo messagetemplatevo) {
        if (IRemoteUtils.isBlank(messagetemplatevo.getMsgeventtypeid())) {
            return new JsonResult(-1, "新增失败！");
        } else if (StringUtils.isBlank(messagetemplatevo.getContenttemplate())) {
            return new JsonResult(-1, "模板内容不能为空！");
        }
        try {
            int result = eventtypeservice.addmsgtemplatedata(messagetemplatevo);
            if(result==-1){
                return new JsonResult(-1, "事件+厂商+语言+类型不唯一！");
            }
            return new JsonResult(1, "新增成功！");
        } catch (Exception e) {
            return new JsonResult(-1, "新增失败！");
        }
    }
    @RequestMapping(value = "importmsgtemplatefile", method = RequestMethod.POST)
    public ModelAndView batchimport(@RequestParam(value="filename") MultipartFile file,String platform, ModelAndView mv){
        if(file==null||file.getSize()==0) {
            mv.setViewName("failed");
            return mv;
        }
        String originalFilename = file.getOriginalFilename();
        boolean b = eventtypeservice.msgImport(platform,file);
        if(b){
            mv.setViewName("event/eventlist");
            mv.addObject("importresult", originalFilename+"上传解析完毕！");
        }else{
            mv.setViewName("failed");
        }
        return mv;
    }
}
