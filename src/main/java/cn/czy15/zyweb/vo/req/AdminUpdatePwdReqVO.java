package cn.czy15.zyweb.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AdminUpdatePwdReqVO {
    @ApiModelProperty(value = "id")
    @NotBlank(message = "id不能为空")
    private String id;

    @ApiModelProperty(value = "新密码")
    @NotBlank(message = "新密码不能为空")
    private String newPwd;
}
