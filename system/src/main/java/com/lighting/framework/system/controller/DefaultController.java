package com.lighting.framework.system.controller;

import com.lighting.framework.SessionContext;
import com.lighting.framework.core.BaseController;
import com.lighting.framework.core.BaseResponse;
import com.lighting.framework.core.StandardResponse;
import com.lighting.framework.security.domain.AbstractAccountShiro;
import com.lighting.framework.system.domain.AccountAndRole;
import com.lighting.framework.system.service.AccountService;
import com.lighting.framework.system.service.ResourceService;
import com.lighting.framework.system.service.RoleService;
import com.lighting.framework.system.domain.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class DefaultController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

	@Autowired
	private ResourceService resourceServiceImpl;

	@Autowired
	private RoleService roleServiceImpl;

	@Autowired
	private AccountService userServiceImpl;

	@Autowired
	private SessionContext sessionContext;

	/**
	 * 用户登录接口
	 * 
	 * @return
	 */
	@PostMapping("/login")

	public BaseResponse login(String username, String password,HttpSession session) {
		BaseResponse baseResponse = new BaseResponse();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			StandardResponse response = new StandardResponse(0,"登录成功");
			subject.login(token);
			subject.getSession().getId();
			response.setToken((String) subject.getSession().getId());
			response.setDomain(sessionContext.getCurrentAccount());
			return response;
		} catch (IncorrectCredentialsException e) {
			baseResponse.setCode(-1);
			baseResponse.setMsg("密码错误");
		} catch (LockedAccountException e) {
			baseResponse.setCode(-1);
			baseResponse.setMsg("登录失败，该用户已被冻结");
		} catch (AuthenticationException e) {
			baseResponse.setCode(-1);
			baseResponse.setMsg("该用户不存在");
		} catch (Exception e) {
			baseResponse.setCode(-1);
			baseResponse.setMsg(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return baseResponse;
	}

	@PostMapping("/user/info")
	public BaseResponse getUserInfo() {
		 AbstractAccountShiro currentUserInfo = null;
		 AccountAndRole userAndRoles= (AccountAndRole) userServiceImpl.getRoleListByAccountId(currentUserInfo.getId());
		//返回角色
		return buildStandardResponse(userAndRoles);
	}

	/**
	 * 获取菜单信息
	 */
	@PostMapping("/unauthorized")
	public BaseResponse unauthorized() {
		BaseResponse baseResponse = new BaseResponse(-9, "权限不足");
		return baseResponse;
	}

	@PostMapping("/getMainMenu")
	public BaseResponse getMainMenu(String userId) {
		List<Resource> resourceListByUserId = resourceServiceImpl.getResourceListByUserId(userId);
		List<Resource> resourceList = new ArrayList<>();
		for (Resource res: resourceListByUserId) {
			if("0".equals(res.getPid())){
				resourceList.add(res);
				for (Resource item: resourceListByUserId) {
					if(res.getId().equals(item.getPid())){
						resourceList.add(item);
					}
				}
			}
		}
		return buildRowsResponse(resourceList);
	}
}
