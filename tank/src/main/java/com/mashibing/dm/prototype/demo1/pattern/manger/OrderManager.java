package com.mashibing.dm.prototype.demo1.pattern.manger;

import com.mashibing.dm.prototype.demo1.pattern.adapter.EnterpriseOderAdapter;
import com.mashibing.dm.prototype.demo1.pattern.adapter.OrderCloneable;
import com.mashibing.dm.prototype.demo1.pattern.adapter.PersonalOrderAdapter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OrderManager {

    private static final Map<String, OrderCloneable> MANAGER = new ConcurrentHashMap<String, OrderCloneable>();

    static {
        MANAGER.put("Personal", new PersonalOrderAdapter());
        MANAGER.put("Enterprise", new EnterpriseOderAdapter());
    }

    public static <T> T getOrder(String orderId, Class<T> clazz) {
        OrderCloneable cloneable = MANAGER.get(orderId);
        if (cloneable.getClass().isAssignableFrom(clazz)) {
            return (T) cloneable;
        }
        return null;
    }
}

