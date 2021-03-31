package com.mashibing.dm.adapter.v3;

import com.mashibing.dm.adapter.v1.LogFileOperate;
import com.mashibing.dm.adapter.v1.LogModel;
import com.mashibing.dm.adapter.v2.LogDbOperateApi;

import java.util.List;
import java.util.stream.Collectors;

/***********************
 * @Description: [数据库存储方式] 适配 [文件方式]<BR>
 * @author: zhao.song
 * @since: 2021/3/31 16:40
 * @version: 1.0
 ***********************/
public class DbClassAdapter extends LogFileOperate implements LogDbOperateApi {


    public DbClassAdapter(String logFilePathName) {
        super(logFilePathName);
    }

    @Override
    public void createLog(LogModel lm) {
        //1.先读取文件的内容
        List<LogModel> list = this.readLogFile();
        //2.加入新的日志对象
        list.add(lm);
        //3.重新写入文件
        this.writeLogFile(list);
    }

    @Override
    public List<LogModel> getAllLog() {
        return this.readLogFile();
    }

    @Override
    public void removeLog(LogModel lm) {
        //1.先读取文件的内容
        List<LogModel> list = this.readLogFile();
        //2.删除
        list.remove(lm);
        //3.重新写入文件
        this.writeLogFile(list);
    }

    @Override
    public void updateLog(LogModel lm) {
        //1.先读取文件的内容
        List<LogModel> list = this.readLogFile();
        //2.修改相应的日志对象
        List<LogModel> nList = list.stream().map(olm -> {
            if (olm.getLogId().equalsIgnoreCase(lm.getLogId())) {
                return lm;
            }
            return olm;
        }).collect(Collectors.toList());
        this.writeLogFile(nList);
    }
}
