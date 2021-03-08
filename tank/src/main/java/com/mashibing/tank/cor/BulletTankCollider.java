package com.mashibing.tank.cor;

import com.mashibing.tank.frame.*;

/***********************
 * @Description: 子弹坦克的碰撞器 <BR>
 * @author: zhao.song
 * @since: 2021/3/4 23:41
 * @version: 1.0
 ***********************/
public class BulletTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Bullet) {
            //如果相撞,返回false,链条不再继续往下执行
            return !collideWithTank((Bullet) o2, (Tank) o1);
        } else if (o1 instanceof Bullet && o2 instanceof Tank) {
            return collide(o2, o1);
        }
        return true;
    }

    /**
     * Description: 子弹和坦克碰撞检测 <BR>
     *
     * @param bullet:
     * @param tank:
     * @param tank:
     * @return boolean: true表示已碰撞, false表示未碰撞
     * @author zhao.song    2021/3/5 0:17
     */
    private boolean collideWithTank(Bullet bullet, Tank tank) {
        if (bullet.group == tank.getGroup()) return false;

        //使用awt下辅助类:获取坦克和子弹的矩形(每一次检测都会创建新对象,垃圾回收时间不受控制)
        //好的做法在自己的类中成员变量Rectangle
        //1.子弹得矩形框
//        Rectangle rectBullet = new Rectangle(this.x, this.y, WIDTH, HEIGHT);

        //2.坦克的矩形框
//        Rectangle rectTank = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);

        if (tank.rect.intersects(bullet.rect)) {
            int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            GameModel.getInstance().add(new Explode(eX, eY));
            tank.die();
            bullet.die();
            return true;
        }
        return false;
    }
}
