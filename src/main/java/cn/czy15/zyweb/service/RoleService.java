package cn.czy15.zyweb.service;

import cn.czy15.zyweb.entity.SysRole;
import cn.czy15.zyweb.vo.req.AddRoleReqVO;
import cn.czy15.zyweb.vo.req.RolePageReqVO;
import cn.czy15.zyweb.vo.req.RoleUpdateReqVO;
import cn.czy15.zyweb.vo.resp.PageVO;

import java.util.List;


public interface RoleService {
    PageVO<SysRole> pageInfo(RolePageReqVO vo);
    SysRole addRole(AddRoleReqVO vo);
    List<SysRole> selectAll();
    SysRole detailInfo(String id);
    void updateRole(RoleUpdateReqVO vo);
    void deletedRole(String roleId);
    List<String> getRoleNames(String userId);
    List<SysRole> getRoleInfoByUserId(String userId);
}
