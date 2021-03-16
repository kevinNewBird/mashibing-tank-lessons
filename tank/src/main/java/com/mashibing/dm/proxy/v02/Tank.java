package com.mashibing.dm.proxy.v02;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/16 22:51
 * @version: 1.0
 ***********************/
public class Tank implements Movable {

    public static void main(String[] args) {
        new TimeProxy(new Tank()).move();
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
    private Tank tank;

    public TimeProxy(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        tank.move();
        long end = System.currentTimeMillis();

        System.out.printf("moving period is %s ms\n", (end - start));
    }
}

interface Movable {
    void move();
}
