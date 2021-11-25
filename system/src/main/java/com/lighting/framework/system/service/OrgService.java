package com.lighting.framework.system.service;

import com.lighting.framework.system.domain.Org;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Captainxero
 * @since 2020-05-21
 */
public interface OrgService extends IService<Org> {

	public List<Org> getOrgTree(String pcode);

	public List<Org> getOrgByCode(String orgCode);

}
