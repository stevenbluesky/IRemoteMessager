package cn.com.isurpass.iremotemessager.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o)throws Exception{
        //登录不做拦截
        if(request.getRequestURI().equals(request.getContextPath()+"/login")
                ||request.getRequestURI().equals(request.getContextPath()+"/checklogin")
                ||request.getRequestURI().equals(request.getContextPath()+"/version")){
            return true;
        }
        //验证
       Object person = request.getSession().getAttribute("person");
        if(person==null){
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
    }
}
