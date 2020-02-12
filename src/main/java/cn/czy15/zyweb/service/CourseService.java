package cn.czy15.zyweb.service;


import cn.czy15.zyweb.entity.SysCourse;
import cn.czy15.zyweb.entity.SysCourse;
import cn.czy15.zyweb.entity.SysRate;
import cn.czy15.zyweb.vo.req.CourseAddReqVO;
import cn.czy15.zyweb.vo.req.CoursePageReqVO;
import cn.czy15.zyweb.vo.req.CoursePageReqVO;
import cn.czy15.zyweb.vo.req.CourseUpdateReqVO;
import cn.czy15.zyweb.vo.resp.PageVO;
import cn.czy15.zyweb.vo.resp.RateVO;


import java.util.List;

/**
 * @author Zhuoyu Chen
 * @project zyweb
 * @date 15:14
 */

public interface CourseService {


    PageVO<SysCourse> pageInfo_all(CoursePageReqVO vo);
    PageVO<SysCourse> pageInfo_quick(CoursePageReqVO vo);
    SysCourse addCourse(CourseAddReqVO vo);
    void deletedCourses(List<String> list);
    SysCourse detailInfo(String id);
    void updateCourse(CourseUpdateReqVO vo);
    void ADDRedisRate(String CourseName,String id);
    RateVO<SysRate> RedisRate_lastThreeDay();
//    List<String> getCourseNames(String userId);
//    List<SysCourse> getCourseInfoByUserId(String userId);


}
