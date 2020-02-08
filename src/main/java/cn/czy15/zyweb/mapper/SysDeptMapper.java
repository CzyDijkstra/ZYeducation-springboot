package cn.czy15.zyweb.mapper;

import cn.czy15.zyweb.entity.SysDept;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDeptMapper {  int deleteByPrimaryKey(String id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    List<SysDept> selectAll();

    //维护新的层级关系
    int updateRelationCode(@Param("oldStr") String oldStr, @Param("newStr") String newStr, @Param("relationCode") String relationCode);

    List<String> selectChildIds(String relationCode);

    int deletedDepts(Date updateTime, @Param("list") List<String> list);
}