package com.mashibing.dm.flyweight;

import com.mashibing.tank.frame.Bullet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/16 22:13
 * @version: 1.0
 ***********************/
public class BulletPool {

    static List<Bullet> bullets = new ArrayList<>();

    {
        IntStream.range(0, 5).forEach(index -> bullets.add(new Bullet()));

        bullets.forEach(System.out::println);
        System.out.println("---------------------------------------------");
    }

    public static void main(String[] args) {
        BulletPool bp = new BulletPool();
        IntStream.range(0, 10).forEach(index -> System.out.println(bp.getBullet()));
    }

    public Bullet getBullet() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            if (!b.isLiving){
                return b;
            }
        }
        return new BulletPool.Bullet();
    }

    class Bullet{
        private UUID id = UUID.randomUUID();
        private boolean isLiving = true;

        @Override
        public String toString() {
            return "Bullet{" +
                    "id=" + id +
                    ", isLiving=" + isLiving +
                    '}';
        }
    }
}
