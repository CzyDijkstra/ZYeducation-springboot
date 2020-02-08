package cn.czy15.zyweb.service;

import cn.czy15.zyweb.vo.resp.AdminRespVO;
import cn.czy15.zyweb.vo.resp.PermissionRespNodeVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Zhuoyu Chen
 * @project zyweb
 * @date 13:25
 */
public interface AdminService {

    AdminRespVO getAdminHomeInfo(String userId);


}
