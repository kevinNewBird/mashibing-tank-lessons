package com.mashibing.tank.factory;

import com.mashibing.tank.frame.Tank;

import java.awt.*;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/2/18 10:57
 * @version: 1.0
 ***********************/
public abstract class BaseBullet<T extends BaseTank> {

    public abstract void paint(Graphics g);

    public abstract void collideWithTank(T tank);

}
