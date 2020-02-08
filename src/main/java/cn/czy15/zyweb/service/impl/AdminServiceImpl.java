package cn.czy15.zyweb.service.impl;

import cn.czy15.zyweb.entity.SysUser;
import cn.czy15.zyweb.mapper.SysUserMapper;
import cn.czy15.zyweb.service.AdminService;
import cn.czy15.zyweb.service.PermissionService;
import cn.czy15.zyweb.vo.resp.AdminRespVO;
import cn.czy15.zyweb.vo.resp.PermissionRespNodeVO;
import cn.czy15.zyweb.vo.resp.UserInfoRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zhuoyu Chen
 * @project zyweb
 * @date 13:26
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PermissionService permissionService;

    @Override
    public AdminRespVO getAdminHomeInfo(String userId) {
        AdminRespVO adminRespVO = new AdminRespVO();
        List<PermissionRespNodeVO> list = permissionService.permissionTreeList(userId);
        adminRespVO.setMenus(list);
        SysUser sysUser =sysUserMapper.selectByPrimaryKey(userId);
        UserInfoRespVO respVO = new UserInfoRespVO();
        if (sysUser != null) {
            respVO.setUsername(sysUser.getUsername());
            respVO.setPhone(sysUser.getPhone());
            respVO.setNickName(sysUser.getNickName());
            respVO.setRealName(sysUser.getRealName());
            //权限组
            respVO.setDeptName(sysUser.getDeptId());
            respVO.setId(sysUser.getId());
        }
        adminRespVO.setUserInfoVO(respVO);
        return adminRespVO;
    }
}



