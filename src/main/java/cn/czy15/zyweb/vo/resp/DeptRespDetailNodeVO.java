package cn.czy15.zyweb.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
public class DeptRespDetailNodeVO {

    @ApiModelProperty(value = "部门id")
    private String id;

    @ApiModelProperty(value = "部门名称")
    private String title;


    @ApiModelProperty(value = "状态 1 正常 2 禁用")
    private Integer status;

    @ApiModelProperty("是否展开 默认true")
    private boolean spread=true;

    @ApiModelProperty(value = "子集叶子节点")
    private List<?> children;
    @ApiModelProperty(value = "部门编码")
    private String deptNo;
    @ApiModelProperty(value = "父级id")
    private String pid;
    @ApiModelProperty(value = "父级名称")
    private String pidName;
    @ApiModelProperty(value = "层级关系编码")
    private String relationCode;
    @ApiModelProperty(value = "部门负责人")
    private String managerName;



}
