package com.mashibing.dm.adapter.demo1.source.file;

import com.mashibing.dm.adapter.demo1.base.file.LogFileOperate;
import com.mashibing.dm.adapter.demo1.base.domain.LogModel;

import java.util.Collections;
import java.util.List;

/***********************
 * @Description: 日志操作客户端 <BR>
 * @author: zhao.song
 * @since: 2021/3/31 14:36
 * @version: 1.0
 ***********************/
public class LogFileOperateClient {

    public static void main(String[] args) {
        //创建操作日志文件的对象
        LogFileOperate logManager = new LogFileOperate();

        //准备日志内容
        LogModel log = new LogModel.LogModelBuilder()
                .basicInfo("003", "admin"
                        , "2021-03-31 14:42:44", "将要写入文件的日志!")
                .build();

        //保存日志文件
        logManager.writeLogFile(Collections.singletonList(log));

        List<LogModel> rlList1 = logManager.readLogFile();
        System.out.println("readLogList=" + rlList1);
    }
}
