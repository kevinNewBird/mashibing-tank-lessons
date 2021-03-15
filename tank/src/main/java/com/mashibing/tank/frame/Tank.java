package com.mashibing.tank.frame;

import com.mashibing.tank.observer.FireEvent;
import com.mashibing.tank.observer.IFireObserver;
import com.mashibing.tank.observer.TankFireObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/***********************
 * Description: 坦克对象类(面向对象思维) <BR>
 * @author: zhao.song
 * @date: 2020/11/18 0:58
 * @version: 1.0
 ***********************/
public class Tank extends GameObject {

    private static Logger logger = LoggerFactory.getLogger(Tank.class);

    private Random r = new Random();

    public static final int WIDTH = ResourceMgr.goodTankD.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankD.getHeight();

    public Rectangle rect = new Rectangle();

    // 记录坦克上一次的移动位置
    public int oldX, oldY;
    private int speed = PropertyMgr.getInt("tankSpeed");
    ;
    private Dir dir = Dir.DOWN;
    private boolean isLiving = true;

    private Group group = Group.BAD;

    public static java.util.List<IFireObserver> observers = Arrays.asList(new TankFireObserver());

    //--------------------用于切换坦克的灯光效果------------------
//    private int step = 0;
//    private static final int DEFAULT_LIGHT_CONST_MIN = 20;
    //--------------------用于切换坦克的灯光效果------------------

    // 坦克的状态:禁止false/移动move;这个不应该是一个方向,仅仅表示坦克的一个运动状态
    private boolean moving = true;

    FireStrategy fs;

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.dir = dir;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        //通过配置文件的形式,避免策略模式代码的变动
        try {
            if (group == Group.GOOD) {
                fs = (FireStrategy) Class.forName(PropertyMgr.getString("goodFs")).getDeclaredConstructor().newInstance();
            } else {
                fs = (FireStrategy) Class.forName(PropertyMgr.getString("badFs")).getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            logger.error("坦克策略初始化失败!", e);
        }
        GameModel.getInstance().add(this);
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
            GameModel.getInstance().remove(this);
            return;
        }
//        g.setColor(Color.BLUE);
//        g.fillRect(x, y, 50, 50);

        move();
        // 3.画出坦克外观
        display(g);

    }

    /**
     * Description: 边界监测 <BR>
     *
     * @param :
     * @return
     * @author zhao.song    2021/1/18 16:58
     */
    private void boundsCheck() {

        if (this.x < 2) {
            x = 2;
        } else if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) {
            x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        } else if (this.y < 30) {
            y = 30;
        } else if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) {
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
        }
    }

    /**
     * Description: 描绘并美化tank的外观 <BR>
     *
     * @param g:
     * @return
     * @author zhao.song    2020/12/22 19:51
     */
    private void display(Graphics g) {
//        ++step;
        switch (dir) {
            case RIGHT:
                g.drawImage(this.group == Group.BAD
                                ? ResourceMgr.badTankR
                                : ResourceMgr.goodTankR
//                                ? (step % DEFAULT_LIGHT_CONST_MIN ==0 ? ResourceMgr.badTankR_light : ResourceMgr.badTankR)
//                                : (step % DEFAULT_LIGHT_CONST_MIN ==0 ? ResourceMgr.goodTankR_light : ResourceMgr.goodTankR)
                        , x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.BAD
                                ? ResourceMgr.badTankD
                                : ResourceMgr.goodTankD
//                                ? (step % DEFAULT_LIGHT_CONST_MIN == 0 ? ResourceMgr.badTankD_light : ResourceMgr.badTankD)
//                                : (step % DEFAULT_LIGHT_CONST_MIN == 0 ? ResourceMgr.goodTankD_light : ResourceMgr.goodTankD)
                        , x, y, null);
                break;
            case LEFT:
                g.drawImage(this.group == Group.BAD
                                ? ResourceMgr.badTankL
                                : ResourceMgr.goodTankL
//                                ? (step % DEFAULT_LIGHT_CONST_MIN == 0 ? ResourceMgr.badTankL_light : ResourceMgr.badTankL)
//                                : (step % DEFAULT_LIGHT_CONST_MIN == 0 ? ResourceMgr.goodTankL_light : ResourceMgr.goodTankL)
                        , x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.BAD
                                ? ResourceMgr.badTankU
                                : ResourceMgr.goodTankU
//                                ? (step % DEFAULT_LIGHT_CONST_MIN == 0 ? ResourceMgr.badTankU_light : ResourceMgr.badTankU)
//                                : (step % DEFAULT_LIGHT_CONST_MIN == 0 ? ResourceMgr.goodTankU_light : ResourceMgr.goodTankU)
                        , x, y, null);
                break;
        }

    }

    private void move() {
        //记录上一次的移动位置
        this.oldX = this.x;
        this.oldY = this.y;
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

        // tank的边界检测
        boundsCheck();

        // update rect
        rect.x = this.x;
        rect.y = this.y;
    }

    private void randomDir() {
        if (r.nextInt(100) > 95) {
            this.setDir(Dir.values()[r.nextInt(Dir.values().length)]);
        }
    }

    private void randomFire() {
        if (r.nextInt(100) > 95) {
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
        fs.apply(this);
//        int bX = (Tank.WIDTH - Bullet.WIDTH) / 2 + x;
//        int bY = (Tank.HEIGHT - Bullet.HEIGHT) / 2 + y;
//        this.gm.b = new Bullet(bX, bY, this.dir, group, this.gm);
//
//        gm.bulletContainer.add(this.gm.b);
    }

    public void handleFireEvent() {
        FireEvent event = new FireEvent(new Date().getTime(), this);
        for (IFireObserver observer : observers) {
            observer.actionOnFire(event);
        }
    }

    public void die() {
        this.isLiving = false;
    }

    //坦克相撞后,回到上一次的位置
    public void back() {
        this.x = oldX;
        this.y = oldY;
    }
}
