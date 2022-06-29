package com.lighting.framework;

import com.lighting.framework.system.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service("sessionContext")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionContext {
    //当前用户
    private User currentUser;

    public User getCurrentUser() {
        if(currentUser == null){
            currentUser = (User) SecurityUtils.getSubject().getPrincipal();
        }
        return currentUser;
    }

    public void setCurrentAccount(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * 获取登录账号id
     * @return
     */
    public String getUserId(){
        if(getCurrentUser()==null){
            return "";
        }
        return getCurrentUser().getId();
    }

    /**
     * 获取登录用户名称
     * @return
     */
    public String getUserName(){
        if(getCurrentUser()==null){
            return "";
        }
        return getCurrentUser().getUsername();
    }

    public String getLoginName(){
        if(getCurrentUser()==null){
            return "";
        }
        return getCurrentUser().getUsername();
    }
    /**
     * 获取登录用户组织机构code
     * @return
     */
    public String getDeptCode(){
        if(getCurrentUser()==null){
            return SystemConstant.ROOT_ORG_CODE;
        }
        return getCurrentUser().getDeptCode();
    }
}
