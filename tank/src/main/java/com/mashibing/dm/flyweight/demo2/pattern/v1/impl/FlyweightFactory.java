package com.mashibing.dm.flyweight.demo2.pattern.v1.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * description：享元工厂，通常实现为单例
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/29 23:02
 */
public class FlyweightFactory {

    /**
     * 缓存多个Flyweight对象
     */
    private static Map<String, FlyWeight> fsMap = new HashMap<>();

    private FlyweightFactory() {
    }

    public static FlyweightFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * 获取key对应的享元对象
     * @param key：获取享元对象的key
     * @return key对应的享元对象
     */
    public FlyWeight getFlyweight(String key) {
        FlyWeight f = fsMap.get(key);

        if (null == f) {
            f = new AuthorizationFlyweight(key);
            fsMap.put(key, f);
        }

        return f;
    }

    private static class InstanceHolder {
        public static final FlyweightFactory INSTANCE = new FlyweightFactory();
    }

}
