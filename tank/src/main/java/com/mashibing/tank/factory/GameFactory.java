package com.mashibing.tank.factory;

import com.mashibing.tank.frame.Dir;
import com.mashibing.tank.frame.Group;
import com.mashibing.tank.frame.TankFrame;

/***********************
 * @Description: tank战斗系列默认工厂 <BR>
 *     1.坦克创建
 *     2.子弹创建
 *     3.爆炸创建
 * @author: zhao.song
 * @since: 2021/1/28 0:06
 * @version: 1.0
 ***********************/
public abstract class GameFactory {

    /**
     * Description: 生产坦克 <BR>
     *
     * @param x:
     * @param y:
     * @param dir:
     * @param group:
     * @param tf:
     * @return {@link com.mashibing.tank.factory.BaseTank}
     * @author zhao.song    2021/2/18 11:02
     */
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);

    /**
     * Description: 生产子弹 <BR>
     *
     * @param x:
     * @param y:
     * @param dir:
     * @param group:
     * @param tf:
     * @return {@link com.mashibing.tank.factory.BaseBullet}
     * @author zhao.song    2021/2/18 11:03
     */
    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);

    /**
     * Description: 生产爆炸效果 <BR>
     *
     * @param x:
     * @param y:
     * @param tf:
     * @return {@link com.mashibing.tank.factory.BaseExplode}
     * @author zhao.song    2021/2/18 11:03
     */
    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
}
