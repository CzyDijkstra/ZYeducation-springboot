package cn.czy15.zyweb.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CoursePageReqVO {
    @ApiModelProperty(value = "第几页")
    private int pageNum = 1;
    @ApiModelProperty(value = "当前页的数量")
    private int pageSize;

    @ApiModelProperty(value = "课程id")
    private String courseId;
    @ApiModelProperty(value = "课程名称")
    private String courseName;
    @ApiModelProperty(value = "课程状态(1:正常0:弃用)")
    private Integer status;
    @ApiModelProperty(value = "开始时间")
    private String startTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;

}
