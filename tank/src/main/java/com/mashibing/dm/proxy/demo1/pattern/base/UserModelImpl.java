package com.mashibing.dm.proxy.demo1.pattern.base;


import lombok.Data;
import lombok.ToString;

/**
 * 实现了UserModelApi
 * 说明：成员属性和成员方法和UserModel是一样的
 */
@Data
@ToString
public class UserModelImpl implements UserModelApi {

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
