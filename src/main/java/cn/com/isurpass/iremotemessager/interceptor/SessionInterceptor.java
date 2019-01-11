package cn.com.isurpass.iremotemessager.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

public class SessionInterceptor implements HandlerInterceptor {
    private static final HashSet<String> EXCEPT_URI = new HashSet();
    static {
        EXCEPT_URI.add("/login");
        EXCEPT_URI.add("/checklogin");
        EXCEPT_URI.add("/version");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o)throws Exception{
        //登录不做拦截
        if(EXCEPT_URI.contains(request.getRequestURI())){
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
