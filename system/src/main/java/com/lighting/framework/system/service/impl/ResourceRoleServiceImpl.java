package com.lighting.framework.system.service.impl;

import com.lighting.framework.system.domain.ResourceRole;
import com.lighting.framework.system.mapper.ResourceRoleMapper;
import com.lighting.framework.system.service.ResourceRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenwei
 * @since 2019-12-31
 */
@Service
public class ResourceRoleServiceImpl extends ServiceImpl<ResourceRoleMapper, ResourceRole> implements ResourceRoleService {

    @Autowired
    private ResourceRoleMapper resourceRoleMapper;

    @Override
    public boolean removeByRoleId(String role_id) {
        return resourceRoleMapper.removeByRoleId(role_id);
    }
}
