package cn.czy15.zyweb.service.impl;

import cn.czy15.zyweb.entity.SysCourse;
import cn.czy15.zyweb.entity.SysRate;
import cn.czy15.zyweb.entity.SysRole;
import cn.czy15.zyweb.entity.SysCourse;
import cn.czy15.zyweb.exception.BusinessException;
import cn.czy15.zyweb.exception.code.BaseResponseCode;
import cn.czy15.zyweb.mapper.SysCourseMapper;
import cn.czy15.zyweb.service.CourseService;
import cn.czy15.zyweb.service.RedisService;
import cn.czy15.zyweb.utils.PageUtil;
import cn.czy15.zyweb.vo.req.CourseAddReqVO;
import cn.czy15.zyweb.vo.req.CoursePageReqVO;
import cn.czy15.zyweb.vo.req.CourseUpdateReqVO;
import cn.czy15.zyweb.vo.req.RolePageReqVO;
import cn.czy15.zyweb.vo.resp.PageVO;
import cn.czy15.zyweb.vo.resp.RateVO;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    RedisService redisService;

    SimpleDateFormat sdf;

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

    @Override
    public void ADDRedisRate(String CourseName, String id) {
        String str_value = new String(CourseName+'&'+id);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str_key = "recent_rate_"+sdf.format(new Date());
        if (redisService.hasKey(str_key)==false){
            redisService.zadd(str_key,1,str_value);
            redisService.expire(str_key,3,TimeUnit.DAYS);
        }
        else {
            redisService.zincrby(str_key, 1, str_value);
            redisService.zincrby("recent_rate_lastThreeDay",1,str_value);
        }
        log.info(" ==>  redis_rate_add:"+str_key+" --- "+str_value);
    }

    @Override
    public RateVO<SysRate> RedisRate_lastThreeDay(){
        Set<ZSetOperations.TypedTuple<Object>> set_recent = redisService.reverseRangeWithScores("recent_rate_lastThreeDay", 0, 10);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str_key = "recent_rate_"+sdf.format(new Date());
        Set<ZSetOperations.TypedTuple<Object>> set_now = redisService.reverseRangeWithScores(str_key,0,10);

        List<SysRate> list = new ArrayList<>();
        List<SysRate> list_recent = new ArrayList<>();

        // 最近3天
        Iterator it = set_recent.iterator();
        int index = 1;
        while (it.hasNext()) {
            ZSetOperations.TypedTuple tt = (ZSetOperations.TypedTuple) it.next();
            SysRate sysRate = new SysRate();
            String str = tt.getValue().toString();

            String[] strArr = str.split("&");
            sysRate.setId(strArr[1]);
            sysRate.setName(strArr[0]);
            sysRate.setOrder(index++);
            sysRate.setGrade(tt.getScore().intValue());
            list_recent.add(sysRate);
        }

        // 今日
        it = set_now.iterator();
        index = 1;
        while (it.hasNext()) {
            ZSetOperations.TypedTuple tt = (ZSetOperations.TypedTuple) it.next();
            SysRate sysRate = new SysRate();
            String str = tt.getValue().toString();
            String[] strArr = str.split("&");
            sysRate.setId(strArr[1]);
            sysRate.setName(strArr[0]);
            sysRate.setOrder(index++);
            sysRate.setGrade(tt.getScore().intValue());
            list.add(sysRate);
        }

        RateVO<SysRate> rote = new RateVO<>();
        rote.setLastThreeDayList(list_recent);
        rote.setTodayList(list);
        return rote;
    }



}
