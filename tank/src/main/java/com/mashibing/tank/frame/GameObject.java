package com.mashibing.tank.frame;

import java.awt.*;
import java.io.Serializable;

/***********************
 * @Description: 游戏物体父类(所有的游戏物体都继承该父类)<BR>
 * @author: zhao.song
 * @since: 2021/3/3 23:51
 * @version: 1.0
 ***********************/
public abstract class GameObject implements Serializable {

    //抽象一个父类,最重要的就是提炼公共属性和方法
    /**
     * <BR>tip:思考: 游戏物体的必有属性?</BR>
     * 针对坦克世界这款游戏:
     * 1.位置属性
     * 2.三维属性
     * 3.画笔-paint
     * ...
     */

    public int x, y, width, height;

    public abstract void paint(Graphics g);

}
