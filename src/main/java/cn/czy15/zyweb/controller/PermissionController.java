package cn.czy15.zyweb.controller;


import cn.czy15.zyweb.aop.annotation.MyLog;
import cn.czy15.zyweb.entity.SysPermission;
import cn.czy15.zyweb.service.PermissionService;
import cn.czy15.zyweb.utils.DataResult;
import cn.czy15.zyweb.vo.req.PermissionAddReqVO;
import cn.czy15.zyweb.vo.req.PermissionUpdateReqVO;
import cn.czy15.zyweb.vo.resp.PermissionRespNodeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Api(tags = "组织管理-菜单权限管理",description = "菜单权限管理相关接口")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    @GetMapping("/permissions")
    @ApiOperation(value = "获取所有的菜单权限数据接口")
    @MyLog(title = "组织管理-菜单权限管理",action = "获取所有的菜单权限数据接口")
    @RequiresPermissions("sys:permission:list")
    public DataResult<List<SysPermission>> getAllPermission(){
        DataResult result=DataResult.success();
        result.setData(permissionService.selectAll());
        return result;
    }

    @GetMapping("/permissions/tree/detail")
    @ApiOperation(value = "获取所有的菜单权限数据树接口-递归查询")
    @MyLog(title = "组织管理-菜单权限管理",action = "获取所有的菜单权限数据接口-递归查询")
    @RequiresPermissions("sys:permission:list")
    public DataResult<List<SysPermission>> getPermissionByRecursion(){
        DataResult result=DataResult.success();
        result.setData(permissionService.selectAllByDetail());
        return result;
    }

    @GetMapping("/permission/tree")
    @ApiOperation(value = "菜单权限树接口-只递归查询到菜单接口")
    @MyLog(title = "组织管理-菜单权限管理",action = "只递归查询到菜单接口")
    @RequiresPermissions(value = {"sys:permission:update","sys:permission:add"},logical = Logical.OR)
    public DataResult<List<PermissionRespNodeVO>> getAllPermissionTreeExBtn(){
        DataResult result=DataResult.success();
        result.setData(permissionService.selectAllMenuByTree());
        return result;
    }

    @PostMapping("/permission")
    @ApiOperation(value = "新增菜单权限接口")
    @MyLog(title = "组织管理-菜单权限管理",action = "新增菜单权限接口")
    @RequiresPermissions("sys:permission:add")
    public DataResult<SysPermission> addPermission(@RequestBody @Valid PermissionAddReqVO vo){
        DataResult result=DataResult.success();
        result.setData(permissionService.addPermission(vo));
        return result;
    }

    @GetMapping("/permission/tree/all")
    @ApiOperation(value = "菜单权限树接口-只递归查询所有接口")
    @MyLog(title = "组织管理-菜单权限管理",action = "只递归查询所有接口")
    @RequiresPermissions(value = {"sys:role:update","sys:role:add"},logical = Logical.OR)
    public DataResult<List<PermissionRespNodeVO>> getAllPermissionTree(){
        DataResult result=DataResult.success();
        result.setData(permissionService.selectAllTree());
        return result;
    }


    @PutMapping("/permission")
    @ApiOperation(value = "编辑菜单权限接口")
    @MyLog(title = "组织管理-菜单权限管理",action = "编辑菜单权限接口")
    @RequiresPermissions("sys:permission:update")
    public DataResult updatePermission(@RequestBody @Valid PermissionUpdateReqVO vo){
        permissionService.updatePermission(vo);
        DataResult result=DataResult.success();
        return result;
    }

    @DeleteMapping("/permission/{permissionId}")
    @ApiOperation(value = "删除菜单权限接口")
    @MyLog(title = "组织管理-菜单权限管理",action = "删除菜单权限接口")
    @RequiresPermissions("sys:permission:delete")
    public DataResult deletedPermission(@PathVariable("permissionId") String permissionId){
        DataResult result=DataResult.success();
        permissionService.deletedPermission(permissionId);
        return result;
    }
}
