package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

<#if restControllerStyle>
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lighting.framework.core.BaseController;
import com.lighting.framework.core.BaseResponse;
import com.lighting.framework.core.util.QueryWrapperBuilder;

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("/api<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} extends BaseController{
	@Autowired
	private ${table.serviceName} ${table.serviceImplName?uncap_first};

	/**
	 * 列表查询
	 *
	 * @return
	 */
	@PostMapping("/get${entity}List")
	public BaseResponse get${entity}List(HttpServletRequest request) {
		List<${entity}> list = ${table.serviceImplName?uncap_first}.list(QueryWrapperBuilder.buildFromRequest(request));
		return buildRowsResponse(list);
	}

	/**
	 * 分页查询查询
	 *
	 * @return
	 */
	@PostMapping("/get${entity}ListByPage")
	public BaseResponse get${entity}ListByPage(Page<${entity}> page, HttpServletRequest request) {
		IPage<${entity}> iPage = ${table.serviceImplName?uncap_first}.page(page, QueryWrapperBuilder.buildFromRequest(request));
		return buildRowsResponse(iPage);
	}

	/**
	 * 保存数据
	 *
	 * @return
	 */
	@PostMapping("/save${entity}")
	public BaseResponse save${entity}( ${entity}  ${entity}) {
		${table.serviceImplName?uncap_first}.saveOrUpdate(${entity});
		return new BaseResponse(0, "保存成功");
	}

	/**
	 * 删除数据
	 *
	 * @return
	 */
	@PostMapping("/del${entity}ById")
	public BaseResponse del${entity}ById( String  id) {
		${table.serviceImplName?uncap_first}.removeById(id);
		return new BaseResponse(0, "删除成功");
	}

	/**
	 * 根据id获取数据
	 *
	 * @return
	 */
	@PostMapping("/get${entity}ById")
	public BaseResponse get${entity}ById( String  id) {
		return buildStandardResponse(${table.serviceImplName?uncap_first}.getById(id));
	}
</#if>
}
</#if>
