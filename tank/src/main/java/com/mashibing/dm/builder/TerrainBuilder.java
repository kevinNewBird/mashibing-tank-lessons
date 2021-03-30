package com.mashibing.dm.builder;

/***********************
 * @Description: 地形构建器 <BR>
 * @author: zhao.song
 * @since: 2021/3/31 0:23
 * @version: 1.0
 ***********************/
public interface TerrainBuilder {

    TerrainBuilder buildWall();
    TerrainBuilder buildFort();
    TerrainBuilder buildMine();
    Terrain build();
}
