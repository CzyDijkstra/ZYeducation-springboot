package cn.czy15.zyweb.service;

import cn.czy15.zyweb.entity.SysDept;
import cn.czy15.zyweb.vo.req.DeptAddReqVO;
import cn.czy15.zyweb.vo.req.DeptUpdateReqVO;
import cn.czy15.zyweb.vo.resp.DeptRespDetailNodeVO;
import cn.czy15.zyweb.vo.resp.DeptRespNodeVO;

import java.util.List;

public interface DeptService {

    List<SysDept> selectAll();

    List<DeptRespNodeVO> deptTreeList(String deptId);

    SysDept addDept(DeptAddReqVO vo);

    void updateDept(DeptUpdateReqVO vo);

    void deletedDept(String id);

    List<DeptRespDetailNodeVO> selectAllByTree();
}
