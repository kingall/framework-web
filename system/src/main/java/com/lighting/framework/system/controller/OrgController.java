package com.lighting.framework.system.controller;

import com.lighting.framework.SessionContext;
import com.lighting.framework.SystemConstant;
import com.lighting.framework.core.BaseController;
import com.lighting.framework.core.BaseResponse;
import com.lighting.framework.system.controller.vo.OrgVo;
import com.lighting.framework.system.domain.Org;
import com.lighting.framework.system.domain.Resource;
import com.lighting.framework.system.service.OrgService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 组织机构代码
 * </p>
 *
 * @author Captainxero
 * @since 2020-05-21
 */
@RestController
@RequestMapping("/api/org")
public class OrgController extends BaseController {

	@Autowired
	private OrgService orgServiceImpl;

	@Autowired
	private SessionContext sessionContext;

	/**
	 * 获取组织机构代码列表
	 * 
	 * @return
	 */
	@PostMapping("/getOrgList")
	public BaseResponse getOrgTree(Page<Resource> page, HttpServletRequest request, String pcode) {

		// 查询自己在内所有子节点
		if (pcode == null || "".equals(pcode)) {
			pcode = sessionContext.getOrgCode();
		}

		List<Org> orgList = orgServiceImpl.getOrgTree(pcode);
		Map<String, OrgVo> orgRespMap = new HashMap<String, OrgVo>();
		for (Org org : orgList) {
			// 当前对象
			OrgVo orgVo = orgRespMap.get(org.getCode());
			if (orgVo == null) {
				orgVo = new OrgVo();
				orgRespMap.put(org.getCode(), orgVo);
			}
			BeanUtils.copyProperties(org, orgVo);

			// 获取父亲节点对象
			OrgVo p = orgRespMap.get(org.getPcode());
			if (p == null) {
				p = new OrgVo();
				orgRespMap.put(org.getPcode(), p);
			}

			p.addChildren(orgVo);
		}

		return buildStandardResponse(orgRespMap.get(pcode));
	}

	/**
	 * 获取组织机构代码列表
	 * 
	 * @return
	 */
	@PostMapping("/getALlOrgList")
	public BaseResponse getALlOrgList(HttpServletRequest request) {

		String pcode = SystemConstant.ROOT_ORG_CODE;
		List<Org> orgList = orgServiceImpl.getOrgTree(pcode);
		Map<String, OrgVo> orgRespMap = new HashMap<String, OrgVo>();
		try{


			for (Org org : orgList) {
				// 当前对象
				OrgVo orgVo = orgRespMap.get(org.getCode());
				if (orgVo == null) {
					orgVo = new OrgVo();
					orgRespMap.put(org.getCode(), orgVo);
				}
				BeanUtils.copyProperties(org, orgVo);

				// 获取父亲节点对象
				OrgVo p = orgRespMap.get(org.getPcode());
				if (p == null) {
					p = new OrgVo();
					orgRespMap.put(org.getPcode(), p);
				}

				p.addChildren(orgVo);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		OrgVo orgVo = orgRespMap.get(pcode);
		return buildStandardResponse(orgVo);
	}

	/**
	 * 添加组织机构信息
	 */
	@PostMapping("/addOrg")
	public BaseResponse addOrg(Org org) {
		orgServiceImpl.save(org);
		return new BaseResponse(0, "添加组织机构成功");

	}

	/**
	 * 删除组织机构代码
	 *
	 * @return
	 */
	@PostMapping("/delOrg")
	public BaseResponse delOrg(String id) {
		orgServiceImpl.removeById(id);
		return new BaseResponse(0, "删除组织机构成功");

	}

	/**
	 * 根据code查询组织机构信息
	 * 
	 * @param orgCode
	 * @return
	 */
	@PostMapping("/getOrgByCode")
	public BaseResponse getOrgByCode(String orgCode) {
		List<Org> orgl = orgServiceImpl.getOrgByCode(orgCode);
		if (orgl.size() > 0) {
			return buildStandardResponse(orgl.get(0));
		} else {
			return new BaseResponse(-1, "不存在");
		}
	}

}
