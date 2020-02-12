package cn.czy15.zyweb.controller;


import cn.czy15.zyweb.aop.annotation.MyLog;
import cn.czy15.zyweb.entity.SysCourse;
import cn.czy15.zyweb.entity.SysRate;
import cn.czy15.zyweb.entity.SysRole;
import cn.czy15.zyweb.entity.SysUser;
import cn.czy15.zyweb.service.CourseService;
import cn.czy15.zyweb.utils.DataResult;
import cn.czy15.zyweb.vo.req.*;
import cn.czy15.zyweb.vo.resp.PageVO;
import cn.czy15.zyweb.vo.resp.RateVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Zhuoyu Chen
 * @project zyweb
 * @date 15:10
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
@Api(tags = "课程模块",description = "课程内容模块 CRUD")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/course")
    @ApiOperation(value = "新增课程接口")
    @MyLog(title = "教学管理-课程管理",action = "新增课程接口")
    @RequiresPermissions("sys:course:add")
    public DataResult<SysCourse> addCourse(@RequestBody @Valid CourseAddReqVO vo){
        DataResult dataResult = DataResult.success();
        dataResult.setData(courseService.addCourse(vo));
        return dataResult;
    }

    @DeleteMapping("/course")
    @ApiOperation(value = "删除课程(批量)")
    @MyLog(title = "教学管理-删除管理",action = "删除课程接口")
    @RequiresPermissions("sys:course:delete")
    public DataResult deletedCourse(@RequestBody @ApiParam(value = "日志id集合") DeleteIdReqVO vo){
        courseService.deletedCourses(vo.getList());
        DataResult dataResult =DataResult.success();
        return dataResult;
    }

    @PutMapping("/course")
    @ApiOperation(value = "更新课程数据接口")
    @MyLog(title = "组织管理-课程管理",action = "更新课程数据接口")
    @RequiresPermissions("sys:course:update")
    public DataResult updateCourse(@RequestBody @Valid CourseUpdateReqVO vo){
        courseService.updateCourse(vo);
        DataResult result=DataResult.success();
        return result;
    }
    @PostMapping("/courses")
    @ApiOperation(value = "分页查询课程接口-简略")
    public DataResult<PageVO<SysCourse>> pageInfo_quick(@RequestBody CoursePageReqVO vo){
        DataResult result=DataResult.success();
//        PageVO<SysCourse> sysCoursePageVO = courseService.pageInfo_quick(vo);
        result.setData(courseService.pageInfo_quick(vo));
        return result;

    }

    @PostMapping("/courses/all")
    @ApiOperation(value = "分页查询课程接口-all")
    public DataResult<PageVO<SysCourse>> pageInfo_all(@RequestBody CoursePageReqVO vo){
        DataResult result=DataResult.success();
        result.setData(courseService.pageInfo_all(vo));
        return result;
    }

    @GetMapping("/rate")
    @ApiOperation(value = "查询排行榜")
    public DataResult<RateVO<SysRate>> Rate(){
        DataResult result=DataResult.success();
        result.setData(courseService.RedisRate_lastThreeDay());
        return result;
    }

    @GetMapping("/course/detail/{courseId}")
    @ApiOperation(value = "查询课程详情")
    public DataResult<SysCourse> detailInfo(@PathVariable("courseId")String courseId){
        DataResult result=DataResult.success();
        SysCourse sysCourse = courseService.detailInfo(courseId);
        result.setData(sysCourse);
        try {
            courseService.ADDRedisRate(sysCourse.getName(),sysCourse.getId());
        }catch (Exception e){}
        finally {
            return result;
        }
    }

}
