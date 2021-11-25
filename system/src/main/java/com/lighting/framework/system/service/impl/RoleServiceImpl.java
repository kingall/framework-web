package com.lighting.framework.system.service.impl;

import com.lighting.framework.system.domain.Role;
import com.lighting.framework.system.mapper.RoleMapper;
import com.lighting.framework.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenwei
 * @since 2019-12-31
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	@Override
	public List getRoleListByUserId(String uid) {
		return baseMapper.getRoleListByUserId(uid);
	}

}
