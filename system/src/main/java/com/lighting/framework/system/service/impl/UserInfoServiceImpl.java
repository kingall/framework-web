package com.lighting.framework.system.service.impl;

import com.lighting.framework.system.domain.UserInfo;
import com.lighting.framework.system.mapper.UserInfoMapper;
import com.lighting.framework.system.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Captainxero
 * @since 2020-05-21
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
