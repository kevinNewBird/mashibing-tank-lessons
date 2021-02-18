package com.mashibing.tank.frame;

/***********************
 * @Description: 默认策略 <BR>
 * @author: zhao.song
 * @since: 2021/1/25 23:21
 * @version: 1.0
 ***********************/
public class DefaultStrategyOfRect implements FireStrategy<RectTank> {

//    private DefaultStrategy() {
//    }


    @Override
    public void apply(RectTank tank) {
        int bX = (RectTank.WIDTH - RectBullet.WIDTH) / 2 + tank.getX();
        int bY = (RectTank.HEIGHT - RectBullet.HEIGHT) / 2 + tank.getY();
        tank.tf.gf.createBullet(bX, bY, tank.getDir(), tank.getGroup(), tank.tf);

        //在构造方法中,加入容器
//        tank.tf.bulletContainer.add(tank.tf.b);

        if (tank.getGroup()==Group.GOOD){
            new Audio("audios/tank_fire.wav").start();
        }
    }

    /**
     * Description: 获取单例对象 <BR>
     *
     * @param :
     * @return {@link DefaultStrategyOfRect}
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
