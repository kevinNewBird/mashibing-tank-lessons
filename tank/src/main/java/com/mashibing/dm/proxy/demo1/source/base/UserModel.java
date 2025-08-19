package com.mashibing.dm.proxy.demo1.source.base;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserModel {

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 用户姓名
     */
    private String userName;


    /**
     * 性别
     */
    private String sex;

    /**
     * 部门编号
     */
    private String depId;
}
