package com.mashibing.dm.proxy.spring_aop.v1;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/16 22:51
 * @version: 1.0
 ***********************/
public class Tank{

    public static void main(String[] args) {
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

