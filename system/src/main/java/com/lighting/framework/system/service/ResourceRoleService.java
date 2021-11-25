package com.lighting.framework.system.service;

import com.lighting.framework.system.domain.ResourceRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenwei
 * @since 2019-12-31
 */
public interface ResourceRoleService extends IService<ResourceRole> {

    boolean removeByRoleId(String id);
}
