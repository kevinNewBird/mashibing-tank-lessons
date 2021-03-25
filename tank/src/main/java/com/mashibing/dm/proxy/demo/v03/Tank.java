package com.mashibing.dm.proxy.demo.v03;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/***********************
 * @Description: 静态代理(与decorator很像) <BR>
 * @author: zhao.song
 * @since: 2021/3/16 22:51
 * @version: 1.0
 ***********************/
public class Tank implements Movable {

    public static void main(String[] args) {
        //不能处理多个: 只能代理一个tank, 所以应用修改为Movable
        new LogProxy(new TimeProxy(new Tank())).move();
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

//模拟代理
class TimeProxy implements Movable {
    private Movable m;

    public TimeProxy(Movable m) {
        this.m = m;
    }

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        m.move();
        long end = System.currentTimeMillis();

        System.out.printf("moving period is %s ms\n", (end - start));
    }
}

//模拟代理
class LogProxy implements Movable {
    private Movable m;

    public LogProxy(Movable m) {
        this.m = m;
    }

    @Override
    public void move() {
        System.out.println("tank start moving....");
        m.move();

        System.out.println("stopped!");
    }
}

interface Movable {
    void move();
}
