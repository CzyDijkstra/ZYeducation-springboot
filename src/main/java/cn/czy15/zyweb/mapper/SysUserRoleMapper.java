package cn.czy15.zyweb.mapper;

import cn.czy15.zyweb.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

    List<String> getRoleIdsByUserId(String userId);

    int removeRoleByUserId(String userId);

    int batchInsertUserRole(List<SysUserRole> list);

    List<String> getUserIdsByRoleIds(List<String> roleIds);

    List<String> getUserIdsByRoleId(String roleId);


    int removeUserRoleId(String roleId);


}