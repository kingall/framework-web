package com.lighting.framework.system.controller;

import com.lighting.framework.SessionContext;
import com.lighting.framework.core.BaseController;
import com.lighting.framework.core.BaseResponse;
import com.lighting.framework.core.util.QueryWrapperBuilder;
import com.lighting.framework.system.domain.UserInfo;
import com.lighting.framework.system.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 人员信息管理类
 * </p>
 *
 * @author Captainxero
 * @since 2020-05-21
 */
@RestController
@RequestMapping("api/userInfo")
public class UserInfoController extends BaseController {

	@Autowired
	private UserInfoService userInfoServiceImpl;

	@Autowired
	private SessionContext sessionContext;

	/**
	 * 获取人员信息列表
	 * 
	 * @return
	 */
	@PostMapping("/getUserInfoList")
	public BaseResponse getUserInfoList(Page<UserInfo> page, HttpServletRequest request) {
		QueryWrapper<UserInfo> queryWrapper = QueryWrapperBuilder.buildFromRequest(request);
		// 添加组织机构查询条件
		//queryWrapper.eq("org_code", getOrgCode());
		IPage<UserInfo> iPage = userInfoServiceImpl.page(page, queryWrapper);
		return buildRowsResponse(iPage);
	}

	/**
	 * 删除人员新成功
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/delUserInfo")
	public BaseResponse delUserInfo(String id) {
		userInfoServiceImpl.removeById(id);
		return new BaseResponse(0, "删除用户成功");

	}

	/**
	 * 添加人员信息成功
	 * 
	 * @param UserInfo
	 * @return
	 */
	@PostMapping("/addUserInfo")
	public BaseResponse addUserInfo(UserInfo UserInfo) {
		// 添加组织机构信息
		if (UserInfo.getOrgCode() == null || UserInfo.getOrgCode().isEmpty()) {
			//UserInfo.setOrgCode(getOrgCode());
		}
		userInfoServiceImpl.saveOrUpdate(UserInfo);
		return new BaseResponse(0, "添加人员成功");
	}

	/**
	 * 获取人员详情
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/getUserInfo")
	public BaseResponse getUserInfoById(String id) {
		UserInfo UserInfo = userInfoServiceImpl.getById(id);
		return buildStandardResponse(UserInfo);
	}
	/**
	 * 获取人员信息列表
	 *
	 * @return
	 */
	@PostMapping("/getUserInfoByUserIdList")
	public BaseResponse getUserInfoByUserIdList(Page<UserInfo> page, HttpServletRequest request) {
		QueryWrapper<UserInfo> queryWrapper = QueryWrapperBuilder.buildFromRequest(request);
		// 添加组织机构查询条件
		queryWrapper.eq("org_code", sessionContext.getOrgCode());
		IPage<UserInfo> iPage = userInfoServiceImpl.page(page, queryWrapper);
		return buildRowsResponse(iPage);
	}
}
