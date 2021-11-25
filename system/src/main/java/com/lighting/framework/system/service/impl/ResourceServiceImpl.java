package com.lighting.framework.system.service.impl;

import com.lighting.framework.system.domain.Resource;
import com.lighting.framework.system.mapper.ResourceMapper;
import com.lighting.framework.system.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
	
	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public List getResourceListByRoleId(String id) {
		return resourceMapper.getResourceListByRoleId(id);
	}

	@Override
	public List getResourceListByUserId(String id) {
		// TODO Auto-generated method stub
		return resourceMapper.getResourceListByUserId(id);
	}

}
