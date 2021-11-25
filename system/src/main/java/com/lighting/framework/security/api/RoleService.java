package com.lighting.framework.security.api;

import com.lighting.framework.security.domain.AbstractRoleShiro;

import java.util.List;


public interface RoleService {

	List<AbstractRoleShiro> getRoleListByUserId(String id);

}
