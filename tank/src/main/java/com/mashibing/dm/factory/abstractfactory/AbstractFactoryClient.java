package com.mashibing.dm.factory.abstractfactory;


/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/1/27 1:04
 * @version: 1.0
 ***********************/
public class AbstractFactoryClient {

    public static void main(String[] args) {
//        Car car = new Car();
//        car.go();
//        AK47 w = new AK47();
//        w.shoot();
//        Bread b = new Bread();
//        b.printName();
        AbstractFactory fac = new ModernFactory();
        fac.createVehicle().go();
        fac.createWeapon().shoot();
        fac.createFood().printName();
    }
}
