package com.lighting.framework.system.controller;


import com.lighting.framework.SessionContext;
import com.lighting.framework.core.BaseController;
import com.lighting.framework.core.BaseResponse;
import com.lighting.framework.system.domain.Department;
import com.lighting.framework.system.service.DepartmentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenwei
 * @since 2021-02-22
 */
@RestController
@RequestMapping("api/department")
public class DepartmentController extends BaseController {
    @Autowired
    private DepartmentService departmentServiceImpl;

    @Autowired
    private SessionContext sessionContext;


    /**
     * 添加组织机构信息
     */
    @PostMapping("/addDept")
    public BaseResponse addDept(Department department) {
        departmentServiceImpl.save(department);
        return new BaseResponse(0, "添加部门成功");

    }

    /**
     * 删除组织机构代码
     *
     * @return
     */
    @PostMapping("/delDept")
    public BaseResponse delDept(String id) {
        departmentServiceImpl.removeById(id);
        return new BaseResponse(0, "删除组织机构成功");

    }

    /**
     * 根据code查询组织机构信息
     *
     * @param deptCode
     * @return
     */
    @PostMapping("/getDeptByCode")
    public BaseResponse getDeptByCode(String deptCode) {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<Department>();
        queryWrapper.eq("code",deptCode);

        Department department = departmentServiceImpl.getOne(queryWrapper);
        if (department != null) {
            return buildStandardResponse(department);
        } else {
            return new BaseResponse(-1, "不存在");
        }
    }

}
