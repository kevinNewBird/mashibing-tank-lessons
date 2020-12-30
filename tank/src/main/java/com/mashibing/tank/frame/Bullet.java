package com.mashibing.tank.frame;

import java.awt.*;


/***********************
 * Description: 炮弹类 <BR>
 * @author: zhao.song
 * @date: 2020/11/19 0:32
 * @version: 1.0
 ***********************/
public class Bullet {


    public static final int UD_WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int UD_HEIGHT = ResourceMgr.bulletD.getHeight();
    public static final int LR_WIDTH = ResourceMgr.bulletL.getWidth();
    public static final int LR_HEIGHT = ResourceMgr.bulletR.getHeight();

    private TankFrame tf;
    //子弹位置
    private int x, y;

    //子弹方向
    private Dir dir;

    //子弹速度
    private final int SPEED = 5;

    private boolean live = true;

    public boolean isLive() {
        return live;
    }

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }


    public void paint(Graphics g) {
        if (!live) {
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
                g.drawImage(ResourceMgr.bulletR
                        , Tank.LR_WIDTH + x
                        , (Tank.LR_HEIGHT - Bullet.LR_HEIGHT) / 2 + y
                        , null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,
                        (Tank.UD_WIDTH - Bullet.UD_WIDTH) / 2 + x
                        , Tank.UD_HEIGHT + y
                        , null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL
                        , x - Bullet.LR_WIDTH
                        , (Tank.LR_HEIGHT - Bullet.LR_HEIGHT) / 2 + y
                        , null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU
                        , x + (Tank.UD_WIDTH - Bullet.UD_WIDTH) / 2
                        , y - Bullet.UD_HEIGHT
                        , null);
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
            this.live = false;
        }
    }


    public void collideWithTank(Tank tank) {
        //使用awt下辅助类:获取坦克和子弹的矩形
        //1.子弹得矩形框
        Rectangle rectBullet = new Rectangle(this.x, this.y
                , this.dir == Dir.DOWN || this.dir == Dir.UP ? this.UD_WIDTH : this.LR_WIDTH
                , this.dir == Dir.DOWN || this.dir == Dir.UP ? this.UD_HEIGHT : this.LR_HEIGHT);

        //2.坦克的矩形框
        Rectangle rectTank = new Rectangle(tank.getX(), tank.getY()
                , tank.getDir() == Dir.DOWN || tank.getDir() == Dir.UP ? Tank.UD_WIDTH : Tank.LR_WIDTH
                , tank.getDir() == Dir.DOWN || tank.getDir() == Dir.UP ? Tank.UD_HEIGHT : Tank.LR_HEIGHT);

        if (rectTank.intersects(rectBullet)) {
            //mine:自己的做法粗暴的移除集合元素
            //teacher:图片渲染
            tank.die();
            this.die();
        }
    }

    private void die() {
        this.live = false;
    }
}
