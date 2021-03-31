package com.mashibing.dm.adapter.v2;

import com.mashibing.dm.adapter.v1.LogFileOperate;
import com.mashibing.dm.adapter.v1.LogFileOperateApi;
import com.mashibing.dm.adapter.v1.LogModel;

import java.util.List;
import java.util.stream.Collectors;

/***********************
 * @Description: 双向适配器 <BR>
 * @author: zhao.song
 * @since: 2021/3/31 15:54
 * @version: 1.0
 ***********************/
public class TwoDirectAdapter implements LogDbOperateApi, LogFileOperateApi {
    /**
     * 持有需要被适配的文件存储日志的接口对象
     */
    private LogFileOperateApi lfo;

    /**
     * 持有需要被适配的DB存储日志的接口对象
     */
    private LogDbOperateApi dbl;

    public TwoDirectAdapter(LogFileOperateApi lfo, LogDbOperateApi dbl) {
        this.lfo = lfo;
        this.dbl = dbl;
    }

    /*------------------------以下是把DB操作的方式适配为文件实现方式的接口------------------------*/
    @Override
    public List<LogModel> readLogFile() {
       return dbl.getAllLog();
    }

    @Override
    public void writeLogFile(List<LogModel> logList) {
        //1.最简单的实现思路是先删除数据库中的数据
        //2.然后循环把现在的数据加入到数据库
        logList.forEach(lm -> dbl.createLog(lm));
    }

    /*------------------------以下是把文件操作的方式适配为DB实现方式的接口------------------------*/

    @Override
    public void createLog(LogModel lm) {
        //1.先读取文件的内容
        List<LogModel> list = lfo.readLogFile();
        //2.加入新的日志对象
        list.add(lm);
        //3.重新写入文件
        lfo.writeLogFile(list);
    }

    @Override
    public List<LogModel> getAllLog() {
        return lfo.readLogFile();
    }

    @Override
    public void removeLog(LogModel lm) {
        //1.先读取文件的内容
        List<LogModel> list = lfo.readLogFile();
        //2.删除相应的日志对象
        list.remove(lm);
        //3.重新写入文件
        lfo.writeLogFile(list);

    }

    @Override
    public void updateLog(LogModel lm) {
        //1.先读取文件的内容
        List<LogModel> list = lfo.readLogFile();
        //2.修改相应的日志对象
        List<LogModel> nList = list.stream().map(olm -> {
            if (olm.getLogId().equalsIgnoreCase(lm.getLogId())) {
                return lm;
            }
            return olm;
        }).collect(Collectors.toList());
        lfo.writeLogFile(nList);
    }

}
