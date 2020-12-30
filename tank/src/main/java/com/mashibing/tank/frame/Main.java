package com.mashibing.tank.frame;

import java.io.IOException;
import java.net.Socket;
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
        tankFrame.enemyTankContainer.add(tankFrame.mainTank);
        //初始化地方坦克
        for (int i = 5; i > 0; i--) {
            tankFrame.enemyTankContainer.add(
                    new Tank(50 + i * 100, 200, Dir.LEFT, Group.BAD, tankFrame));
        }

        while (true) {
            TimeUnit.MILLISECONDS.sleep(50);
            //刷新窗口,repaint方法会主动调用paint方法
            tankFrame.repaint();
        }
    }

}
