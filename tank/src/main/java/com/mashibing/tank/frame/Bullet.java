package com.mashibing.tank.frame;

import java.awt.*;


/***********************
 * Description: 炮弹类 <BR>
 * @author: zhao.song
 * @date: 2020/11/19 0:32
 * @version: 1.0
 ***********************/
public class Bullet {


    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    //子弹位置
    private int x, y;

    //子弹方向
    private Dir dir;

    //子弹速度
    private final int SPEED = 1;

    //是否发射
    private boolean isShoot = false;

    public Bullet(int x,int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        if (isShoot) {
            Color c = g.getColor();
            g.setColor(Color.RED);
            g.fillOval(x,y,WIDTH,HEIGHT);
            g.setColor(c);

            move();
        }

    }

    public void setShoot(boolean shoot) {
        isShoot = shoot;
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
    }


}
