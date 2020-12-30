package com.mashibing.tank.frame;

import java.awt.*;

/***********************
 * Description: 坦克对象类(面向对象思维) <BR>
 * @author: zhao.song
 * @date: 2020/11/18 0:58
 * @version: 1.0
 ***********************/
public class Tank {

    public static final int UD_WIDTH = ResourceMgr.tankD.getWidth();
    public static final int UD_HEIGHT = ResourceMgr.tankD.getHeight();
    public static final int LR_WIDTH = ResourceMgr.tankL.getWidth();
    public static final int LR_HEIGHT = ResourceMgr.tankL.getHeight();

    private int x, y;
    private int speed = 10;
    private Dir dir = Dir.DOWN;
    private boolean isLiving = true;

    // 坦克的状态:禁止false/移动move;这个不应该是一个方向,仅仅表示坦克的一个运动状态
    private boolean moving = false;

    //面向对象思想:保证这个类持有窗口类的引用
    private TankFrame tf = null;


    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public Tank(int x, int y, int speed, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.speed = speed;
        this.tf = tf;
    }

    public Dir getDir() {
        return dir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public synchronized void paint(Graphics g) {
        //tank被击中,停止描绘
        if (!isLiving) {
            tf.enemyTankContainer.remove(this);
            return;
        }
//        g.setColor(Color.BLUE);
//        g.fillRect(x, y, 50, 50);
        stopAndReverseDir();
        move();
        //画出坦克外观
        drawAppearance(g);

    }

    private void stopAndReverseDir() {
        if (this.x < 0 || this.y < 0 || this.x > this.tf.GAME_WIDTH || this.y > this.tf.GAME_HEIGHT) {

        }
        if (this.x < 0) {
            dir = Dir.RIGHT;
        } else if (this.y < 0) {
            dir = Dir.DOWN;
        } else if (this.x > this.tf.GAME_WIDTH) {
            dir = Dir.LEFT;
        } else if (this.y > this.tf.GAME_HEIGHT) {
            dir = Dir.UP;
        }
    }

    /**
     * Description: 描绘并美化tank的外观 <BR>
     *
     * @param g:
     * @return
     * @author zhao.song    2020/12/22 19:51
     */
    private void drawAppearance(Graphics g) {
        switch (dir) {
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
        }
    }

    private void move() {
        if (!moving) return;

        switch (dir) {
            case RIGHT:
                x += speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case UP:
                y -= speed;
                break;
        }
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    /**
     * Description: 坦克发射子弹方法 <BR>
     * tip:这个子弹的方向同坦克的运动方向一致, 但是怎么把这颗子弹交给TankFrame对象呢?
     * ans:面向对象思路,那就是这个类必须持有TankFrame的引用
     *
     * @param :
     * @return
     * @author zhao.song    2020/12/14 11:06
     */
    public void fire() {
        this.tf.b = new Bullet(this.x, this.y, this.dir, this.tf);
    }

    public void die() {
        this.isLiving = false;
    }
}
