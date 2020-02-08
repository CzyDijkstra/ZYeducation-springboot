package cn.czy15.zyweb.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author Zhuoyu Chen
 * @project zyweb
 * @date 15:35
 */
@Data
public class CourseAddReqVO {

    @ApiModelProperty(value = "排序码")
    private Integer ordernum;

    @ApiModelProperty(value = "教师Id")
    private String teacherId;

    @ApiModelProperty(value = "课程名称")
    private String name;

    @ApiModelProperty(value = "课程描述")
    private String description;

    @ApiModelProperty(value = "账户状态(1.正常 2.锁定 )")
    private Integer status;

    @ApiModelProperty(value = "推荐状态(2.高级推荐1:推荐0:普通)")
    private Integer hot;

    @ApiModelProperty(value = "标签")
    private String span;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "示例代码")
    private String simplecode;
}
