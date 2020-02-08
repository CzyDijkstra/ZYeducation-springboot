package cn.czy15.zyweb.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class DeptUpdateReqVO {
    @ApiModelProperty(value = "身份id")
    @NotBlank(message = "身份id不能为空")
    private String id;
    @ApiModelProperty(value = "身份呢名称")
    private String name;
    @ApiModelProperty(value = "父级id")
    private String pid;
    @ApiModelProperty(value = "身份状态(1:正常；0:弃用)")
    private Integer status;
    @ApiModelProperty(value = "身份总管名称")
    private String managerName;
    @ApiModelProperty(value = "身份总管电话")
    private String phone;
}
