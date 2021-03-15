package com.mashibing.tank.observer;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/16 0:48
 * @version: 1.0
 ***********************/
public interface IFireObserver {

    abstract void actionOnFire(FireEvent event);
}
