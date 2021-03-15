package com.mashibing.tank.observer;

import java.util.Optional;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/16 0:50
 * @version: 1.0
 ***********************/
public class TankFireObserver implements IFireObserver{
    @Override
    public void actionOnFire(FireEvent event) {
        event.getSource().fire();
        Optional.of("tank fire observe...").ifPresent(System.out::println);
    }
}
