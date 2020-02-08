package cn.czy15.zyweb.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Zhuoyu Chen
 * @project zyweb
 * @date 13:22
 */
@Data
public class PermissionDetailRespNodeVO {
    @ApiModelProperty(value = "主键id")
    private String id;
    @ApiModelProperty(value = "菜单权限名称")
    private String title;
    @ApiModelProperty(value = "接口地址")
    private String url;
    @ApiModelProperty(value = "类型")
    private Integer type;
    @ApiModelProperty(value = "请求方式")
    private String method;

    @ApiModelProperty(value = "父级名称")
    private String pidName;

    @ApiModelProperty(value = "排序数字")
    private Integer orderNum;

    @ApiModelProperty(value = "前后端分离按钮")
    private String code;


    @ApiModelProperty(value = "资源标识")
    private String perms;


    @ApiModelProperty(value = "状态 1 正常 2 禁用")
    private Integer status;


    @ApiModelProperty(value = "子集集合")
    private List<?> children;

    @ApiModelProperty(value = "默认展开")
    private boolean spread;

    @ApiModelProperty(value = "节点是否选中")
    private boolean checked;
}
