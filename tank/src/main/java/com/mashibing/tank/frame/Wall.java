package com.mashibing.tank.frame;

import java.awt.*;

/***********************
 * @Description: 墙--游戏物体<BR>
 * @author: zhao.song
 * @since: 2021/3/8 23:30
 * @version: 1.0
 ***********************/
public class Wall extends GameObject {
    //记录墙的位置坐标
    //墙的矩形块
    public Rectangle rect = new Rectangle();

    //记录墙体的宽高


    public Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rect.x = x;
        rect.y = y;
        rect.width = width;
        rect.height = height;
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);
        g.setColor(c);
    }
}
