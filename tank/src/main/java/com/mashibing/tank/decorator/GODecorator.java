package com.mashibing.tank.decorator;

import com.mashibing.tank.frame.GameObject;

/***********************
 * @Description: 游戏物体装饰类 <BR>
 * @author: zhao.song
 * @since: 2021/3/10 23:33
 * @version: 1.0
 ***********************/
public abstract class GODecorator extends GameObject {

    /*
    目的:给游戏物理添加外壳或者尾巴
    定义:装饰类必定是持有源对象
     */
    protected GameObject go;

    public GODecorator(GameObject go) {
        this.go = go;
    }
}
