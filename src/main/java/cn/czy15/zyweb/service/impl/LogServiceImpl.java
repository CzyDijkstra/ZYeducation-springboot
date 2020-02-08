package cn.czy15.zyweb.service.impl;


import cn.czy15.zyweb.entity.SysLog;
import cn.czy15.zyweb.exception.BusinessException;
import cn.czy15.zyweb.exception.code.BaseResponseCode;
import cn.czy15.zyweb.mapper.SysLogMapper;
import cn.czy15.zyweb.service.LogService;
import cn.czy15.zyweb.utils.PageUtil;
import cn.czy15.zyweb.vo.req.SysLogPageReqVO;
import cn.czy15.zyweb.vo.resp.PageVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private SysLogMapper sysLogMapper;
    @Override
    public PageVO<SysLog> pageInfo(SysLogPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        return PageUtil.getPageVO(sysLogMapper.selectAll(vo));
    }

    @Override
    public void deletedLog(List<String> logIds) {
        int i = sysLogMapper.batchDeletedLog(logIds);
        if(i==0){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }
}
