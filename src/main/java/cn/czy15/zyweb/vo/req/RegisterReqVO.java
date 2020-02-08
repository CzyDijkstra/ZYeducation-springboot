package cn.czy15.zyweb.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterReqVO {

    @ApiModelProperty(value = "账号")
    @NotBlank(message = "账号不能为空")
    private String username;

    @ApiModelProperty(value = "真实姓名")
    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    @ApiModelProperty(value = "昵称")
    @NotBlank(message = "昵称不能为空")
    private String nickName;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "邮箱不能为空")
    @Email
    private String email;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty(value = "创建来源(1.web 2.android 3.ios )")
    private String createWhere;

    @ApiModelProperty(value = "账户状态(1.正常 2.锁定 )")
    private Integer status;
}
