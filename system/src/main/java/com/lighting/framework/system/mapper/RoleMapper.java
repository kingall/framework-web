package com.lighting.framework.system.mapper;


import com.lighting.framework.system.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenwei
 * @since 2019-12-31
 */
public interface RoleMapper extends BaseMapper<Role> {

    List getRoleListByUserId(@Param("uid") String uid);
}
