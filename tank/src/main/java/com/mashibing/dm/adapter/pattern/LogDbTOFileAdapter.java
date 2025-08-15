package com.mashibing.dm.adapter.pattern;

import com.mashibing.dm.adapter.base.db.LogDbOperateApi;
import com.mashibing.dm.adapter.base.domain.LogModel;
import com.mashibing.dm.adapter.base.file.LogFileOperateApi;
import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.List;

public class LogDbTOFileAdapter implements LogDbOperateApi {

    /**
     * 持有需要被适配的接口对象
     */
    private LogFileOperateApi adaptee;

    /**
     *
     * @param adaptee ：需要被适配的对象
     */
    public LogDbTOFileAdapter(LogFileOperateApi adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void insert(LogModel log) {
        // 1.读取文件内容
        List<LogModel> contentList = adaptee.readLogFile();
        // 2.加入新的日志对象
        contentList.add(log);
        // 3.重新写入文件
        adaptee.writeLogFile(contentList);
    }

    @Override
    public void update(LogModel log) {
        // 1.读取文件内容
        List<LogModel> contentList = adaptee.readLogFile();
        // 2.修改对应的日志对象
        for (int i = 0; i < contentList.size(); i++) {
            if (StringUtils.equals(contentList.get(i).getLogId(), log.getLogId())) {
                contentList.set(i, log);
                break;
            }
        }
        // 3.重新写入文件
        adaptee.writeLogFile(contentList);
    }

    @Override
    public List<LogModel> queryAll() {
        return adaptee.readLogFile();
    }

    @Override
    public void delete(LogModel log) {
        // 1.读取文件内容
        List<LogModel> contentList = adaptee.readLogFile();
        // 2.删除相应的日志
        contentList.remove(log);
        // 3.重新写入文件
        adaptee.writeLogFile(contentList);
    }
}
