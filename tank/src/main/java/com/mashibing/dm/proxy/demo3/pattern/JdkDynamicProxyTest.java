package com.mashibing.dm.proxy.demo3.pattern;

import com.mashibing.dm.proxy.demo2.pattern.base.Order;
import com.mashibing.dm.proxy.demo2.pattern.base.OrderApi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 对demo2的静态代理使用jdk内建的动态代理进行实现
 */
public class JdkDynamicProxyTest {

    /**
     * 1.要实现InvocationHandler接口。
     * 2.需要提供一个方法来实现：把具体的目标对象和动态代理绑定起来，并在绑定好过后，返回被代理的目标对象的接口，以利于客户端的操作。
     * 3.需要实现invoke方法，在这个方法里面，具体判断当前是在调用什么方法，需要如何处理。
     * @param args
     */
    public static void main(String[] args) {
        Order order = new Order("设计模式", 100, "赵四");
        OrderApi proxy = (OrderApi) Proxy.newProxyInstance(order.getClass().getClassLoader(), order.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 如果是调用setter方法就需要检查权限
                if (method.getName().startsWith("set")) {
                    // 如果不是创建人，那就不能修改
                    if (order.getOrderUser() != null && order.getOrderUser().equals(args[1])) {
                        return method.invoke(order, args);
                    } else {
                        System.out.printf("对不起， %s，您无权修改本订单中的数据\n", args[1]);
                    }
                } else {
                    // 不是调用的setter方法就继续运行
                    return method.invoke(order, args);
                }
                return null;
            }
        });

        // 2.李四想要修改，那就回报错
        proxy.setOrderNum(123, "李四");

        // 3.张三修改就不会有问题
        proxy.setOrderNum(123,"张三");

        // 4.再次输出order
        System.out.println("张三修改后，订单记录："+ proxy);
    }
}
