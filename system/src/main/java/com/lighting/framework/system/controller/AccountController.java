package com.lighting.framework.system.controller;

import com.lighting.framework.SessionContext;
import com.lighting.framework.core.BaseController;
import com.lighting.framework.core.BaseResponse;
import com.lighting.framework.core.util.QueryWrapperBuilder;
import com.lighting.framework.system.domain.Account;
import com.lighting.framework.system.domain.AccountRole;
import com.lighting.framework.system.domain.UserInfo;
import com.lighting.framework.system.service.AccountRoleService;
import com.lighting.framework.system.service.AccountService;
import com.lighting.framework.system.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 用户管理接口服务
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("api/account")
public class AccountController extends BaseController {
	@Autowired
	private AccountService accountServiceImpl;

	@Autowired
	private UserInfoService userInfoServiceImpl;

	@Autowired
	private AccountRoleService accountRoleService;

	@Autowired
	private HashedCredentialsMatcher hashedCredentialsMatcher;

	@Autowired
	private SessionContext sessionContext;

	/**
	 * 用户登录接口
	 * 
	 * @return
	 */
	@PostMapping("/getAccountList")
	public BaseResponse getAccountList(Page<Account> page, HttpServletRequest request) {
		IPage<Account> accountPage = accountServiceImpl.page(page, QueryWrapperBuilder.buildFromRequest(request));
		return buildRowsResponse(accountPage);
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/delAccount")
	public BaseResponse delAccount(String id) {
		if (accountServiceImpl.removeById(id)) {
			return buildStandardResponse(true);
		} else {
			return new BaseResponse(-1, "删除失败");
		}
	}

	/**
	 * 修改用户
	 */
	@PostMapping("/saveAccount")
	public BaseResponse saveAccount(Account account) {
		// 判断用户名是否存在
		Account existAccount = accountServiceImpl.findUserByUserName(account.getUsername());
		
		//添加组织机构信息
		if(account.getOrgCode() == null || account.getOrgCode().isEmpty()) {
			account.setOrgCode(sessionContext.getOrgCode());
		}

		//添加部门信息
		if(account.getDepartmentCode() == null || account.getDepartmentCode().isEmpty()) {
			account.setOrgCode(sessionContext.getDeptCode());
		}

		// 判断用户是否存在
		if (existAccount != null && !existAccount.getId().equals(account.getId())) {
			return new BaseResponse(-1, "用户已存在");
		} else {
			// 对用户密码加密
			if (account.getPassword() != null && !account.getPassword().isEmpty()) {
				account.setSalt(new SecureRandomNumberGenerator().nextBytes().toHex());
				SimpleHash simpleHash = new SimpleHash(hashedCredentialsMatcher.getHashAlgorithmName(),
						account.getPassword(), account.getSalt(), hashedCredentialsMatcher.getHashIterations());
				account.setPassword(simpleHash.toString());
			} else {
				if (account.getId() == null || account.getId().isEmpty()) {
					return new BaseResponse(-1, "密码不能为空");
				}
			}
			//添加默认用户个人信息
			if(account.getId() == null || account.getId().isEmpty()){
				UserInfo userInfo = new UserInfo();
				userInfoServiceImpl.save(userInfo);
				account.setUserId(userInfo.getId());
			}

			//添加账号
			if (accountServiceImpl.saveOrUpdate(account)) {
				//匹配账号角色信息
				List<AccountRole> userRoles = accountRoleService.list(new QueryWrapper<AccountRole>().eq("user_id", account.getId()));
				if(userRoles.size()>0){
					AccountRole accountRole = userRoles.get(0);
					accountRole.setUpdater(sessionContext.getAccountId());
					accountRole.setUpdateTime(new Date());
					accountRoleService.saveOrUpdate(accountRole);
				}else{
					AccountRole accountRole = new AccountRole();
					accountRole.setRoleId(account.getRoleId());
					accountRole.setUserId(account.getId());
					accountRole.setUpdater(sessionContext.getAccountId());
					accountRole.setCreateTime(new Date());
					accountRoleService.saveOrUpdate(accountRole);
				}
				return buildStandardResponse(true);
			} else {
				return new BaseResponse(-1, "新增失败");
			}

		}
	}

	/**
	 * 查询机构下的所有人员
	 * 
	 * @return
	 */
	@PostMapping("/getAccountListByOrgCode")
	public BaseResponse getAccountListByOrgCode(String orgCode, Page<Account> page) {
		if(orgCode ==null || orgCode.isEmpty()) {
			orgCode = sessionContext.getOrgCode();
		}
		IPage<Account> accountList = accountServiceImpl.page(page, new QueryWrapper<Account>().likeRight("org_code", orgCode));
		return buildRowsResponse(accountList);
	}
	
	/**
	 * 查询机构下的所有人员
	 * 
	 * @return
	 */
	@PostMapping("/getAccountListXOrgCode")
	public BaseResponse getAccountListXOrgCode(String orgCode, IPage<Account> page) {
		if(orgCode ==null || orgCode.isEmpty()) {
			//orgCode = getOrgCode();
		}
		IPage<Account> accountList = accountServiceImpl.page(page, new QueryWrapper<Account>().eq("org_code", orgCode));
		return buildRowsResponse(accountList);
	}
	
	/**
	 * 根据id获取用户账号
	 * 
	 * @return
	 */
	@PostMapping("/getAccountById")
	public BaseResponse getAccountById(String id) {
		AccountRole accountRole = accountRoleService.getOne(new QueryWrapper<AccountRole>().eq("user_id",id));
		Account account = accountServiceImpl.getById(id);
		account.setDefaultRole(accountRole.getRoleId());
		return buildStandardResponse(account);
	}


	public static void main(String[] args) {
		SimpleHash simpleHash = new SimpleHash("md5",
				"123456","d38b9701acb01df3625afcf226ae33e8", 2);
		System.out.println(simpleHash.toString());
	}
}
