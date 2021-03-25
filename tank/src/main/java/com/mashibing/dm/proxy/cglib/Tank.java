package com.mashibing.dm.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/16 22:51
 * @version: 1.0
 ***********************/
public final class Tank {

    /**
     * 问题：我想记录坦克的移动时间
     * 最简单的办法：修改代码，记录时间
     * 问题2：如果无法改变方法源码呢？
     * 用继承？
     * v05:使用代理
     * v06:代理有各种类型
     * 问题：如何实现代理的各种组合？继承？Decorator?
     * v07:代理的对象改成Movable类型-越来越像decorator了
     * v08:如果有stop方法需要代理...
     * 如果想让LogProxy可以重用，不仅可以代理Tank，还可以代理任何其他可以代理的类型 Object
     * （毕竟日志记录，时间计算是很多方法都需要的东西），这时该怎么做呢？
     * 分离代理行为与被代理对象
     * 使用cglib的动态代理
     */

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Tank.class);
        enhancer.setCallback(new TimeMethodInterceptor());
        Tank tank = (Tank) enhancer.create();
        tank.move();
    }


    //模拟坦克移动了一段时间
    public void move() {
        System.out.println("Tank moving claclacla...");
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class TimeMethodInterceptor implements MethodInterceptor{

    /**
     * Description: cglib动态代理核心 <BR>
     *
     * @author zhao.song    2021/3/20 21:02
     * @param o:生成的代理对象(也就是tank的子类)
     * @param method:
     * @param objects:
     * @param methodProxy:
     * @return {@link java.lang.Object}
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.printf("method %s start...\n", method.getName());
        System.out.println(methodProxy.getSuperName());
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.printf("method %s end!\n", method.getName());
        return result;
    }
}
