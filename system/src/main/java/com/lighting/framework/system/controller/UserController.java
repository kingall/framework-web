package com.lighting.framework.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lighting.framework.SystemController;
import com.lighting.framework.core.BaseResponse;
import com.lighting.framework.core.StandardResponse;
import com.lighting.framework.core.util.QueryWrapperBuilder;
import com.lighting.framework.system.entity.User;
import com.lighting.framework.system.entity.UserRole;
import com.lighting.framework.system.service.IUserRoleService;
import com.lighting.framework.system.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理接口服务
 *
 * @author Administrator
 */
@RestController
@RequestMapping("api/user")
public class UserController extends SystemController {
    @Autowired
    private IUserService userServiceImpl;

    @Autowired
    private IUserRoleService userRoleServiceImpl;

    @Autowired
    private HashedCredentialsMatcher hashedCredentialsMatcher;

    /**
     * 用户登录接口
     *
     * @return
     */
    @PostMapping("/getUserList")
    public BaseResponse getUserList(Page<User> page, HttpServletRequest request) {
        IPage<User> userPage = userServiceImpl.page(page, QueryWrapperBuilder.buildFromRequest(request));
        return StandardResponse.data(userPage);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @PostMapping("/delUser")
    public BaseResponse delUser(String id) {
        if (userServiceImpl.removeById(id)) {
            return BaseResponse.fail();
        } else {
            return BaseResponse.success();
        }
    }

    /**
     * 修改用户
     */
    @PostMapping("/saveAccount")
    public BaseResponse saveAccount(User user, List<String> roles) {
        // 判断用户名是否存在
        List<User> existUserList = userServiceImpl.list(new QueryWrapper<User>().eq("username", user.getUsername()));

        // 判断用户是否存在(新增用户)
        if (existUserList.size() > 0 && StringUtils.isEmpty(user.getId())) {
            return new BaseResponse(-1, "用户已存在");
        }

        // 判断用户是否存在(修改用户时)
        if (existUserList.size() > 0 && !existUserList.get(0).getId().equals(user.getId())) {
            return new BaseResponse(-1, "用户已存在");
        }

        //密码不能为空
        if (StringUtils.isEmpty(user.getPassword())) {
            return new BaseResponse(-1, "密码不能为空");
        }

        //添加组织机构信息
        if (StringUtils.isEmpty(user.getDeptCode())) {
            user.setDeptCode(sessionContext.getDeptCode());
        }

        // 对用户密码加密
        user.setSalt(new SecureRandomNumberGenerator().nextBytes().toHex());
        SimpleHash simpleHash = new SimpleHash(hashedCredentialsMatcher.getHashAlgorithmName(),
                user.getPassword(), user.getSalt(), hashedCredentialsMatcher.getHashIterations());
        user.setPassword(simpleHash.toString());

        //添加账号
        if (userServiceImpl.saveOrUpdate(user)) {

            //添加角色信息
            if (roles != null && roles.size() > 0) {
                userRoleServiceImpl.remove(new QueryWrapper<UserRole>().eq("user_id", user.getId()));
                List<UserRole> userRoleList = new ArrayList<>(roles.size());
                for (String roleId : roles) {
                    if (!StringUtils.isEmpty(roleId)) {
                        UserRole userRole = new UserRole();
                        userRole.setRoleId(user.getId());
                        userRole.setUserId(user.getId());
                        userRoleList.add(userRole);
                    }
                }
                userRoleServiceImpl.saveBatch(userRoleList);
            }

            return BaseResponse.success();
        } else {
            return BaseResponse.fail();
        }

    }


    /**
     * 查询机构下的所有人员
     *
     * @return
     */
    @PostMapping("/getUserListByDeptCode")
    public BaseResponse getUserListByDeptCode(String deptCode, Page<User> page) {
        if (deptCode == null || deptCode.isEmpty()) {
            deptCode = sessionContext.getDeptCode();
        }
        IPage<User> userList = userServiceImpl.page(page, new QueryWrapper<User>().likeRight("dept_code", deptCode));
        return StandardResponse.data(userList);
    }

    /**
     * 根据id获取用户账号
     *
     * @return
     */
    @PostMapping("/getAccountById")
    public BaseResponse getAccountById(String id) {
        User user = userServiceImpl.getById(id);
        return StandardResponse.data(user);
    }


    public static void main(String[] args) {
        String sal = new SecureRandomNumberGenerator().nextBytes().toHex();
        SimpleHash simpleHash = new SimpleHash("md5",
                "123456", sal, 2);
        System.out.println(sal);
        System.out.println(simpleHash.toString());
    }
}
