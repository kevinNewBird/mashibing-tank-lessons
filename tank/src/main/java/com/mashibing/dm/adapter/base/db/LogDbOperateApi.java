package com.mashibing.dm.adapter.base.db;

import com.mashibing.dm.adapter.base.domain.LogModel;

import java.util.List;

/***********************
 * @Description: 数据库管理日志接口<BR>
 * @author: zhao.song
 * @since: 2021/3/31 15:51
 * @version: 1.0
 ***********************/
public interface LogDbOperateApi {
    public void insert(LogModel log);

    public void update(LogModel log);

    public List<LogModel> queryAll();

    public void delete(LogModel log);


}
