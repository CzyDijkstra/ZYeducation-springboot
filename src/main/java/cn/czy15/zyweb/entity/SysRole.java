package cn.czy15.zyweb.entity;

import cn.czy15.zyweb.vo.resp.PermissionRespNodeVO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String description;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    private List<PermissionRespNodeVO> permissionRespNode;
}