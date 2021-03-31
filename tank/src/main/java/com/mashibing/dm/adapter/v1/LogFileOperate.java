package com.mashibing.dm.adapter.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/***********************
 * @Description: 实现对日志文件的操作 <BR>
 * @author: zhao.song
 * @since: 2021/3/31 14:18
 * @version: 1.0
 ***********************/
public class LogFileOperate implements LogFileOperateApi {

    private static Logger logger = LoggerFactory.getLogger(LogFileOperate.class);

    /**
     * 日志文件的路径和文件名称,默认是当前项目根下的AdapterLog.log
     */
    private String logFilePathName = "AdapterLog.log";

    // 无参构造方法
    public LogFileOperate() {
    }

    //有参构造,传入文件路径和名称
    public LogFileOperate(String logFilePathName) {
        //判断文件名,不为空时重设置日志文件的路径和名称
        if (!StringUtils.isEmpty(logFilePathName)) {
            this.logFilePathName = logFilePathName;
        }

    }

    @Override
    public List<LogModel> readLogFile() {
        File lf = new File(logFilePathName);
        List<LogModel> logList = new ArrayList<>();

        if (lf.exists()) {
            try (InputStream fis = new FileInputStream(lf);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                logList = (List<LogModel>) ois.readObject();

            } catch (Exception e) {
                logger.error("读取日志列表异常!", e);
            }
        }
        return logList;
    }

    @Override
    public void writeLogFile(List<LogModel> logList) {
        Objects.requireNonNull(logList, "写入日志列表为空!");
        File lf = new File(logFilePathName);
        try (OutputStream fos = new FileOutputStream(lf, false);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(logList);

        } catch (Exception e) {
            logger.error("写日志列表异常!", e);
        }
    }
}
