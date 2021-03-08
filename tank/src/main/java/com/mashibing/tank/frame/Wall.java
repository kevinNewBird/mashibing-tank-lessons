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
    int x, y;
    //墙的矩形块
    public Rectangle rect = new Rectangle();

    //记录墙体的宽高
    int w, h;

    private GameModel gm;

    public Wall(int x, int y, int width, int height,GameModel gm) {
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
        this.gm = gm;
        rect.x = x;
        rect.y = y;
        rect.width = width;
        rect.height = height;
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(x, y, w, h);
        g.setColor(c);
    }
}
