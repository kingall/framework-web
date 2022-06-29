package com.lighting.framework.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lighting.framework.security.domain.AbstractRoleShiro;
import com.lighting.framework.system.entity.Dept;
import com.lighting.framework.system.entity.Role;
import com.lighting.framework.system.mapper.DeptMapper;
import com.lighting.framework.system.mapper.RoleMapper;
import com.lighting.framework.system.service.IDeptService;
import com.lighting.framework.system.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenwei
 * @since 2022-06-03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public List<AbstractRoleShiro> getRoleListByUserId(String uid) {
        return baseMapper.getRoleListByUserId(uid);
    }
}
