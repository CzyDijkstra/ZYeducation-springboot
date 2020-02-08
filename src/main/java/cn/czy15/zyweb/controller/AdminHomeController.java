package cn.czy15.zyweb.controller;

import cn.czy15.zyweb.constant.Constant;
import cn.czy15.zyweb.service.AdminService;
import cn.czy15.zyweb.utils.DataResult;
import cn.czy15.zyweb.utils.JwtTokenUtil;
import cn.czy15.zyweb.vo.resp.AdminRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zhuoyu Chen
 * @project zyweb
 * @date 13:45
 */
@RestController
@RequestMapping("/api/admin")
@Api(tags = "管理界面首页相关模块")
@CrossOrigin
public class AdminHomeController {

    @Autowired
    AdminService adminService;

    @GetMapping("/home")
    @ApiModelProperty(value = "获取管理界面首页数据")
    public DataResult<AdminRespVO> getHome(HttpServletRequest request)
    {
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String userId=JwtTokenUtil.getUserId(accessToken);
        DataResult data =DataResult.success();
        data.setData(adminService.getAdminHomeInfo(userId));
        return data;
    }
}
