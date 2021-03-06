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
public class PermissionRespNodeVO {
    @ApiModelProperty(value = "主键id")
    private String id;
    @ApiModelProperty(value = "菜单权限名称")
    private String title;
    @ApiModelProperty(value = "接口地址")
    private String url;

    @ApiModelProperty(value = "子集集合")
    private List<?> children;

    @ApiModelProperty(value = "默认展开")
    private boolean spread;

    @ApiModelProperty(value = "节点是否选中")
    private boolean checked;
}
