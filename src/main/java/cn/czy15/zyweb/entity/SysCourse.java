package cn.czy15.zyweb.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysCourse implements Serializable {
    private String id;

    private Integer deleted;

    private Date updateTime;

    private Date createTime;

    private Integer ordernum;

    private String teacherId;

    private String name;

    private String description;

    private Integer status;

    private Integer hot;

    private String span;

    private String content;

    private String simplecode;
}