package cn.czy15.zyweb.service.impl;

import cn.czy15.zyweb.entity.SysCourse;
import cn.czy15.zyweb.entity.SysRole;
import cn.czy15.zyweb.entity.SysCourse;
import cn.czy15.zyweb.exception.BusinessException;
import cn.czy15.zyweb.exception.code.BaseResponseCode;
import cn.czy15.zyweb.mapper.SysCourseMapper;
import cn.czy15.zyweb.service.CourseService;
import cn.czy15.zyweb.utils.PageUtil;
import cn.czy15.zyweb.vo.req.CourseAddReqVO;
import cn.czy15.zyweb.vo.req.CoursePageReqVO;
import cn.czy15.zyweb.vo.req.CourseUpdateReqVO;
import cn.czy15.zyweb.vo.req.RolePageReqVO;
import cn.czy15.zyweb.vo.resp.PageVO;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Zhuoyu Chen
 * @project zyweb
 * @date 15:14
 */
@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    SysCourseMapper sysCourseMapper;

    @Override
    public void deletedCourses(List<String> list) {
        SysCourse sysCourse = new SysCourse();
        sysCourse.setUpdateTime(new Date());
        int i = sysCourseMapper.deletedCourses(sysCourse, list);
        if (i == 0) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    @Override
    public void updateCourse(CourseUpdateReqVO vo) {
        SysCourse sysCourse = new SysCourse();
        BeanUtils.copyProperties(vo, sysCourse);
        sysCourse.setUpdateTime(new Date());
        int i = sysCourseMapper.updateByPrimaryKeySelective(sysCourse);
        if (i != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    @Override
    public PageVO<SysCourse> pageInfo_quick(CoursePageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<SysCourse> list = sysCourseMapper.selectQuickAll(vo);
        return PageUtil.getPageVO(list);
    }

    @Override
    public PageVO<SysCourse> pageInfo_all(CoursePageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<SysCourse> sysCourses = sysCourseMapper.selectAll(vo);
        return PageUtil.getPageVO(sysCourses);
    }

    @Override
    public SysCourse addCourse(CourseAddReqVO vo) {
        SysCourse sysCourse = new SysCourse();
        BeanUtils.copyProperties(vo, sysCourse);
        sysCourse.setId(UUID.randomUUID().toString());
        sysCourse.setCreateTime(new Date());
        int i = sysCourseMapper.insertSelective(sysCourse);
        if (i != 1) {
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        return sysCourse;
    }

    @Override
    public SysCourse detailInfo(String id) {
        SysCourse sysCourse =sysCourseMapper.selectByPrimaryKey(id);
        if (null==sysCourse){
            log.error("传入 的 id:{}不合法",id);
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        return sysCourse;
    }
}
