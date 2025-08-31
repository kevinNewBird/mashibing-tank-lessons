package com.mashibing.dm.flyweight.demo1.pattern.impl;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;

/**
 * description：封装授权数据中重复出现部分的享元对象（内部数据：基本上不变化）
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/29 22:56
 */
public class AuthorizationFlyweight implements FlyWeight {


    /**
     * 内部状态，安全实体
     */
    @Getter
    private String securityEntity;

    /**
     * 内部状态，权限
     */
    @Getter
    private String permit;


    /**
     * 构造方法，传入状态数据
     *
     * @param state： 状态数据，包含安全梯和权限的数据，用“，”分隔
     */
    public AuthorizationFlyweight(String state) {
        String[] authArray = StringUtils.splitByWholeSeparator(state, ",");
        this.securityEntity = authArray[0];
        this.permit = authArray[1];
    }


    /**
     * 判断传入安全实体和权限是否匹配内部数据
     *
     * @param securityEntity：安全实体
     * @param permit：权限
     * @return
     */
    @Override
    public boolean match(String securityEntity, String permit) {
        return StringUtils.equals(this.securityEntity, securityEntity)
                && StringUtils.equals(this.permit, permit);
    }
}
