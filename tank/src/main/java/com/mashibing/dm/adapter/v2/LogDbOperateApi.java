package com.mashibing.dm.adapter.v2;

import com.mashibing.dm.adapter.v1.LogModel;

import java.util.List;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/31 15:51
 * @version: 1.0
 ***********************/
public interface LogDbOperateApi {
    public void createLog(LogModel lm);

    public List<LogModel> getAllLog();

    public void removeLog(LogModel lm);

    public void updateLog(LogModel lm);
}
