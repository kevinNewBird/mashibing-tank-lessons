package com.mashibing.dm.adapter.demo1.pattern;

import com.mashibing.dm.adapter.demo1.base.db.LogDbOperate;
import com.mashibing.dm.adapter.demo1.base.db.LogDbOperateApi;
import com.mashibing.dm.adapter.demo1.base.domain.LogModel;
import com.mashibing.dm.adapter.demo1.base.file.LogFileOperateApi;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * 适配器：把日志文件存储到数据库中
 */
public class LogFileToDbAdapter implements LogFileOperateApi {

    // 持有需要被适配的接口对象
    private final LogDbOperateApi adaptee;

    /**
     * 需要被适配的对象
     * @param adaptee
     */
    public LogFileToDbAdapter(LogDbOperateApi adaptee) {
        this.adaptee = adaptee;
    }

    /**
     * 读取日志
     * @return
     */
    @Override
    public List<LogModel> readLogFile() {
        // 转调已经实现了的方法进行适配
        return adaptee.queryAll();
    }

    @Override
    public void writeLogFile(List<LogModel> logList) {
        if (CollectionUtils.isEmpty(logList)) {
            return;
        }
        // 将日志写入数据库
        logList.forEach(adaptee::insert);
    }
}
