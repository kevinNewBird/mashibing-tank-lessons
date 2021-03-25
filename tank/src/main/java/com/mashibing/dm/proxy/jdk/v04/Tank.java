package com.mashibing.dm.proxy.jdk.v04;

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
public class Tank implements Movable {

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
     * 使用jdk的动态代理
     */

    public static void main(String[] args) {
        Tank tank = new Tank();

        Movable m = (Movable) Proxy.newProxyInstance(

                Tank.class.getClassLoader()//被代理对象
//                , new Class[]{Movable.class}//被代理对象实现接口
                , Tank.class.getInterfaces()
//                , ((proxy, method, params) -> {
//                    System.out.printf("method %s start...\n", method.getName());
//                    Object o = method.invoke(tank, params);
//                    System.out.printf("method %s end!\n", method.getName());
//                    return o;
//                })
                , new LogHandler(tank)
        );
        m.move();
    }


    //模拟坦克移动了一段时间
    @Override
    public void move() {
        System.out.println("Tank moving claclacla...");
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

interface Movable {
    void move();
}

class LogHandler implements InvocationHandler {
    private Tank tank;

    public LogHandler(Tank tank) {
        this.tank = tank;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
        System.out.printf("method %s start...\n", method.getName());
        Object o = method.invoke(tank, params);
        method.getAnnotations();
        System.out.printf("method %s end!\n", method.getName());
        return o;
    }
}
