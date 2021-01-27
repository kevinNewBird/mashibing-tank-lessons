package com.mashibing.dm.factory.abstractfactory;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/1/27 22:32
 * @version: 1.0
 ***********************/
public class MagicFactory extends AbstractFactory{
    @Override
    Food createFood() {
        return new MushRoom();
    }

    @Override
    Vehicle createVehicle() {
        return new Broom();
    }

    @Override
    Weapon createWeapon() {
        return new MagicStick();
    }
}
