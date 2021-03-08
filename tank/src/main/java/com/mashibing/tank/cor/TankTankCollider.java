package com.mashibing.tank.cor;

import com.mashibing.tank.frame.Bullet;
import com.mashibing.tank.frame.Explode;
import com.mashibing.tank.frame.GameObject;
import com.mashibing.tank.frame.Tank;

/***********************
 * @Description: 坦克间的碰撞器 <BR>
 * @author: zhao.song
 * @since: 2021/3/4 23:41
 * @version: 1.0
 ***********************/
public class TankTankCollider implements Collider {
    /**
     * Description: 坦克间相撞 <BR>
     *
     * @author zhao.song    2021/3/8 23:10
     * @param o1:
     * @param o2:
     * @return {@link boolean}
     */
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            //坦克相撞后,回到上一次的位置
            if (t1.rect.intersects(t2.rect)) {
                t1.backStep();
                t2.backStep();
            }
        }
        return true;
    }
}
