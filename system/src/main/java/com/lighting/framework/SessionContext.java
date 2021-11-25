package com.lighting.framework;

import com.lighting.framework.system.domain.Account;
import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service("sessionContext")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionContext {
    //当前用户
    private Account currentAccount;

    public Account getCurrentAccount() {
        if(currentAccount == null){
            currentAccount = (Account) SecurityUtils.getSubject().getPrincipal();
        }
        return currentAccount;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    /**
     * 获取登录账号id
     * @return
     */
    public String getAccountId(){
        if(getCurrentAccount()==null){
            return "";
        }
        return getCurrentAccount().getId();
    }

    /**
     * 获取登录用户名称
     * @return
     */
    public String getUserName(){
        if(getCurrentAccount()==null){
            return "";
        }
        return getCurrentAccount().getUsername();
    }
    /**
     * 获取登录用户组织机构code
     * @return
     */
    public String getOrgCode(){
        if(getCurrentAccount()==null){
            return SystemConstant.ROOT_ORG_CODE;
        }
        return getCurrentAccount().getOrgCode();
    }
    /**
     * 获取登录用户组织机构code
     * @return
     */
    public String getDeptCode(){
        if(getCurrentAccount()==null){
            return SystemConstant.ROOT_DEPT_CODE;
        }
        return getCurrentAccount().getDepartmentCode();
    }
}
