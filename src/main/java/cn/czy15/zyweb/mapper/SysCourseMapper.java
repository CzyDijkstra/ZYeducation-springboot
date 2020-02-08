package cn.czy15.zyweb.mapper;

import cn.czy15.zyweb.entity.SysCourse;
import cn.czy15.zyweb.entity.SysUser;
import cn.czy15.zyweb.vo.req.CoursePageReqVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysCourseMapper {


    List<SysCourse> selectQuickAll(CoursePageReqVO vo);

    List<SysCourse> selectAll(CoursePageReqVO vo);

    int deleteByPrimaryKey(String id);

    int insert(SysCourse record);

    int insertSelective(SysCourse record);

    SysCourse selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysCourse record);

    int updateByPrimaryKeyWithBLOBs(SysCourse record);

    int updateByPrimaryKey(SysCourse record);

    int deletedCourses(@Param("sysCourse") SysCourse sysCourse, @Param("list") List<String> list);
}