package com.mashibing.dm.factory.factorymethod;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/1/27 1:16
 * @version: 1.0
 ***********************/
public class CarFactory {

    public Movable createCar() {
        //模拟日志记录
        System.out.println("a car created");
        return new Car();
    }
}
