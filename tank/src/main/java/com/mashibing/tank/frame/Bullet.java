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
    private final int SPEED = 10;

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
                        , Bullet.LR_WIDTH + x
                        , (Tank.HEIGHT - Bullet.LR_HEIGHT) / 2 + y
                        , null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,
                        (Tank.WIDTH - Bullet.UD_WIDTH) / 2 + x
                        , Tank.HEIGHT + y
                        , null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL
                        , x - Bullet.LR_WIDTH
                        , (Tank.HEIGHT - Bullet.LR_HEIGHT) / 2 + y
                        , null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU
                        , x + (Tank.WIDTH - Bullet.UD_WIDTH) / 2
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


}
