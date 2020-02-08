package cn.czy15.zyweb.service;


import cn.czy15.zyweb.entity.SysPermission;
import cn.czy15.zyweb.vo.req.PermissionAddReqVO;
import cn.czy15.zyweb.vo.req.PermissionUpdateReqVO;
import cn.czy15.zyweb.vo.resp.PermissionDetailRespNodeVO;
import cn.czy15.zyweb.vo.resp.PermissionRespNodeVO;

import java.util.List;
import java.util.Set;

public interface PermissionService {
    List<SysPermission> selectAll();
    List<PermissionRespNodeVO> selectAllMenuByTree();
    SysPermission addPermission(PermissionAddReqVO vo);
    List<PermissionRespNodeVO> permissionTreeList(String userId);
    List<PermissionRespNodeVO> selectAllTree();
    List<PermissionDetailRespNodeVO> selectAllByDetail();
    void updatePermission(PermissionUpdateReqVO vo);
    void deletedPermission(String permissionId);
    Set<String> getPermissionsByUserId(String userId);
    List<SysPermission> getPermission(String userId);
}
