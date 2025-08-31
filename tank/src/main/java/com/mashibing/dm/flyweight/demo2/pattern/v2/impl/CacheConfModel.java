package com.mashibing.dm.flyweight.demo2.pattern.v2.impl;

import lombok.Data;

/**
 * description：描述享元对象缓存的配置对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/31 10:12
 */
@Data
public class CacheConfModel {

    /**
     * 缓存开始计时的开始时间
     */
    private long beginTime;

    /**
     * 缓存对象存放的持续时间，其实就是最长不被使用的时间
     */
    private double durableTime;

    /**
     * 缓存对象需要被永久存储，也就是不需要从缓存中删除
     */
    private boolean forever;
}
