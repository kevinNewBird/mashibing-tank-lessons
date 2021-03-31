package com.mashibing.dm.adapter.v2;

import com.mashibing.dm.adapter.v1.LogModel;

import java.util.List;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/31 15:50
 * @version: 1.0
 ***********************/
public class LogDbOperate implements LogDbOperateApi {

    @Override
    public void createLog(LogModel lm) {
        System.out.println("now in LogDbOperate createLog,lml=" + lm);
    }

    @Override
    public List<LogModel> getAllLog() {
        System.out.println("now in LogDbOperate getAllLog");
        return null;
    }

    @Override
    public void removeLog(LogModel lm) {
        System.out.println("now in LogDbOperate removeLog,lm=" + lm);
    }

    @Override
    public void updateLog(LogModel lm) {
        System.out.println("now in LogDbOperate updateLog,lm=" + lm);
    }
}
