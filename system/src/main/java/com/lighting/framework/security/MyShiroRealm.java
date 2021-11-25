package com.lighting.framework.security;

import com.lighting.framework.security.api.AccountService;
import com.lighting.framework.security.api.ResourceService;
import com.lighting.framework.security.api.RoleService;
import com.lighting.framework.security.domain.AbstractAccountShiro;
import com.lighting.framework.security.domain.AbstractResourceShiro;
import com.lighting.framework.security.domain.AbstractRoleShiro;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
	@Autowired
	private AccountService accountService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private ResourceService resourceService;

	// 权限信息，包括角色以及权限
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 如果身份认证的时候没有传入User对象，这里只能取到userName
		// 也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
		AbstractAccountShiro user = (AbstractAccountShiro) principals.getPrimaryPrincipal();
		List<AbstractRoleShiro> roleList = roleService.getRoleListByUserId(user.getId());
		for (AbstractRoleShiro role : roleList) {
			authorizationInfo.addRole(role.getId());
			// 根据角色id获取资源
			List<AbstractResourceShiro> resourceList = resourceService.getResourceListByRoleId(role.getId());
			for (AbstractResourceShiro resource : resourceList) {
				authorizationInfo.addStringPermission(resource.getUrl());
			}
		}
		return authorizationInfo;
	}

	/* 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户的输入的账号.
		String userName = (String) token.getPrincipal();
		// 通过username从数据库中查找 User对象.
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		AbstractAccountShiro user = accountService.findAccountByUserName(userName);
		if (user == null) {
			return null;
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, // 这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
				user.getPassword(), // 密码
				ByteSource.Util.bytes(user.getSalt()), // salt=username+salt
				getName() // realm name
		);
		return authenticationInfo;
	}

	// 自定义sessionManager
	@Bean
	public SessionManager sessionManager() {
		TokenAdapterSession mySessionManager = new TokenAdapterSession();
		return mySessionManager;
	}

}