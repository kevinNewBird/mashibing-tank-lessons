package com.mashibing.dm.adapter.demo1.source.db;

import com.mashibing.dm.adapter.demo1.base.db.LogDbOperate;
import com.mashibing.dm.adapter.demo1.base.domain.LogModel;

/***********************
 * @Description: 数据库管理日志客户端 <BR>
 * @author: zhao.song
 * @since: 2021/3/31 16:20
 * @version: 1.0
 ***********************/
public class LogDbOperateClient {


    public static void main(String[] args) {
        //准备日志内容,也就是测试数据
        LogModel log = new LogModel.LogModelBuilder()
                .basicInfo("004", "admin4"
                        , "2021-03-31 16:30:56", "将要保存到数据库的日志!")
                .build();

        LogDbOperate logManager = new LogDbOperate();

        logManager.insert(log);
        logManager.update(log);
    }
}