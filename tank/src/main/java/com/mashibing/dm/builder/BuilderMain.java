package com.mashibing.dm.builder;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/31 0:32
 * @version: 1.0
 ***********************/
public class BuilderMain {

    public static void main(String[] args) {

        //这个模式的使用：参数特别多
        Terrain terrain = new ComplexTerrainBuilder().buildWall().buildFort().buildMine().build();
        System.out.println(terrain);

        Person p = new Person.PersonBuilder().buildWeight(72.4d).build();
        System.out.println(p);
    }
}
