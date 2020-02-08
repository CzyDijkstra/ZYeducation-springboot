package cn.czy15.zyweb.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Zhuoyu Chen
 */
@Data
public class TestReqVO {

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "年龄")
    @NotNull(message = "年龄不能为空")
    private Integer age;

    @ApiModelProperty(value = "id集合")
    @NotEmpty(message = "id集合不能为空")
    private List<String> ids;
}
