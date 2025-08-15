package com.mashibing.dm.adapter.pattern;

import com.mashibing.dm.adapter.base.db.LogDbOperate;
import com.mashibing.dm.adapter.base.domain.LogModel;
import com.mashibing.dm.adapter.base.file.LogFileOperate;
import com.mashibing.dm.adapter.base.file.LogFileOperateApi;

public class LogDbToFileAdapterTest {

    public static void main(String[] args) {
        // 1.创建被适配对象
        LogFileOperateApi logManager = new LogFileOperate();
        LogDbTOFileAdapter adapter = new LogDbTOFileAdapter(logManager);

        LogModel log = new LogModel.LogModelBuilder()
                .basicInfo("005", "admin5"
                        , "2021-03-31 16:30:56", "将要保存到数据库的日志!")
                .build();

        // 2.run
        adapter.insert(log);
        adapter.queryAll();
    }
}
