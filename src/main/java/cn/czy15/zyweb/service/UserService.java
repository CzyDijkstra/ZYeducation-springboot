package cn.czy15.zyweb.service;


import cn.czy15.zyweb.entity.SysUser;
import cn.czy15.zyweb.vo.req.*;
import cn.czy15.zyweb.vo.resp.LoginRespVO;
import cn.czy15.zyweb.vo.resp.PageVO;
import cn.czy15.zyweb.vo.resp.UserOwnRoleRespVO;

import java.util.List;

public interface UserService {

    LoginRespVO login(LoginReqVO vo);

    void logout(String accessToken, String refreshToken);

    PageVO<SysUser> pageInfo(UserPageReqVO vo);

    void addUser(UserAddReqVO vo);

    UserOwnRoleRespVO getUserOwnRole(String userId);

    void setUserOwnRole(UserOwnRoleReqVO vo);

    String refreshToken(String refreshToken);

    void updateUserInfo(UserUpdateReqVO vo, String operationId);

    void deletedUsers(List<String> list, String operationId);

    List<SysUser> selectUserInfoByDeptIds(List<String> deptIds);

    SysUser detailInfo(String userId);

    //个人用户编辑信息接口
    void userUpdateDetailInfo(UserUpdateDetailInfoReqVO vo, String userId);

    void userUpdatePwd(UserUpdatePwdReqVO vo, String accessToken, String refreshToken);

    void adminUpdatePwd(AdminUpdatePwdReqVO vo);

    void adminUpdateStatus(UpdateStatusReqVO vo);

    void registerUser(RegisterReqVO addReqVO);
}
