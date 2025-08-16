package com.mashibing.dm.adapter.demo1.pattern;

import com.mashibing.dm.adapter.demo1.base.db.LogDbOperate;

public class LogFileToDbAdapterTest {

    public static void main(String[] args) {
        // 1.创建被适配对象
        LogDbOperate logManager = new LogDbOperate();
        LogFileToDbAdapter adapter = new LogFileToDbAdapter(logManager);

        System.out.println(adapter.readLogFile());
    }
}
