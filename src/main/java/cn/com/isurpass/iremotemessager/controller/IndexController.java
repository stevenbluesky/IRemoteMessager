package cn.com.isurpass.iremotemessager.controller;

import cn.com.isurpass.iremotemessager.domain.PhoneUser;
import cn.com.isurpass.iremotemessager.service.PhoneUserAttributeService;
import cn.com.isurpass.iremotemessager.service.PhoneUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liwenxiang
 * Date:2018/11/6
 * Time:14:25
 */
@Controller
public class IndexController {
    @Autowired
    private PhoneUserService pus;
    @Autowired
    private PhoneUserAttributeService puas;
    @RequestMapping(value = "/index")
    public ModelAndView toDemo(ModelAndView mv) {
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping(value = "/hello")
    public ModelAndView toHello(ModelAndView mv) {
        mv.setViewName("hello");
        return mv;
    }
    @RequestMapping(value = "/login")
    public ModelAndView login(ModelAndView mv) {
        mv.setViewName("login");
        return mv;
    }
    @RequestMapping("/")
    public String tologin(){
        return "login";
    }

    @ResponseBody
    @RequestMapping("checklogin")
    public String checklogin(String account,String password,String platform, HttpServletRequest request){
        try {
            PhoneUser admin = pus.checkLoginData(account,password,platform);
            if(admin==null){
                return "unregistered";
            }else{
                request.getSession().setAttribute("person",admin);
                return "success";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
    }
    @RequestMapping("logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("person");
        return "login";
    }
}
