package com.lighting.framework.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lighting.framework.system.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    @Select("SELECT role.* FROM sys_role role, sys_user_role ref WHERE role.id = ref.role_id AND ref.user_id = #{uid}")
    List getRoleListByUserId(@Param("uid") String uid);
}
