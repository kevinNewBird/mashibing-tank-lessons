package com.mashibing.dm.factory.factorymethod;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/1/27 1:09
 * @version: 1.0
 ***********************/
public class Broom implements Movable {

    @Override
    public void go() {
        System.out.println("the broom flying chuachuachua...");
    }

}
