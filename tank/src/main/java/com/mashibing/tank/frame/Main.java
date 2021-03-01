package com.mashibing.tank.frame;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/***********************
 * Description: 实现new 一个窗口 <BR>
 * @author: zhao.song
 * @date: 2020/11/13 16:12
 * @version: 1.0
 ***********************/
public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {

        TankFrame tankFrame = new TankFrame();

        // 背景音乐
//        new Audio("audios/war1.wav").start();
        while (true) {

            TimeUnit.MILLISECONDS.sleep(50);
            //刷新窗口,repaint方法会主动调用paint方法
            tankFrame.repaint();
        }
    }

}
