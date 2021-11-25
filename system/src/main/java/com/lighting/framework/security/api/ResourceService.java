package com.lighting.framework.security.api;

import com.lighting.framework.security.domain.AbstractResourceShiro;
import com.lighting.framework.system.domain.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ResourceService extends IService<Resource> {

	List<AbstractResourceShiro> getResourceListByRoleId(String id);

}
