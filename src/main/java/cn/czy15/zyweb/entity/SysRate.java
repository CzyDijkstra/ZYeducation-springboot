package cn.czy15.zyweb.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Zhuoyu Chen
 * @project zyweb
 * @date 14:17
 */
@Data
public class SysRate implements Serializable {
    // 点击量
    private int grade;

    // id
    private String id;
    // 名称
    private String name;
    //排名
    private int order;
}
