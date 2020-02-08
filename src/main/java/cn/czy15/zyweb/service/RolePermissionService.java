package cn.czy15.zyweb.service;

import cn.czy15.zyweb.vo.req.RolePermissionOperationReqVO;

import java.util.List;

public interface RolePermissionService {
    void addRolePermission(RolePermissionOperationReqVO vo);
    List<String> getRoleIdsByPermissionId(String permissionId);
    int removeRoleByPermissionId(String permissionId);
    List<String> getPermissionIdsByRoleId(String roleId);
    int removeByRoleId(String roleId);
    List<String> getPermissionIdsByRoles(List<String> roleIds);

}
