package cn.czy15.zyweb.controller;

import cn.czy15.zyweb.aop.annotation.MyLog;
import cn.czy15.zyweb.entity.SysRole;
import cn.czy15.zyweb.service.RoleService;
import cn.czy15.zyweb.utils.DataResult;
import cn.czy15.zyweb.vo.req.AddRoleReqVO;
import cn.czy15.zyweb.vo.req.RolePageReqVO;
import cn.czy15.zyweb.vo.req.RoleUpdateReqVO;
import cn.czy15.zyweb.vo.resp.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Api(tags = "组织管理-角色管理",description = "角色管理相关接口")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/roles")
    @ApiOperation(value = "分页获取角色数据接口")
    @MyLog(title = "组织管理-角色管理",action = "分页获取角色数据接口")
    @RequiresPermissions("sys:role:list")
    public DataResult<PageVO<SysRole>> pageInfo(@RequestBody RolePageReqVO vo){
        DataResult result =DataResult.success();
        result.setData(roleService.pageInfo(vo));
        return result;
    }

    @PostMapping("/role")
    @ApiOperation(value = "新增角色接口")
    @MyLog(title = "组织管理-角色管理",action = "新增角色接口")
    @RequiresPermissions("sys:role:add")
    public DataResult<SysRole> addRole(@RequestBody @Valid AddRoleReqVO vo){
        DataResult result =DataResult.success();
        result.setData(roleService.addRole(vo));
        return result;
    }

    @GetMapping("/role/{id}")
    @ApiOperation(value = "获取角色详情接口")
    @MyLog(title = "组织管理-角色管理",action = "获取角色详情接口")
    @RequiresPermissions("sys:role:detail")
    public DataResult<SysRole> detailInfo(@PathVariable("id") String id){
        DataResult result=DataResult.success();
        result.setData(roleService.detailInfo(id));
        return result;
    }

    @PutMapping("/role")
    @ApiOperation(value = "更新角色信息接口")
    @MyLog(title = "组织管理-角色管理",action = "更新角色信息接口")
    @RequiresPermissions("sys:role:update")
    public DataResult updateRole(@RequestBody @Valid RoleUpdateReqVO vo){
        DataResult result=DataResult.success();
        roleService.updateRole(vo);
        return result;
    }

    @DeleteMapping("/role/{id}")
    @ApiOperation(value = "删除角色接口")
    @MyLog(title = "组织管理-角色管理",action = "删除角色接口")
    @RequiresPermissions("sys:role:delete")
    public DataResult deletedRole(@PathVariable("id") String id){
        roleService.deletedRole(id);
        DataResult result=DataResult.success();
        return result;
    }
}
