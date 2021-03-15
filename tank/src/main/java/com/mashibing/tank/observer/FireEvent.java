package com.mashibing.tank.observer;

import com.mashibing.tank.frame.Tank;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/16 0:47
 * @version: 1.0
 ***********************/
public class FireEvent {

    long time;
    Tank tank;

    public FireEvent(long time, Tank tank) {
        this.time = time;
        this.tank = tank;
    }

    public Tank getSource() {
        return this.tank;
    }
}
