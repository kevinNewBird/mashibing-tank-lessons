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
public class Explode extends GameObject {

    private Logger logger = LoggerFactory.getLogger(Explode.class);

    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();


    private boolean isLiving;

    //主要用于确认爆炸的位置
    private int x, y;

    private int step;


    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        new Audio("audios/explode.wav").start();
        logger.info(String.format("目标(%s,%s)被摧毁!", x, y));
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
            GameModel.getInstance().remove(this);
            return;
        }

        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
    }
}
