package com.mashibing.dm.factory.factorymethod;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/1/27 1:04
 * @version: 1.0
 ***********************/
public class Main {

    public static void main(String[] args) {
/*        Car car = new Car();
        car.go();*/
        Movable m = new CarFactory().createCar();
        m.go();
    }
}
