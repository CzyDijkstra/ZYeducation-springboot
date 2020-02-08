package cn.czy15.zyweb.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Zhuoyu Chen
 * @project zyweb
 * @date 13:13
 */
@Data
public class AdminRespVO {
    @ApiModelProperty(value = "用户信息")
    private UserInfoRespVO userInfoVO;

    @ApiModelProperty(value = "首页菜单导航数据")
    private List<PermissionRespNodeVO> menus;


}
