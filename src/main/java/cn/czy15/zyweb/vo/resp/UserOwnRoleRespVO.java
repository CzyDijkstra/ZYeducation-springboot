package cn.czy15.zyweb.vo.resp;

import cn.czy15.zyweb.entity.SysRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
public class UserOwnRoleRespVO {

    @ApiModelProperty(value = "拥有角色集合")
    private List<String> ownRoles;

    @ApiModelProperty(value = "所有角色列表")
    private List<SysRole> allRole;
}
