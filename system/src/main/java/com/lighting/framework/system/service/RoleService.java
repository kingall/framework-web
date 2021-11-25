package com.lighting.framework.system.service;


import com.lighting.framework.system.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenwei
 * @since 2019-12-31
 */
public interface RoleService extends IService<Role>, com.lighting.framework.security.api.RoleService {

	List getRoleListByUserId(String id);

}
