package cn.czy15.zyweb.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class DeleteIdReqVO {

    @ApiModelProperty(value = "要删除的id(单个或者批量)")
    private List<String> list;
}
