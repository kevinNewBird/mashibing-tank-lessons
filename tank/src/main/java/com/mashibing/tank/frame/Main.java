package com.mashibing.tank.frame;

import java.util.concurrent.TimeUnit;

/***********************
 * Description: 实现new 一个窗口 <BR>
 * @author: zhao.song
 * @date: 2020/11/13 16:12
 * @version: 1.0
 ***********************/
public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        while (true) {
            TimeUnit.MILLISECONDS.sleep(50);
            //刷新窗口,repaint方法会主动调用paint方法
            tankFrame.repaint();
        }
    }
}
