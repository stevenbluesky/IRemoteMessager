package cn.com.isurpass.iremotemessager.controller;

import cn.com.isurpass.iremotemessager.common.util.JsonResult;
import cn.com.isurpass.iremotemessager.common.util.PageResult;
import cn.com.isurpass.iremotemessager.domain.MsgEventGroup;
import cn.com.isurpass.iremotemessager.service.MsgPushSettingService;
import cn.com.isurpass.iremotemessager.vo.EventGroupVo;
import cn.com.isurpass.iremotemessager.vo.PushSettingVo;
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
    //deletepushsettings
    @RequestMapping(value = "/deletepushsettings")
    @ResponseBody
    public JsonResult deleteEvents(@RequestBody String[] ids) {
        try {
            pss.deletePushSettings(ids);
            return new JsonResult(1, "删除成功！");
        } catch (Exception e) {
            return new JsonResult(-1, "删除失败！");
        }
    }
    //modifypushsettingpage
    @RequestMapping(value = "/modifypushsettingpage")
    public ModelAndView toModifyEventGroupPage(@RequestParam(required = true) Integer msgpushsettingid, ModelAndView mv) {
        mv.setViewName("pushsetting/modifypushsettingpage");
        PushSettingVo pushsettingvo = pss.findByMsgpushsettingid(msgpushsettingid);
        if (pushsettingvo != null) {
            mv.addObject("pushsetting", pushsettingvo);
        }
        return mv;
    }
}
