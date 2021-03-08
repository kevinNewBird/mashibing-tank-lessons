package com.mashibing.tank.frame;

import com.mashibing.tank.cor.BulletTankCollider;
import com.mashibing.tank.cor.Collider;
import com.mashibing.tank.cor.ColliderChain;
import com.mashibing.tank.cor.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;

/***********************
 * @Description: MVC设计模式, Model就是GameModel(数据) , View就是TankFrame(视图)
 * ,而MyKeyListener中的按键控制了主坦克的开火就是Controller<BR>
 * @author: zhao.song
 * @since: 2021/2/24 9:49
 * @version: 1.0
 ***********************/
public class GameModel {


    Tank mainTank = new Tank(200, 300, Dir.DOWN, Group.GOOD, this);

    //子弹容器,实现多个字段的输出
//    java.util.List<Bullet> bulletContainer = new ArrayList<Bullet>();
//
//    java.util.List<Tank> enemyTankContainer = new ArrayList<>();
//
//    java.util.List<Explode> explodes = new ArrayList<>();

    private java.util.List<GameObject> objects = new ArrayList<>();
    //必须注掉,否则在创建TankFrame对象时会加入子弹容器
//    Bullet b = new Bullet(200, 200, Dir.DOWN, Group.GOOD, this);

    ColliderChain chain = new ColliderChain();

    public GameModel() {
        //0.初始化坦克
        initTank();
    }

    public void add(GameObject go) {
        this.objects.add(go);
    }

    public boolean remove(GameObject go) {
        return this.objects.remove(go);
    }


    /**
     * Description: 初始化坦克 <BR>
     *
     * @param :
     * @return
     * @author zhao.song    2021/3/1 15:39
     */
    public void initTank() {
        int initTankCount = Integer.parseInt(PropertyMgr.get("initTankCount").orElse(10).toString());
//        tankFrame.enemyTankContainer.add(tankFrame.mainTank);
        //初始化地方坦克
        for (int i = initTankCount; i > 0; i--) {
            add(
                    new Tank(50 + i * 100, 200, Dir.DOWN, Group.BAD, this));
        }
    }


    public void paint(Graphics g) {

//        Map<String, List<GameObject>> map = objects.stream().collect(Collectors.groupingBy(go -> {
//            if (go instanceof Bullet) {
//                return "bullet";
//            } else if (go instanceof Tank) {
//                return "tank";
//            } else if (go instanceof Explode) {
//                return "explode";
//            }
//            return "default";
//        }));
//        List<GameObject> bulletContainer = map.get("bullet") == null ? new ArrayList() : map.get("bullet");
//        List<GameObject> enemyTankContainer = map.get("tank") == null ? new ArrayList() : map.get("tank");
//        List<GameObject> explodes = map.get("explode") == null ? new ArrayList() : map.get("explode");

//        g.drawString("子弹数量:" + bulletContainer.size(), 10, 50);
//        g.drawString("敌坦数量:" + (enemyTankContainer.size()), 10, 80);
//        g.drawString("爆炸数量:" + (explodes.size()), 10, 110);
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.setColor(c);
        // tip: 面向对象的思维:应该是将画笔递给坦克,坦克最知道该如何移动
        // ,而不是把tank的属性获取到再去设置
        mainTank.paint(g);

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                chain.collide(objects.get(i), objects.get(j));
            }
        }

//        for (int i = 0; i < enemyTankContainer.size(); i++) {
//            Tank enemyTank = (Tank) enemyTankContainer.get(i);
//            if (enemyTank.getGroup() == Group.GOOD) {
//                continue;
//            }
//            enemyTank.setMoving(true);
//            enemyTank.paint(g);
//        }


/*        bulletContainer.forEach(bullet -> {
            //思考问题: 打出去的子弹是否该移出容器? 应该怎样移出容器?
            //ans:1.不可以,因为每次都是重画窗口,如果移出会导致子弹消失;
            // 2.如果不移出,就需要考虑内存的优化,集合不可一直增大
            bullet.paint(g);
            if (bullet.getX() > this.GAME_WIDTH || bullet.getY() > this.GAME_HEIGHT) {
                bulletContainer.remove(bullet);
            }
        });*/
        // 1.可行解决方案一
/*        ListIterator<Bullet> iterator = bulletContainer.listIterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.paint(g);
            if (bullet.getX() > this.GAME_WIDTH || bullet.getY() > this.GAME_HEIGHT) {
                iterator.remove();
            }

        }*/
        // 2.可行解决方案二:
//        for (int i = 0; i < bulletContainer.size(); i++) {
//            bulletContainer.get(i).paint(g);
//        }


        // 3.碰撞判断
//        for (int i = bulletContainer.size() - 1; i >= 0; i--) {
//            for (int j = enemyTankContainer.size() - 1; j >= 0; j--) {
//                ((Bullet) (bulletContainer.get(i))).collideWithTank((Tank) enemyTankContainer.get(j));
//            }
//        }

//        for (int i = 0; i < explodes.size(); i++) {
//            explodes.get(i).paint(g);
//        }
    }

    public Tank getMainTank() {
        return this.mainTank;
    }


}
