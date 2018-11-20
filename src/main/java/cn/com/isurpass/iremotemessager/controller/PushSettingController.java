package cn.com.isurpass.iremotemessager.controller;

import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
import cn.com.isurpass.iremotemessager.common.util.JsonResult;
import cn.com.isurpass.iremotemessager.common.util.PageResult;
import cn.com.isurpass.iremotemessager.domain.MsgEventGroup;
import cn.com.isurpass.iremotemessager.service.MsgEventGroupService;
import cn.com.isurpass.iremotemessager.service.MsgProcessClassService;
import cn.com.isurpass.iremotemessager.service.MsgPushSettingService;
import cn.com.isurpass.iremotemessager.vo.EventGroupVo;
import cn.com.isurpass.iremotemessager.vo.MessageTemplateVo;
import cn.com.isurpass.iremotemessager.vo.PushSettingVo;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author liwenxiang
 * Date:2018/11/17
 * Time:13:39
 */
@Controller
@RequestMapping(value = "/pushsetting")
public class PushSettingController {

    @Autowired
    private MsgPushSettingService pss;
    @Resource
    private MsgEventGroupService msgEventGroupService;
    @Resource
    private MsgProcessClassService msgProcessClassService;

    @RequestMapping(value = "/pushsettinglistpage")
    public ModelAndView toPushsettingListPage(ModelAndView mv) {
        mv.setViewName("pushsetting/pushsettinglist");
        return mv;
    }
    @RequestMapping(value = "/addpushsettingpage")
    public ModelAndView toAddPushsettingPage(ModelAndView mv) {
        mv.setViewName("pushsetting/addpushsetting");
        return mv;
    }

    @RequestMapping(value = "/showpushsettinglist")
    @ResponseBody
    public Map<String, Object> showPushSettingList(PageResult pr, PushSettingVo pushsettingvo) {
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.DESC, "msgpushsettingid");
        return pss.listPushSetting(pageable,pushsettingvo);
    }

    @RequestMapping("listalleventgroup")
    @ResponseBody
    public String listEventGroup(Integer platform) {
        Map<String, String> stringStringMap = msgEventGroupService.listAllEventGroup(platform);
        JSONObject json = net.sf.json.JSONObject.fromObject(stringStringMap);
        return json.toString();
    }

    @RequestMapping("listprocessorclassbytype")
    @ResponseBody
    public String listProcessorClassByType(Integer type, Integer subType){
        Map<String, String> stringStringMap = msgProcessClassService.listProcessClass(type, subType);
        JSONObject json = net.sf.json.JSONObject.fromObject(stringStringMap);
        return json.toString();
    }
    //deletepushsettings
    @RequestMapping(value = "/deletepushsettings")
    @ResponseBody
    public JsonResult deleteEvents(@RequestBody String[] ids) {
        try {
            pss.deletePushSettings(ids);
            return new JsonResult(1, "删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(-1, "删除失败！");
        }
    }
    //modifypushsettingpage
    @RequestMapping(value = "/modifypushsettingpage")
    public ModelAndView toModifyEventGroupPage(@RequestParam(required = true) Integer msgpushsettingid, ModelAndView mv) {
        mv.setViewName("pushsetting/modifypushsettingpage");
        PushSettingVo pushsettingvo = pss.findByMsgpushsettingid(msgpushsettingid);
        if (pushsettingvo != null) {
            mv.addObject("pushsettingvo", pushsettingvo);
        }
        return mv;
    }
    //addpushsettingdata
    @RequestMapping(value = "/addpushsettingdata")
    @ResponseBody
    public JsonResult addPushSettingData(PushSettingVo pushsettingvo, ModelAndView mv) {
        try {
            if(StringUtils.isBlank(pushsettingvo.getEventgroupname())){
                return new JsonResult(-1, "事件组必选！");
            }
            boolean b = pss.addPushSettingData(pushsettingvo);
            if(!b){
                return new JsonResult(-1, "厂商和事件组不唯一！");
            }
            return new JsonResult(1, "新增成功！");
        } catch (Exception e) {
            return new JsonResult(-1, "新增失败！");
        }
    }
    //modifypushsettingdata
    @RequestMapping(value = "/modifypushsettingdata")
    @ResponseBody
    public JsonResult modifyPushSettingData(PushSettingVo pushsettingvo, ModelAndView mv) {
        try {
            boolean b = pss.modifyPushSettingData(pushsettingvo);
            if(!b){
                return new JsonResult(-1, "厂商和事件组不唯一！");
            }
            return new JsonResult(1, "修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(-1, "修改失败！");
        }
    }
}