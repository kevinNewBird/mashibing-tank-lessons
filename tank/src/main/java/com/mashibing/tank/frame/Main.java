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

        int initTankCount = Integer.parseInt(PropertyMgr.get("initTankCount").orElse(10).toString());
//        tankFrame.enemyTankContainer.add(tankFrame.mainTank);
        //初始化地方坦克
        for (int i = initTankCount; i > 0; i--) {
            tankFrame.enemyTankContainer.add(tankFrame.gf
                    .createTank(50 + i * 100, 200, Dir.DOWN, Group.BAD, tankFrame));
        }

        while (true) {
            // 背景音乐
//            new Audio("audios/war1.wav").start();
            TimeUnit.MILLISECONDS.sleep(50);
            //刷新窗口,repaint方法会主动调用paint方法
            tankFrame.repaint();
        }
    }

}
