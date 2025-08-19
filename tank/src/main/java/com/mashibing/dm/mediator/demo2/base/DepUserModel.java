package com.mashibing.dm.mediator.demo2.base;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 描述部门和人员关系的类
 */
@Data
@Accessors(chain = true)
public class DepUserModel {

    /**
     * 用于部门和人员关系的编号，用作主键
     */
    private String depUserId;

    /**
     * 部门的编号
     */
    private String depId;

    /**
     * 人员的编号
     */
    private String userId;
}
