package com.mashibing.dm.factory.abstractfactory;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/1/27 22:11
 * @version: 1.0
 ***********************/
public abstract class AbstractFactory {

    abstract Food createFood();

    abstract Vehicle createVehicle();

    abstract Weapon createWeapon();
}
