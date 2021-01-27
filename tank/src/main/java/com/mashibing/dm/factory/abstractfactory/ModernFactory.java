package com.mashibing.dm.factory.abstractfactory;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/1/27 22:15
 * @version: 1.0
 ***********************/
public class ModernFactory extends AbstractFactory{
    @Override
    Food createFood() {
        return new Bread();
    }

    @Override
    Vehicle createVehicle() {
        return new Car();
    }

    @Override
    Weapon createWeapon() {
        return new AK47();
    }
}
