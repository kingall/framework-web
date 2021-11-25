package com.lighting.framework.security;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;


public class SystemLogoutFilter extends LogoutFilter {


    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        //这里退出时进行需要清除的数据
        Subject subject = getSubject(request, response);
       // String redirect = "/api/login";
        try {
            subject.logout();
           /* HttpServletRequest httpServletRequest = (HttpServletRequest)request;
            issueRedirect(request,response,redirect);*/
            //设置返回状态，然后前台负责跳转
            HttpServletResponse httpServletResponse = (HttpServletResponse)response;
            httpServletResponse.setHeader("code","-9");
            httpServletResponse.setHeader("msg", URLEncoder.encode("注销成功", "UTF-8"));
            httpServletResponse.setHeader("Access-Control-Expose-Headers", "code,msg");
        }catch (Exception e){
            e.printStackTrace();
        }
        //直接false表示不执行后续的过滤器，直接跳转到登录页面
        return false;
    }
}
