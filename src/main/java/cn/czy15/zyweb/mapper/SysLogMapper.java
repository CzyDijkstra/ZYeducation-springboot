package cn.czy15.zyweb.mapper;

import cn.czy15.zyweb.entity.SysLog;
import cn.czy15.zyweb.vo.req.SysLogPageReqVO;

import java.util.List;


public interface SysLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

    List<SysLog> selectAll(SysLogPageReqVO vo);


    int batchDeletedLog(List<String> logIds);


}