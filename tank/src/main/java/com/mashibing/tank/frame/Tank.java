package com.mashibing.tank.frame;

import java.awt.*;
import java.util.Random;

/***********************
 * Description: 坦克对象类(面向对象思维) <BR>
 * @author: zhao.song
 * @date: 2020/11/18 0:58
 * @version: 1.0
 ***********************/
public class Tank {

    private Random r = new Random();

    public static final int WIDTH = ResourceMgr.tankD.getWidth();
    public static final int HEIGHT = ResourceMgr.tankD.getHeight();

    private int x, y;
    private int speed = 5;
    private Dir dir = Dir.DOWN;
    private boolean isLiving = true;

    private Group group = Group.BAD;

    // 坦克的状态:禁止false/移动move;这个不应该是一个方向,仅仅表示坦克的一个运动状态
    private boolean moving = false;

    //面向对象思想:保证这个类持有窗口类的引用
    private TankFrame tf = null;

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
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

    public Group getGroup() {
        return this.group;
    }


    public synchronized void paint(Graphics g) {
        // 1.tank被击中,停止描绘
        if (!isLiving) {
            tf.enemyTankContainer.remove(this);
            return;
        }
//        g.setColor(Color.BLUE);
//        g.fillRect(x, y, 50, 50);
        // 2.tank的边界检测
        checkTankEdge();
        move();
        // 3.画出坦克外观
        drawAppearance(g);

    }

    private void checkTankEdge() {
        if (this.group == Group.GOOD && (this.x < 0 || this.y < 0 || this.x > this.tf.GAME_WIDTH || this.y > this.tf.GAME_HEIGHT)) {
//            this.moving = false;
        }
        /*if (this.x < 0) {
            dir = Dir.RIGHT;
        } else if (this.y < 0) {
            dir = Dir.DOWN;
        } else if (this.x > this.tf.GAME_WIDTH) {
            dir = Dir.LEFT;
        } else if (this.y > this.tf.GAME_HEIGHT) {
            dir = Dir.UP;
        }*/
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

        if (this.group == Group.BAD) {
            // 敌方坦克自动开火
            randomFire();
            //随机变动敌坦方向
            randomDir();
        }
    }

    private void randomDir() {
        if (r.nextInt(50) > 45) {
            this.setDir(Dir.values()[r.nextInt(Dir.values().length)]);
        }
    }

    private void randomFire() {
        if (r.nextInt(20) > 16) {
            this.fire();
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
        int bX = (Tank.WIDTH - Bullet.WIDTH) / 2 + x;
        int bY = (Tank.HEIGHT - Bullet.HEIGHT) / 2 + y;
        this.tf.b = new Bullet(bX, bY, this.dir, group, this.tf);

        tf.bulletContainer.add(this.tf.b);
    }

    public void die() {
        this.isLiving = false;
    }
}
