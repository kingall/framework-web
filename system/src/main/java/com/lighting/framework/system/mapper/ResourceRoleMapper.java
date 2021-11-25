package com.lighting.framework.system.mapper;

import com.lighting.framework.system.domain.ResourceRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenwei
 * @since 2019-12-31
 */
public interface ResourceRoleMapper extends BaseMapper<ResourceRole> {

    boolean removeByRoleId(String role_id);
}
