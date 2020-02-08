package cn.czy15.zyweb.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserInfoRespVO {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "账号")
    private String username;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    @ApiModelProperty(value = "身份id")
    private String deptId;
    //如老师、管理员、客户等等
    @ApiModelProperty(value = "身份名称")
    private String deptName;

}
