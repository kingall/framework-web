package com.lighting.framework.system.mapper;


import com.lighting.framework.system.domain.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenwei
 * @since 2019-12-31
 */
public interface ResourceMapper extends BaseMapper<Resource> {

	public List<Resource> getResourceListByUserId(String userId);

	public List getResourceListByRoleId(String roleId);

}
