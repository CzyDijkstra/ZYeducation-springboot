package cn.czy15.zyweb.controller;
import cn.czy15.zyweb.aop.annotation.MyLog;
import cn.czy15.zyweb.constant.Constant;
import cn.czy15.zyweb.entity.SysUser;
import cn.czy15.zyweb.exception.code.BaseResponseCode;
import cn.czy15.zyweb.service.UserService;
import cn.czy15.zyweb.utils.DataResult;
import cn.czy15.zyweb.utils.JwtTokenUtil;
import cn.czy15.zyweb.vo.req.*;
import cn.czy15.zyweb.vo.resp.LoginRespVO;
import cn.czy15.zyweb.vo.resp.PageVO;
import cn.czy15.zyweb.vo.resp.UserOwnRoleRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @ClassName: UserController
 */
@RestController
@RequestMapping("/api")
@Api(tags = "用户模块相关接口")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录接口")
    public DataResult<LoginRespVO> login(@RequestBody @Valid LoginReqVO vo){
        DataResult result=DataResult.success();
        result.setData(userService.login(vo));
        return result;
    }

    @GetMapping("/user/logout")
    @ApiOperation(value = "用户登出接口")
    public DataResult logout(HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String refreshToken=request.getHeader(Constant.REFRESH_TOKEN);
        userService.logout(accessToken,refreshToken);
        return DataResult.success();
    }

    @PostMapping("/user/register")
    @ApiOperation(value = "用户注册")
    public DataResult registerUser(@RequestBody @Valid RegisterReqVO addReqVO){
        userService.registerUser(addReqVO);
        return DataResult.success();
    }

    @GetMapping("/user/unLogin")
    @ApiOperation(value = "引导客户端去登录")
    public DataResult unLogin(){
        DataResult result= DataResult.getResult(BaseResponseCode.TOKEN_ERROR);
        return result;
    }

    @PostMapping("/users")
    @ApiOperation(value = "分页查询用户接口")
    @RequiresPermissions("sys:user:list")
    public DataResult<PageVO<SysUser>> pageInfo(@RequestBody UserPageReqVO vo){
        DataResult result=DataResult.success();
        result.setData(userService.pageInfo(vo));
        return result;
    }


    @PostMapping("/user")
    @ApiOperation(value = "增加用户")
    public DataResult addUser(@RequestBody @Valid UserAddReqVO addReqVO){
        userService.addUser(addReqVO);
        return DataResult.success();
    }



    @PostMapping("/user/role")
    @ApiOperation(value = "保持用户拥有的角色信息接口")
    public DataResult saveUserOwnRole(@RequestBody @Valid UserOwnRoleReqVO vo){
        userService.setUserOwnRole(vo);
        return DataResult.success();
    }

    @GetMapping("/user/token")
    @ApiOperation(value = "jwt token 刷新接口")
    @MyLog(title = "组织管理-用户管理",action = "jwt token 刷新接口")
    public DataResult<String> refreshToken(HttpServletRequest request){
        String refreshToken=request.getHeader(Constant.REFRESH_TOKEN);
        String newAccessToken = userService.refreshToken(refreshToken);
        DataResult result=DataResult.success();
        result.setData(newAccessToken);
        return result;
    }

    @GetMapping("/user/roles/{userId}")
    @ApiOperation(value = "赋予角色-获取用户拥有角色接口")
    public DataResult<UserOwnRoleRespVO> getUserOwnRole(@PathVariable("userId")String userId){
        DataResult<UserOwnRoleRespVO> result=DataResult.success();
        result.setData(userService.getUserOwnRole(userId));
        return result;
    }



    @PutMapping("/user")
    @ApiOperation(value ="列表修改用户信息接口")
    @MyLog(title = "组织管理-用户管理",action = "列表修改用户信息接口")
    @RequiresPermissions("sys:user:update")
    public DataResult updateUserInfo(@RequestBody @Valid UserUpdateReqVO vo,HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String userId= JwtTokenUtil.getUserId(accessToken);
        DataResult result=DataResult.success();
        userService.updateUserInfo(vo,userId);
        return result;
    }

    @DeleteMapping("/user")
    @ApiOperation(value = "批量/删除用户接口")
    @MyLog(title = "组织管理-用户管理",action = "批量/删除用户接口")
    // @RequiresPermissions("sys:user:delete")
    public DataResult deletedUsers(@RequestBody @Valid DeleteIdReqVO vo, HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String operationId=JwtTokenUtil.getUserId(accessToken);
        userService.deletedUsers(vo.getList(),operationId);
        DataResult result=DataResult.success();
        return result;
    }

    @GetMapping("/user/info")
    @ApiOperation(value = "用户信息详情接口")
    @MyLog(title = "组织管理-用户管理",action = "用户信息详情接口")
    public DataResult<SysUser> detailInfo(HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String id=JwtTokenUtil.getUserId(accessToken);
        DataResult result=DataResult.success();
        result.setData(userService.detailInfo(id));
        return result;
    }

    @PutMapping("/user/info")
    @ApiOperation(value = "保存个人信息接口")
    @MyLog(title = "组织管理-用户管理",action = "保存个人信息接口")
    public DataResult saveUserInfo(@RequestBody UserUpdateDetailInfoReqVO vo,HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String id=JwtTokenUtil.getUserId(accessToken);
        userService.userUpdateDetailInfo(vo,id);
        DataResult result=DataResult.success();
        return result;
    }

    @PutMapping("/user/mypwd")
    @ApiOperation(value = "修改个人密码接口")
    public DataResult updateMyPwd(@RequestBody @Valid UserUpdatePwdReqVO vo,HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String refresgToken=request.getHeader(Constant.REFRESH_TOKEN);
        userService.userUpdatePwd(vo,accessToken,refresgToken);
        DataResult result=DataResult.success();
        return result;
    }

    @PutMapping("/user/pwd")
    @ApiOperation(value = "修改指定用户密码接口")
    @MyLog(title = "组织管理-用户管理",action = "管理员修改指定密码接口")
    @RequiresPermissions("sys:user:update")
    public DataResult updatePwd(@RequestBody @Valid AdminUpdatePwdReqVO vo){
        userService.adminUpdatePwd(vo);
        DataResult result=DataResult.success();
        return result;
    }

    @PutMapping("/user/status")
    @ApiOperation(value = "修改指定用户状态接口---封禁/解封")
    @MyLog(title = "组织管理-用户管理",action = "管理员封禁/解封")
    @RequiresPermissions("sys:user:update")
    public DataResult updateStatus(@RequestBody @Valid UpdateStatusReqVO vo){
        userService.adminUpdateStatus(vo);
        DataResult result=DataResult.success();
        return result;
    }
}
