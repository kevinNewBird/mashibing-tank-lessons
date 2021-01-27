package com.mashibing.dm.factory.factorymethod;

/***********************
 * @Description: 简单工厂<BR>
 *     简单工厂的可扩展性不好!
 * @author: zhao.song
 * @since: 2021/1/27 1:13
 * @version: 1.0
 ***********************/
public class SimpleVehicleFactory {

    public Car createCar() {
        //before processing(做一些权限的处理)
        return new Car();
    }

    public Broom createBroom() {
        //before processing
        return new Broom();
    }
}
