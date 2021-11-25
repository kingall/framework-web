package com.lighting.framework.system.service;


import com.lighting.framework.system.domain.Resource;
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
public interface ResourceService extends IService<Resource>, com.lighting.framework.security.api.ResourceService {

	List getResourceListByRoleId(String id);

	List getResourceListByUserId(String string);

}
