package com.mashibing.dm.adapter.v1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***********************
 * @Description: 日志操作客户端 <BR>
 * @author: zhao.song
 * @since: 2021/3/31 14:36
 * @version: 1.0
 ***********************/
public class LogOperateClient {

    public static void main(String[] args) {
        //创建操作日志文件的对象
        LogFileOperate lfo = new LogFileOperate();

        List<LogModel> rlList0 = lfo.readLogFile();
        System.out.println("readLogList=" + rlList0);
        System.out.println("------------------------------------------");
        //准备日志内容
        LogModel lm = new LogModel.LogModelBuilder()
                .basicInfo("003", "admin", "2021-03-31 14:42:44", "这是一个测试3")
                .build();

//        LogModel lm = new LogModel();
//        lm.setLogId("001");
//        lm.setOperateUser("admin");
//        lm.setOperateTime("2021-03-31 14:42:39");
//        lm.setLogContent("这是一个测试");

        List<LogModel> list = new ArrayList<>(Arrays.asList(lm));
//        list.addAll(rlList0);
        //保存日志文件
        lfo.writeLogFile(list);

        System.out.println("------------------------------------------");

        List<LogModel> rlList1 = lfo.readLogFile();
        System.out.println("readLogList=" + rlList1);
    }
}
