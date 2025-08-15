package com.mashibing.dm.adapter.pattern;

import com.mashibing.dm.adapter.base.db.LogDbOperate;

public class LogFileToDbAdapterTest {

    public static void main(String[] args) {
        // 1.创建被适配对象
        LogDbOperate logManager = new LogDbOperate();
        LogFileToDbAdapter adapter = new LogFileToDbAdapter(logManager);

        System.out.println(adapter.readLogFile());
    }
}
