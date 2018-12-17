package cn.com.isurpass.iremotemessager.controller;

import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
import cn.com.isurpass.iremotemessager.common.util.JsonResult;
import cn.com.isurpass.iremotemessager.common.util.PageResult;
import cn.com.isurpass.iremotemessager.domain.MsgEventGroup;
import cn.com.isurpass.iremotemessager.service.MsgEventGroupService;
import cn.com.isurpass.iremotemessager.vo.EventGroupVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

/**
 * @author liwenxiang
 * Date:2018/11/6
 * Time:15:59
 */
@Controller
@RequestMapping(value = "/eventgroup")
public class EventGroupController {
    private MsgEventGroupService egs;
    @Autowired
    public void setEgs(MsgEventGroupService egs) {
        this.egs = egs;
    }

    @RequestMapping(value = "/eventgrouplistpage")
    public ModelAndView toeventlistpage(ModelAndView mv) {
        mv.setViewName("eventgroup/eventgrouplist");
        return mv;
    }


    @RequestMapping(value = "/showeventgrouplist")
    @ResponseBody
    public Map<String, Object> showeventlist(PageResult pr, EventGroupVo eventgroup) {
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.DESC, "msgeventgroupid");
        return egs.listEventGroup(pageable, eventgroup);
    }

    @RequestMapping(value = "/addeventgrouppage")
    public ModelAndView toaddeventgrouppage(ModelAndView mv) {
        mv.setViewName("eventgroup/addeventgroup");
        return mv;
    }

    @RequestMapping(value = "/addeventgroupdata")
    public ModelAndView saveeventgroupdata(EventGroupVo eventgroup, ModelAndView mv) {
        mv.setViewName("eventgroup/addeventgroup");
        if (StringUtils.isBlank(eventgroup.getEventgroupname())) {
            mv.addObject("msg", "事件组名称不能为空！");
            return mv;
        }
        try {
            int checkresult = egs.checkNameAndPlatform(eventgroup);
            if (checkresult == 1) {
                egs.saveeventgroupdata(eventgroup);
                mv.addObject("success", 1);
                mv.addObject("msg", "事件组\"" + eventgroup.getEventgroupname() + "\"添加成功！");
            }else {
                mv.addObject("msg", "事件组\"" + eventgroup.getEventgroupname() + "\"重复！");
            }
        } catch (Exception e) {
            mv.addObject("msg", "事件组\"" + eventgroup.getEventgroupname() + "\"添加失败！");
            return mv;
        }
        return mv;
    }
    @RequestMapping(value = "/modifyeventgrouppage")
    public ModelAndView toModifyEventGroupPage(Integer msgeventgroupid, ModelAndView mv) {
        mv.setViewName("eventgroup/modifyeventgroup");
        MsgEventGroup eventg = egs.findByMsgEventGroupId(msgeventgroupid);
        if (eventg != null) {
            mv.addObject("eventgroup", eventg);
        }
        return mv;
    }
    @RequestMapping(value = "/modifyeventgroupdata")
    @ResponseBody
    public JsonResult modifyeventdata(EventGroupVo eventgroup) {
        if (IRemoteUtils.isBlank(eventgroup.getMsgeventgroupid())) {
            return new JsonResult(-1, "修改失败！");
        } else if (StringUtils.isBlank(eventgroup.getEventgroupname())) {
            return new JsonResult(-1, "事件组名称不能为空！");
        }
        try {
            egs.updateeventtypedata(eventgroup);
            return new JsonResult(1, "修改成功！");
        } catch (Exception e) {
            return new JsonResult(-1, "修改失败！");
        }
    }

    @RequestMapping(value = "/deleteeventgroups")
    @ResponseBody
    public JsonResult deleteEventGroups(@RequestBody String[] ids) {
        try {
            egs.deleteEventGroups(ids);
            return new JsonResult(1, "删除成功！");
        } catch (Exception e) {
            return new JsonResult(-1, "删除失败！");
        }
    }
    @RequestMapping(value = "/eventgroupevent")
    public ModelAndView toEventGroupEventPage(Integer msgeventgroupid, ModelAndView mv) {
        MsgEventGroup eventg = egs.findByMsgEventGroupId(msgeventgroupid);
        if (eventg != null) {
            mv.addObject("eventgroup", eventg);
        }
        mv.setViewName("eventgroup/eventgroupeventpage");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/showeventgroupeventlist")
    public Map<String, Object> showEventGroupEventList(PageResult pr, Integer msgeventgroupid) {
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.DESC, "msgeventgroupid");
        return egs.listEventGroupEvent(pageable, msgeventgroupid);
    }

    @ResponseBody
    @RequestMapping(value = "/showaddeventgroupeventlist")
    public Map<String, Object> showAddEventGroupEventList(PageResult pr, String eventname,String eventcode,Integer msgeventgroupid) {
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.DESC, "msgeventgroupid");
        return egs.listAddEventGroupEvent(pageable, msgeventgroupid,eventname,eventcode);
    }

    @RequestMapping(value = "/addeventgroupeventpage")
    public ModelAndView toAddEventGroupEventPage(Integer msgeventgroupid, ModelAndView mv) {
        MsgEventGroup eventg = egs.findByMsgEventGroupId(msgeventgroupid);
        if (eventg != null) {
            mv.addObject("eventgroup", eventg);
        }
        mv.setViewName("eventgroup/addeventgroupeventpage");
        return mv;
    }

    @RequestMapping(value = "/addeventgroupeventtogroup")
    @ResponseBody
    public JsonResult addEventGroupEventToGroup(@RequestBody String[] ids,Integer msgeventgroupid) {//msgeventtypeid
        try {
            egs.addEventGroupEventToGroup(ids,msgeventgroupid);
            return new JsonResult(1, "新增成功！");
        } catch (Exception e) {
            return new JsonResult(-1, "新增失败！");
        }
    }
    @RequestMapping(value = "/deleteeventgroupevents")
    @ResponseBody
    public JsonResult deleteEventGroupEvents(@RequestBody String[] ids) {
        try {
            egs.deleteEventGroupEvents(ids);
            return new JsonResult(1, "删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(-1, "删除失败！");
        }
    }
}
