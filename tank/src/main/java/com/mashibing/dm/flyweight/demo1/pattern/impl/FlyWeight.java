package com.mashibing.dm.flyweight.demo1.pattern.impl;

/**
 * description：描述授权数据的享元接口
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/29 22:54
 */
public interface FlyWeight {

    /**
     * 判断传入的安全实体和权限，是否和享元对象内部状态匹配
     *
     * @param securityEntity：安全实体
     * @param permit：权限
     * @return true表示匹配，false表示不匹配
     */
    boolean match(String securityEntity, String permit);
}
