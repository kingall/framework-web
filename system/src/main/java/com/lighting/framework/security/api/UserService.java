package com.lighting.framework.security.api;


import com.lighting.framework.security.domain.AbstractUserShiro;

/**
 * 权限框架用户查询接口定义
 */
public interface UserService {
	/**
	 * 根据用户名获取用户信息
	 * @param userName
	 * @return
	 */
	AbstractUserShiro findUserByUserName(String userName);
}
