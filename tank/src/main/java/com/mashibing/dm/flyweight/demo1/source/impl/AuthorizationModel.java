package com.mashibing.dm.flyweight.demo1.source.impl;

import lombok.Data;

/**
 * description：描述授权数据的数据model
 * <br/>
 * 描述授权数据的数据对象，即人员对安全实体具有什么权限。
 * 分别对应三个属性：
 * - 人员
 * - 安全实体
 * - 权限
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/29 11:48
 */
@Data
public class AuthorizationModel {

    /**
     * 人员
     */
    private String user;

    /**
     * 安全实体
     */
    private String securityEntity;

    /**
     * 权限
     */
    private String permit;

}
