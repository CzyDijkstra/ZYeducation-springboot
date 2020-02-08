package cn.czy15.zyweb.service;

import cn.czy15.zyweb.vo.req.UserOwnRoleReqVO;

import java.util.List;

public interface UserRoleService {

    List<String> getRoleIdsByUserId(String userId);
    void addUserRoleInfo(UserOwnRoleReqVO vo);
    List<String> getUserIdsByRoleIds(List<String> roleIds);
    List<String> getUserIdsBtRoleId(String roleId);
    int removeUserRoleId(String roleId);
}
