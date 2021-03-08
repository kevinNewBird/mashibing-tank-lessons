package com.mashibing.tank.cor;

import com.mashibing.tank.frame.Bullet;
import com.mashibing.tank.frame.GameObject;
import com.mashibing.tank.frame.Tank;
import com.mashibing.tank.frame.Wall;

/***********************
 * @Description: 坦克和墙的碰撞器 <BR>
 * @author: zhao.song
 * @since: 2021/3/4 23:41
 * @version: 1.0
 ***********************/
public class TankWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank t = (Tank) o1;
            Wall w = (Wall) o2;
            if (t.rect.intersects(w.rect)) {
                t.back();
            }
        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}
