package com.mashibing.dm.flyweight.demo2.pattern.v1.impl;

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

    /**
     * 为Flyweight添加子Flyweight对象
     * <br/>
     * 目的：添加组合对象的操作，主要是添加向组合对象中加入子对象。原来的内部数据对象（即享元对象）无需实现，抛出一场即可
     * @param f： 被添加的子Flyweight对象
     */
    void add(FlyWeight f);
}
