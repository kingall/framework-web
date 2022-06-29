package com.lighting.framework.system.controller;

import com.lighting.framework.SystemController;
import com.lighting.framework.core.BaseResponse;
import com.lighting.framework.core.StandardResponse;
import com.lighting.framework.system.entity.Menu;
import com.lighting.framework.system.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Api(value="用户登录接口", tags="用户登录接口")
@RestController
@RequestMapping("api")
public class DefaultController extends SystemController {

    private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @Autowired
    private IMenuService menuServiceImpl;

    /**
     * 用户登录接口
     *
     * @return
     */
    @ApiOperation("登录接口")
    @PostMapping("/login")
    public BaseResponse login(String username, String password) {
        BaseResponse baseResponse = new BaseResponse();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            StandardResponse response = new StandardResponse(200, "登录成功");
            subject.login(token);
            subject.getSession().getId();
            response.setToken((String) subject.getSession().getId());
            return response;
        } catch (IncorrectCredentialsException e) {
            baseResponse.setCode(500);
            baseResponse.setMsg("密码错误");
        } catch (LockedAccountException e) {
            baseResponse.setCode(500);
            baseResponse.setMsg("登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            baseResponse.setCode(500);
            baseResponse.setMsg("该用户不存在");
        } catch (Exception e) {
            baseResponse.setCode(500);
            baseResponse.setMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return baseResponse;
    }

    @ApiOperation("登出接口")
    @PostMapping("/logout")
    public BaseResponse logout() {
        BaseResponse baseResponse = new BaseResponse();
        SecurityUtils.getSubject().logout();
        baseResponse = new StandardResponse(200, "登出成功！");
        return baseResponse;
    }

    /**
     * 获取菜单信息
     */
    @ApiOperation("鉴权失败接口")
    @PostMapping("/unauthorized")
    public BaseResponse unauthorized() {
        BaseResponse baseResponse = new BaseResponse(500, "权限不足");
        return baseResponse;
    }

}
