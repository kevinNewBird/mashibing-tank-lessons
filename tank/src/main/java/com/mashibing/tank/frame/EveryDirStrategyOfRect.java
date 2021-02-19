package com.mashibing.tank.frame;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/1/25 23:21
 * @version: 1.0
 ***********************/
public class EveryDirStrategyOfRect implements FireStrategy<RectTank> {

//    private EveryDirStrategy() {
//    }

    @Override
    public void apply(RectTank tank) {
        int bX = (RectTank.WIDTH - RectBullet.WIDTH) / 2 + tank.getX();
        int bY = (RectTank.HEIGHT - RectBullet.HEIGHT) / 2 + tank.getY();
        tank.tf.gf.createBullet(bX, bY, Dir.DOWN, tank.getGroup(), tank.tf);
        tank.tf.gf.createBullet(bX, bY, Dir.LEFT, tank.getGroup(), tank.tf);
        tank.tf.gf.createBullet(bX, bY, Dir.UP, tank.getGroup(), tank.tf);
        tank.tf.gf.createBullet(bX, bY, Dir.RIGHT, tank.getGroup(), tank.tf);


        //在构建bullet对象时,放入容器
//        tank.tf.bulletContainer.add(b1);
//        tank.tf.bulletContainer.add(b2);
//        tank.tf.bulletContainer.add(b3);
//        tank.tf.bulletContainer.add(b4);

        if (tank.getGroup() == Group.GOOD) {
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
//    public static EveryDirStrategy getInstance() {
//        return EveryDirStrategy.EveryDirStrategyHolder.INSTANCE;
//    }
//
//    private static class EveryDirStrategyHolder {
//        public static EveryDirStrategy INSTANCE = new EveryDirStrategy();
//    }
}