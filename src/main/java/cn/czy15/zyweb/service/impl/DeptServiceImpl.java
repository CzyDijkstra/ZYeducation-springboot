package cn.czy15.zyweb.service.impl;

import cn.czy15.zyweb.constant.Constant;
import cn.czy15.zyweb.entity.SysDept;
import cn.czy15.zyweb.entity.SysPermission;
import cn.czy15.zyweb.entity.SysUser;
import cn.czy15.zyweb.exception.BusinessException;
import cn.czy15.zyweb.exception.code.BaseResponseCode;
import cn.czy15.zyweb.mapper.SysDeptMapper;
import cn.czy15.zyweb.service.DeptService;
import cn.czy15.zyweb.service.RedisService;
import cn.czy15.zyweb.service.UserService;
import cn.czy15.zyweb.utils.CodeUtil;
import cn.czy15.zyweb.vo.req.DeptAddReqVO;
import cn.czy15.zyweb.vo.req.DeptUpdateReqVO;
import cn.czy15.zyweb.vo.resp.DeptRespDetailNodeVO;
import cn.czy15.zyweb.vo.resp.DeptRespNodeVO;
import cn.czy15.zyweb.vo.resp.PermissionDetailRespNodeVO;
import com.alibaba.druid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class DeptServiceImpl implements DeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    @Override
    public List<SysDept> selectAll() {
        List<SysDept> list=sysDeptMapper.selectAll();
        for (SysDept s:
             list) {
            SysDept parent = sysDeptMapper.selectByPrimaryKey(s.getPid());
            if(parent!=null){
                s.setPidName(s.getName());
            }
        }
        return list;
    }

    @Override
    public List<DeptRespDetailNodeVO> selectAllByTree() {
        List<SysDept> list=sysDeptMapper.selectAll();
        //我要想去掉这个部门的叶子节点，直接在数据源移除这个部门就可以了
//        if(!StringUtils.isEmpty(deptId)&&!list.isEmpty()){
//            for (SysDept s:
//                    list) {
//                if(s.getId().equals(deptId)){
//                    list.remove(s);
//                    break;
//                }
//            }
//        }
        DeptRespDetailNodeVO respNodeVO=new DeptRespDetailNodeVO();
        respNodeVO.setId("0");
        respNodeVO.setTitle("默认顶级部门");
        respNodeVO.setChildren(getTrees(list));
        List<DeptRespDetailNodeVO> result=new ArrayList<>();
        result.add(respNodeVO);
        return result;
    }

    @Override
    public List<DeptRespNodeVO> deptTreeList(String deptId) {
        List<SysDept> list=sysDeptMapper.selectAll();
        //我要想去掉这个部门的叶子节点，直接在数据源移除这个部门就可以了
        if(!StringUtils.isEmpty(deptId)&&!list.isEmpty()){
            for (SysDept s:
                 list) {
                if(s.getId().equals(deptId)){
                    list.remove(s);
                    break;
                }
            }
        }
        DeptRespNodeVO respNodeVO=new DeptRespNodeVO();
        respNodeVO.setId("0");
        respNodeVO.setTitle("默认顶级部门");
        respNodeVO.setChildren(getTree(list));
        List<DeptRespNodeVO> result=new ArrayList<>();
        result.add(respNodeVO);
        return result;
    }

    private List<DeptRespNodeVO> getTree(List<SysDept> all){
        List<DeptRespNodeVO> list=new ArrayList<>();
        for (SysDept s:
                all) {
            if(s.getPid().equals("0")){
                DeptRespNodeVO respNodeVO=new DeptRespNodeVO();
                respNodeVO.setId(s.getId());
                respNodeVO.setTitle(s.getName());
                respNodeVO.setChildren(getChild(s.getId(),all));
                list.add(respNodeVO);
            }
        }
        return list;
    }

    private List<DeptRespDetailNodeVO> getTrees(List<SysDept> all){
        List<DeptRespDetailNodeVO> list=new ArrayList<>();
        for (SysDept s:
                all) {
            if(s.getPid().equals("0")){
                DeptRespDetailNodeVO respNodeVO = new DeptRespDetailNodeVO();
                BeanUtils.copyProperties(s, respNodeVO);
                respNodeVO.setTitle(s.getName());
                respNodeVO.setChildren(getChilds(s.getId(), all));
                list.add(respNodeVO);
            }
        }
        return list;
    }

    private List<DeptRespNodeVO> getChild(String id,List<SysDept> all){
        List<DeptRespNodeVO> list=new ArrayList<>();
        for (SysDept s :
                all) {
            if(s.getPid().equals(id)){
                DeptRespNodeVO deptRespNodeVO=new DeptRespNodeVO();
                deptRespNodeVO.setId(s.getId());
                deptRespNodeVO.setTitle(s.getName());
                deptRespNodeVO.setChildren(getChild(s.getId(),all));
                list.add(deptRespNodeVO);

            }
        }
        return list;
    }

    private List<DeptRespDetailNodeVO> getChilds(String id,List<SysDept> all){
        List<DeptRespDetailNodeVO> list=new ArrayList<>();
        for (SysDept s :
                all) {
            if(s.getPid().equals(id)){
                DeptRespDetailNodeVO deptRespNodeVO=new DeptRespDetailNodeVO();
                BeanUtils.copyProperties(s, deptRespNodeVO);
                deptRespNodeVO.setTitle(s.getName());
                deptRespNodeVO.setChildren(getChild(s.getId(),all));
                list.add(deptRespNodeVO);

            }
        }
        return list;
    }

    @Override
    public SysDept addDept(DeptAddReqVO vo) {
        String relationCode;
        long deptCount=redisService.incrby(Constant.DEPT_CODE_KEY,1);
        String deptCode= CodeUtil.deptCode(String.valueOf(deptCount),7,"0");
        SysDept parent=sysDeptMapper.selectByPrimaryKey(vo.getPid());
        if(vo.getPid().equals("0")){
            relationCode=deptCode;
        }else if(null==parent){
            log.info("父级数据不存在{}",vo.getPid());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }else {
            relationCode=parent.getRelationCode()+deptCode;
        }
        SysDept sysDept=new SysDept();
        BeanUtils.copyProperties(vo,sysDept);
        sysDept.setId(UUID.randomUUID().toString());
        sysDept.setCreateTime(new Date());
        sysDept.setDeptNo(deptCode);
        sysDept.setRelationCode(relationCode);
        int i = sysDeptMapper.insertSelective(sysDept);
        if(i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return sysDept;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDept(DeptUpdateReqVO vo) {
        //保存更新的部门数据
        SysDept sysDept=sysDeptMapper.selectByPrimaryKey(vo.getId());
        if(null==sysDept){
            log.error("传入 的 id:{}不合法",vo.getId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        SysDept update=new SysDept();
        BeanUtils.copyProperties(vo,update);
        update.setUpdateTime(new Date());
        int count=sysDeptMapper.updateByPrimaryKeySelective(update);
        if(count!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }

        //就是维护层级关系
        if(!vo.getPid().equals(sysDept.getPid())){
            //子集的部门层级关系编码=父级部门层级关系编码+它本身部门编码
            SysDept newParent=sysDeptMapper.selectByPrimaryKey(vo.getPid());
                if(!vo.getPid().equals("0")&&null==newParent){
                    log.info("修改后的部门在数据库查找不到{}",vo.getPid());
                    throw new BusinessException(BaseResponseCode.DATA_ERROR);
                }
                SysDept oldParent=sysDeptMapper.selectByPrimaryKey(sysDept.getPid());
                String oleRelation;
                String newRelation;
            /**
             * 根目录挂靠到其它目录
             */
            if(sysDept.getPid().equals("0")){
                oleRelation=sysDept.getRelationCode();
                newRelation=newParent.getRelationCode()+sysDept.getDeptNo();
            }else if(vo.getPid().equals("0")){
                oleRelation=sysDept.getRelationCode();
                newRelation=sysDept.getDeptNo();
            }else {
                oleRelation=oldParent.getRelationCode();
                newRelation=newParent.getRelationCode();
            }
            sysDeptMapper.updateRelationCode(oleRelation,newRelation,sysDept.getRelationCode());
        }
        }

    @Override
    public void deletedDept(String id) {
        //查找它和它的叶子节点
        SysDept sysDept=sysDeptMapper.selectByPrimaryKey(id);
        if(sysDept==null){
            log.info("传入的部门id在数据库不存在{}",id);
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        List<String> list = sysDeptMapper.selectChildIds(sysDept.getRelationCode());
        //判断它和它子集的叶子节点是否关联有用户
        List<SysUser> sysUsers = userService.selectUserInfoByDeptIds(list);
        if(!sysUsers.isEmpty()){
            throw new BusinessException(BaseResponseCode.NOT_PERMISSION_DELETED_DEPT);
        }

        //逻辑删除部门数据
        int count=sysDeptMapper.deletedDepts(new Date(),list);
        if(count==0){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }
}
