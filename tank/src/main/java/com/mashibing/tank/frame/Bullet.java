package com.mashibing.tank.frame;

import java.awt.*;


/***********************
 * Description: 炮弹类 <BR>
 * @author: zhao.song
 * @date: 2020/11/19 0:32
 * @version: 1.0
 ***********************/
public class Bullet {


    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();

    private Group group = Group.BAD;

    private TankFrame tf;
    //子弹位置
    private int x, y;

    //子弹方向
    private Dir dir;

    //子弹速度
    private final int SPEED = 5;

    private boolean isLiving = true;

    public boolean isLiving() {
        return isLiving;
    }

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }


    public void paint(Graphics g) {
        if (!isLiving) {
            tf.bulletContainer.remove(this);
        }

        drawAppearance(g);
        move();

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
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
        }
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
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH
                || y > TankFrame.GAME_HEIGHT) {
            this.isLiving = false;
        }
    }


    public void collideWithTank(Tank tank) {
        if (this.group == tank.getGroup()) return;

        //使用awt下辅助类:获取坦克和子弹的矩形
        //1.子弹得矩形框
        Rectangle rectBullet = new Rectangle(this.x, this.y, WIDTH, HEIGHT);

        //2.坦克的矩形框
        Rectangle rectTank = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);

        if (rectTank.intersects(rectBullet)) {
            tank.die();
            this.die();
        }
    }

    private void die() {
        this.isLiving = false;
    }
}
