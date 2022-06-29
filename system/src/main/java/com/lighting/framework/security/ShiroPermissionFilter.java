package com.lighting.framework.security;

import com.alibaba.fastjson.JSONObject;
import com.lighting.framework.core.BaseResponse;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ShiroPermissionFilter extends PermissionsAuthorizationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        boolean result = super.onAccessDenied(request, response);
        if (super.onAccessDenied(request, response)) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setStatus(200);
            httpServletResponse.setContentType("application/json;charset=utf-8");
            PrintWriter out = httpServletResponse.getWriter();
            BaseResponse res = new BaseResponse<>(500,"权限不足!");
            out.println(JSONObject.toJSONString(res));
            out.flush();
            out.close();
            return false;
        }
        return true;
    }
}
