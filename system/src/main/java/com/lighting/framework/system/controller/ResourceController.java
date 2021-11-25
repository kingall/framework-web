package com.lighting.framework.system.controller;

import com.lighting.framework.core.BaseController;
import com.lighting.framework.core.BaseResponse;
import com.lighting.framework.core.util.QueryWrapperBuilder;
import com.lighting.framework.security.api.ResourceService;
import com.lighting.framework.system.domain.Resource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 资源服务管理接口
 * @author Administrator
 *
 */
@RestController
@RequestMapping("api/resource")
public class ResourceController extends BaseController {
	@Autowired
	private ResourceService resourceServiceImpl;

	/**
	 * 获取资源列表
	 * @return
	 */
	@PostMapping("/list")
	public BaseResponse list(HttpServletRequest request) {
		QueryWrapper<Resource> queryWrapper = QueryWrapperBuilder.buildFromRequest(request);
		List<Resource> list = resourceServiceImpl.list(queryWrapper);
		return buildRowsResponse(list);
	}



	/**
	 * 分页查询查询
	 *
	 * @return
	 */
	@PostMapping("/page")
	public BaseResponse getResourceList(Page<Resource> page, HttpServletRequest request){
		IPage<Resource> iPage = resourceServiceImpl.page(page, QueryWrapperBuilder.buildFromRequest(request));
		return buildRowsResponse(iPage);
	}
	/**
	 * 删除资源信息
	 * @param id
	 * @return
	 */
	@PostMapping("/delResource")
	public BaseResponse delResource(String id){
		if(resourceServiceImpl.removeById(id)){
			return buildStandardResponse(true);
		}else{
			return new BaseResponse(-1, "删除失败");
		}
	}
	/**
	 * 获取资源资源信息
	 * @param id
	 * @return
	 */
	@PostMapping("/getResourceById")
	public BaseResponse getResourceById(String id){
		return buildStandardResponse(resourceServiceImpl.getById(id));
	}
	/**
	 * 修改资源信息
	 */
		@PostMapping("/saveResource")
	public BaseResponse saveResource(Resource resource){
		/*if(StringUtils.isBlank(resource.getId())){
			resource.setCreater(getUserId());
			resource.setCreateTime(new Date());
		}else{
			resource.setCreater(getUserId());
			resource.setUpdateTime(new Date());
		}*/
		if(resourceServiceImpl.saveOrUpdate(resource)){
			return buildStandardResponse(true);
		}else{
			return new BaseResponse(-1, "新增失败");
		}
	}
}
