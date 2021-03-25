package com.mashibing.dm.proxy.demo.v01;

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
        new Tank().move();
    }


    //模拟坦克移动了一段时间
    @Override
    public void move() {
        long start = System.currentTimeMillis();
        System.out.println("Tank moving claclacla...");
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.printf("moving period is %s ms\n", (end - start));
    }
}

interface Movable {
    void move();
}
