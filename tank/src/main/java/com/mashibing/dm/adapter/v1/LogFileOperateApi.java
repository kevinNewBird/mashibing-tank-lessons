package com.mashibing.dm.adapter.v1;

import java.util.List;

/***********************
 * @Description: 日志文件操作接口 <BR>
 * @author: zhao.song
 * @since: 2021/3/31 14:06
 * @version: 1.0
 ***********************/
public interface LogFileOperateApi {

    /**
     * Description: 读取日志文件,从文件里面获取存储的日志列表对象 <BR>
     *
     * @author zhao.song    2021/3/31 14:16
     * @param :
     * @return {@link java.util.List<com.mashibing.dm.adapter.v1.LogModel>}
     */
    public List<LogModel> readLogFile();

    /**
     * Description: 写入日志文件,把日志列表写出到日志文件中去 <BR>
     *
     * @author zhao.song    2021/3/31 14:17
     * @param logList:  要写入到日志文件的日志列表
     * @return
     */
    public void writeLogFile(List<LogModel> logList);
}
