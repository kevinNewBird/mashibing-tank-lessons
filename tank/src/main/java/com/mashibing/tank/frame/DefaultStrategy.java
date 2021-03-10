package com.mashibing.tank.frame;

import com.mashibing.tank.decorator.RectDecorator;
import com.mashibing.tank.decorator.TailDecorator;

/***********************
 * @Description: 默认策略 <BR>
 * @author: zhao.song
 * @since: 2021/1/25 23:21
 * @version: 1.0
 ***********************/
public class DefaultStrategy implements FireStrategy<Tank> {

//    private DefaultStrategy() {
//    }


    @Override
    public void apply(Tank tank) {
        int bX = (Tank.WIDTH - Bullet.WIDTH) / 2 + tank.getX();
        int bY = (Tank.HEIGHT - Bullet.HEIGHT) / 2 + tank.getY();
        //装饰器:注释掉为了方便后续的开发
//        GameModel.getInstance().add(new TailDecorator(
//                new RectDecorator(
//                        new Bullet(bX, bY, tank.getDir(), tank.getGroup()))));

        new Bullet(bX, bY, tank.getDir(), tank.getGroup());
        //在构造方法中,加入容器
//        tank.gm.bulletContainer.add(tank.gm.b);

        if (tank.getGroup() == Group.GOOD) {
            new Audio("audios/tank_fire.wav").start();
        }
    }

    /**
     * Description: 获取单例对象 <BR>
     *
     * @param :
     * @return {@link com.mashibing.tank.frame.DefaultStrategy}
     * @author zhao.song    2021/1/25 23:57
     */
//    public static DefaultStrategy getInstance() {
//        return DefaultStrategyHolder.INSTANCE;
//    }
//
//    private static class DefaultStrategyHolder {
//        public static DefaultStrategy INSTANCE = new DefaultStrategy();
//    }
}
