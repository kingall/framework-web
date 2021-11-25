package com.lighting.framework.system.mapper;

import com.lighting.framework.system.domain.Org;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Captainxero
 * @since 2020-05-21
 */
public interface OrgMapper extends BaseMapper<Org> {

	public List<Org> getOrgTree(String pcode);
	
	public List<Org> getOrgByCode(String orgCode);
	

}
