package cn.czy15.zyweb.service.impl;

import cn.czy15.zyweb.entity.SysUserRole;
import cn.czy15.zyweb.exception.BusinessException;
import cn.czy15.zyweb.exception.code.BaseResponseCode;
import cn.czy15.zyweb.mapper.SysUserRoleMapper;
import cn.czy15.zyweb.service.UserRoleService;
import cn.czy15.zyweb.vo.req.UserOwnRoleReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Override
    public List<String> getRoleIdsByUserId(String userId) {
        return sysUserRoleMapper.getRoleIdsByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUserRoleInfo(UserOwnRoleReqVO vo) {
        //删除他们关联数据
        sysUserRoleMapper.removeRoleByUserId(vo.getUserId());
        if(vo.getRoleIds()==null||vo.getRoleIds().isEmpty()){
            return;
        }
        List<SysUserRole> list=new ArrayList<>();
        for (String roleId:
             vo.getRoleIds()) {
            SysUserRole sysUserRole=new SysUserRole();
            sysUserRole.setId(UUID.randomUUID().toString());
            sysUserRole.setCreateTime(new Date());
            sysUserRole.setUserId(vo.getUserId());
            sysUserRole.setRoleId(roleId);
            list.add(sysUserRole);
        }
        int i = sysUserRoleMapper.batchInsertUserRole(list);
        if(i==0){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    @Override
    public List<String> getUserIdsByRoleIds(List<String> roleIds) {
        return sysUserRoleMapper.getUserIdsByRoleIds(roleIds);
    }

    @Override
    public List<String> getUserIdsBtRoleId(String roleId) {
        return sysUserRoleMapper.getUserIdsByRoleId(roleId);
    }

    @Override
    public int removeUserRoleId(String roleId) {
        return sysUserRoleMapper.removeUserRoleId(roleId);
    }
}
