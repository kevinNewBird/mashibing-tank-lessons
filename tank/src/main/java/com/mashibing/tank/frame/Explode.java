package com.mashibing.tank.frame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

/***********************
 * Description: 爆炸效果 <BR>
 * author: zhao.song
 * date: 2020/12/31 10:06
 * version: 1.0
 ***********************/
public class Explode {

    private Logger logger = LoggerFactory.getLogger(Explode.class);

    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();


    TankFrame tf = null;

    private boolean isLiving;

    //主要用于确认爆炸的位置
    private int x, y;

    private int step;


    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Audio("audios/explode.wav").start();
        logger.info("目标被摧毁!");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //如何画:使用一个常数记录步骤
    public void paint(Graphics g) {
        if (step >= ResourceMgr.explodes.length) {
            this.tf.explodes.remove(this);
            return;
        }

        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
    }
}
