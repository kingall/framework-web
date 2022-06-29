package com.lighting.framework;

import com.lighting.framework.core.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 系统公共类型可以获取用户登录后的信息
 * 如：用户账号信息等
 */
public class SystemController extends BaseController {

    @Autowired
    public SessionContext sessionContext;
}
