package com.lighting.framework.system.service.impl;

import com.lighting.framework.security.domain.AbstractAccountShiro;
import com.lighting.framework.system.domain.Account;
import com.lighting.framework.system.domain.AccountAndRole;
import com.lighting.framework.system.mapper.AccountMapper;
import com.lighting.framework.system.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenwei
 * @since 2019-12-31
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

	@Override
	public Account findUserByUserName(String userName) {
		return baseMapper.findAccountByUserName(userName);
	}

	@Override
	public AccountAndRole getRoleListByAccountId(String uid) {
		return baseMapper.getRoleListByAccountId(uid);
	}

	public AbstractAccountShiro findAccountByUserName(String userName){
		return baseMapper.findAccountByUserName(userName);
	}

}
