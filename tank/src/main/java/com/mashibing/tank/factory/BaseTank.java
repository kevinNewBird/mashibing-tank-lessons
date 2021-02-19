package com.mashibing.tank.factory;

import com.mashibing.tank.frame.Group;

import java.awt.*;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/2/18 10:56
 * @version: 1.0
 ***********************/
public abstract class BaseTank {

    public Rectangle rect = new Rectangle();

    protected Group group = Group.BAD;

    public abstract void paint(Graphics g);

    public Group getGroup(){
        return this.group;
    }

    public abstract void die();
}
