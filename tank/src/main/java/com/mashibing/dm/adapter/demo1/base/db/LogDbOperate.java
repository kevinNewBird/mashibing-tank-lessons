package com.mashibing.dm.adapter.demo1.base.db;

import com.mashibing.dm.adapter.demo1.base.domain.LogModel;

import java.util.List;

/***********************
 * @Description: 数据库管理日志实现<BR>
 * @author: zhao.song
 * @since: 2021/3/31 15:50
 * @version: 1.0
 ***********************/
public class LogDbOperate implements LogDbOperateApi {

    /**
     * 插入日志
     * @param log
     */
    @Override
    public void insert(LogModel log) {
        System.out.println("insert log to db, data=" + log);
    }

    /**
     * 更新日志
     * @param log
     */
    @Override
    public void update(LogModel log) {
        System.out.println("update log to db, data=" + log);
    }

    /**
     * 查询所有日志
     * @return
     */
    @Override
    public List<LogModel> queryAll() {
        System.out.println("query all log from db！");
        return null;
    }

    /**
     * 删除日志
     * @param log
     */
    @Override
    public void delete(LogModel log) {
        System.out.println("delete log from db,data=" + log);
    }

}
