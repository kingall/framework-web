package com.lighting.framework.system.controller;

import com.lighting.framework.core.BaseController;
import com.lighting.framework.core.BaseResponse;
import com.lighting.framework.core.util.QueryWrapperBuilder;
import com.lighting.framework.system.domain.ResourceRole;
import com.lighting.framework.system.domain.Role;
import com.lighting.framework.system.service.ResourceRoleService;
import com.lighting.framework.system.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/role")
public class RoleController extends BaseController {
	
	@Autowired
	private RoleService roleServiceImpl;
	
	@Autowired
	private ResourceRoleService resourceRoleServiceImpl;
	
	/**
	 * 获取角色列表
	 * @return
	 */
	@PostMapping("/getRoleList")
	public BaseResponse getRoleList(Page<Role> page, HttpServletRequest request){
		IPage<Role> iPage = roleServiceImpl.page(page, QueryWrapperBuilder.buildFromRequest(request));
		return buildRowsResponse(iPage);
	}
	@PostMapping("/list")
	public BaseResponse list(HttpServletRequest request){
		List<Role> list = roleServiceImpl.list(QueryWrapperBuilder.buildFromRequest(request));
		return buildRowsResponse(list);
	}
	/**
	 * 根据角色id获取资源列表
	 * @return
	 */
	@PostMapping("/getResourceListByRoleId")
	public BaseResponse getResourceListByRoleId(String id){
		QueryWrapper<ResourceRole> queryWrapper = new QueryWrapper<ResourceRole>();
		queryWrapper.eq("role_id", id);
		List<ResourceRole> iList = resourceRoleServiceImpl.list(queryWrapper);
		return buildRowsResponse(iList);
	}
	
	/**
	 * 删除角色信息
	 * @param id
	 * @return
	 */
	@PostMapping("/delRole")
	public BaseResponse delRole(String id){
		if(roleServiceImpl.removeById(id)){
			//删除原有的角色关系
			resourceRoleServiceImpl.remove( new QueryWrapper<ResourceRole>().eq("role_id", id));
			return buildStandardResponse(true);
		}else{
			return new BaseResponse(-1, "删除失败");
		}
	}
	/**
	 * 保存角色信息
	 */
	@PostMapping("/saveRole")
	public BaseResponse saveRole(Role role,String resourceIds){
		if(roleServiceImpl.saveOrUpdate(role)){
			//删除原有的角色关系
			resourceRoleServiceImpl.remove(new QueryWrapper<ResourceRole>().eq("role_id", role.getId()));
			//添加新的角色关系
			List<ResourceRole> resourceRoleList= new ArrayList<ResourceRole>();
			for(String resourceId : resourceIds.split(",")){
				ResourceRole resourceRole = new ResourceRole();
				resourceRole.setRoleId(role.getId());
				resourceRole.setResourceId(resourceId);
				resourceRoleList.add(resourceRole);
			}
			resourceRoleServiceImpl.saveBatch(resourceRoleList);
			return buildStandardResponse(true);
		}else{
			return new BaseResponse(-1, "新增失败");
		}
	}
	/**
	 * 只保存角色信息,没资源
	 */
	@PostMapping("/saveRoleInfo")
	public BaseResponse saveRoleInfo(Role role){
		try{
			if(StringUtils.isBlank(role.getId())){
				//role.setCreater(getCurrentUserInfo().getUsername());
				role.setCreateTime(new Date());
			}else{
				//role.setUpdater(getCurrentUserInfo().getUsername());
				role.setUpdateTime(new Date());
			}
			roleServiceImpl.saveOrUpdate(role);
			return buildStandardResponse(true);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return new BaseResponse(-1, "新增失败");
	}
	/**
	 * 只保存角色信息,没资源
	 */
	@PostMapping("/getRoleInfo")
	public BaseResponse getRoleInfo(String id){
		Role role = roleServiceImpl.getById(id);
		return buildStandardResponse(role);
	}
}
