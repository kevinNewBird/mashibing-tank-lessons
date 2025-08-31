package com.mashibing.dm.flyweight.demo2.pattern.v2.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * description：享元工厂，通常实现为单例
 * <br/>
 * 加入实现垃圾回收和引用计数的功能
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
    /**
     * 默认保存6秒钟
     * 主要为了测试方便，这个时间可以根据应用的要求来设置
     */
    private final long DURABLE_TIME = 6 * 1000L;
    /**
     * 用来缓存被共享对象的缓存配置，key值和上面的map一样
     */
    private Map<String, CacheConfModel> cacheConfMap = new HashMap<>();
    /**
     * 用来记录缓存对象被引用的次数，key值和上面map一样
     */
    private Map<String, Integer> countMap = new HashMap<>();

    private FlyweightFactory() {
        // 启动清除缓存值的线程
        ClearCache clearCache = new ClearCache();
        clearCache.start();
    }

    public static FlyweightFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * 获取key对应的享元对象
     *
     * @param key：获取享元对象的key
     * @return key对应的享元对象
     */
    public synchronized FlyWeight getFlyweight(String key) {
        FlyWeight f = fsMap.get(key);

        if (null == f) {
            f = new AuthorizationFlyweight(key);
            fsMap.put(key, f);

            // 新增缓存
            saveCache(key, true);
        } else {
            // 更新缓存
            saveCache(key, false);
        }

        return f;
    }

    /**
     * 获取某个享元被使用的次数
     *
     * @param key：享元的key
     * @return： 被使用的次数
     */
    public synchronized int getUserTimes(String key) {
        Integer count = countMap.get(key);
        if (count == null) {
            count = 0;
        }
        return count;
    }

    public void saveCache(String key, boolean isNew) {
        if (isNew) {
            // 同时设置引用计数
            countMap.put(key, 1);
            // 同时设置缓存配置数据
            CacheConfModel cm = new CacheConfModel();
            cm.setBeginTime(System.currentTimeMillis());
            cm.setForever(false);
            cm.setDurableTime(DURABLE_TIME);

            cacheConfMap.put(key, cm);
        } else {
            // 表示还在使用，那么应该重新设置缓存配置
            CacheConfModel cm = cacheConfMap.get(key);
            cm.setBeginTime(System.currentTimeMillis());
            this.cacheConfMap.put(key, cm);

            // 同时计数+1
            Integer count = countMap.get(key);
            countMap.put(key, ++count);
        }
    }

    /**
     * 删除 key 对应的享元对象，连带清除对应的缓存配置和引用次数的记录，不对外
     *
     * @param key： 要删除的享元对象的key
     */
    private synchronized void removeFlyweight(String key) {
        fsMap.remove(key);
        this.cacheConfMap.remove(key);
        this.countMap.remove(key);
    }

    private static class InstanceHolder {
        public static final FlyweightFactory INSTANCE = new FlyweightFactory();
    }

    /**
     * 维护清除缓存的线程，内部使用
     */
    private class ClearCache extends Thread {
        @Override
        public void run() {
            while (true) {
                Set<String> tempSet = new HashSet<>();
                Set<String> set = cacheConfMap.keySet();
                for (String key : set) {
                    CacheConfModel ccm = cacheConfMap.get(key);
                    if (System.currentTimeMillis() - ccm.getBeginTime() >= ccm.getDurableTime()) {
                        // 可以清除，先记录下来
                        tempSet.add(key);
                    }
                }

                // 真正清除
                for (String key : tempSet) {
                    FlyweightFactory.getInstance().removeFlyweight(key);
                }

                System.out.printf("now thread=%s,fsMap==%s\n", fsMap.size(), fsMap.keySet());

                // 休息1秒钟再重新判断
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
