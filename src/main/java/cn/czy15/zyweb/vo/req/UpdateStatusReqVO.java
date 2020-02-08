package cn.czy15.zyweb.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class UpdateStatusReqVO {
    @ApiModelProperty(value = "id")
    @NotBlank(message = "id不能为空")
    private String id;

    @ApiModelProperty(value = "账户状态(1.正常 2.锁定 )")
    private int status;
}
