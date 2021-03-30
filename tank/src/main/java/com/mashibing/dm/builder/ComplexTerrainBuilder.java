package com.mashibing.dm.builder;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/31 0:28
 * @version: 1.0
 ***********************/
public class ComplexTerrainBuilder implements TerrainBuilder {

    //初始化对象
    Terrain terrain = new Terrain();

    @Override

    public TerrainBuilder buildWall() {
        terrain.w = new Wall(10, 10, 10, 10);
        return this;
    }

    @Override
    public TerrainBuilder buildFort() {
        terrain.f = new Fort(20, 20, 20, 20);
        return this;
    }

    @Override
    public TerrainBuilder buildMine() {
        terrain.m = new Mine(30, 30, 30, 30);
        return this;
    }

    @Override
    public Terrain build() {
        return terrain;
    }
}
