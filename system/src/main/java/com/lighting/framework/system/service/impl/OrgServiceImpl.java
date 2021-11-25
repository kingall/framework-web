package com.lighting.framework.system.service.impl;

import com.lighting.framework.system.domain.Org;
import com.lighting.framework.system.mapper.OrgMapper;
import com.lighting.framework.system.service.OrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Captainxero
 * @since 2020-05-21
 */
@Service
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements OrgService {

	@Override
	public List<Org> getOrgTree(String pcode) {

		return baseMapper.getOrgTree(pcode);
	}

	@Override
	public List<Org> getOrgByCode(String orgCode) {
		return baseMapper.getOrgByCode(orgCode);
	}

}
