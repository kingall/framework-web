package com.lighting.framework.system.mapper;


import com.lighting.framework.system.domain.Account;
import com.lighting.framework.system.domain.AccountAndRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenwei
 * @since 2019-12-31
 */
public interface AccountMapper extends BaseMapper<Account> {

	public Account findAccountByUserName(String username);

    AccountAndRole getRoleListByAccountId(@Param("uid") String uid);

}
