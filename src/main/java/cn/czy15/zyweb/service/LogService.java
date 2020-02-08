package cn.czy15.zyweb.service;

import cn.czy15.zyweb.entity.SysLog;
import cn.czy15.zyweb.vo.req.SysLogPageReqVO;
import cn.czy15.zyweb.vo.resp.PageVO;

import java.util.List;

public interface LogService {

    PageVO<SysLog> pageInfo(SysLogPageReqVO vo);

    void deletedLog(List<String> logIds);
}
