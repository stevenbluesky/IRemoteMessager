package cn.com.isurpass.iremotemessager.controller;

import cn.com.isurpass.iremotemessager.common.util.JsonResult;
import cn.com.isurpass.iremotemessager.common.util.PageResult;
import cn.com.isurpass.iremotemessager.service.PlatformMappingService;
import cn.com.isurpass.iremotemessager.vo.PlatformMappingVo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/other")
public class OtherController {
    @Resource
    private PlatformMappingService platformMappingService;

    @RequestMapping("/mappingplatform")
    public ModelAndView mappingPlatform(ModelAndView mv) {


        mv.setViewName("other/mappingplatform");
        return mv;
    }

    @RequestMapping("/listmapping")
    @ResponseBody
    public Map<String, Object> listMapping(PageResult pr) {
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.DESC, "platformmappingid");
        return platformMappingService.listPlatformMapping(pageable);
    }

    @RequestMapping("addmapping")
    @ResponseBody
    public JsonResult addMapping(@RequestBody PlatformMappingVo vo) {
        return platformMappingService.addMapping(vo.getFromplatform(), vo.getToplatform());
    }

    @RequestMapping("/deletemapping")
    @ResponseBody
    @Transactional
    public JsonResult deleteMapping(@RequestBody String[] ids) {
        return platformMappingService.deleteMapping(ids);
    }
}
