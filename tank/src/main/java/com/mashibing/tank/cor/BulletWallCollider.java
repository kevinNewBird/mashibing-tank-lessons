package com.mashibing.tank.cor;

import com.mashibing.tank.frame.*;

/***********************
 * @Description: 子弹和墙的碰撞器 <BR>
 * @author: zhao.song
 * @since: 2021/3/4 23:41
 * @version: 1.0
 ***********************/
public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet b = (Bullet) o1;
            Wall w = (Wall) o2;
            if (b.rect.intersects(w.rect)) {
                b.die();
                return false;
            }
        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}
