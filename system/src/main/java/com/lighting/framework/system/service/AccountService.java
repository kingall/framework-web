package com.lighting.framework.system.service;

import com.lighting.framework.system.domain.Account;
import com.lighting.framework.system.domain.AccountAndRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenwei
 * @since 2019-12-31
 */
public interface AccountService extends IService<Account>, com.lighting.framework.security.api.AccountService {

    Account findUserByUserName(String userName);

    AccountAndRole getRoleListByAccountId(String id);
}
