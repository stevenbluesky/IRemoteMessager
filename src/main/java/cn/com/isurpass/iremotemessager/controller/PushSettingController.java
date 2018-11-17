package cn.com.isurpass.iremotemessager.controller;

import cn.com.isurpass.iremotemessager.common.util.PageResult;
import cn.com.isurpass.iremotemessager.service.MsgPushSettingService;
import cn.com.isurpass.iremotemessager.vo.EventGroupVo;
import cn.com.isurpass.iremotemessager.vo.PushSettingVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    //showpushsettinglist
    @RequestMapping(value = "/showpushsettinglist")
    @ResponseBody
    public Map<String, Object> showPushSettingList(PageResult pr, PushSettingVo pushsettingvo) {
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.DESC, "msgpushsettingid");
        return pss.listPushSetting(pageable,pushsettingvo);
    }
}
