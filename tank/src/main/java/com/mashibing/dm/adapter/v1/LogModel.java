package com.mashibing.dm.adapter.v1;

import java.io.Serializable;

/***********************
 * @Description: 日志数据对象 <BR>
 * @author: zhao.song
 * @since: 2021/3/31 14:03
 * @version: 1.0
 ***********************/
public class LogModel implements Serializable {

    public static final long serialVersionUID = 1111L;

    /**
     * 日志编号
     */
    private String logId;

    /**
     * 操作人员
     */
    private String operateUser;


    /**
     * 操作时间,以yyyy-MM-dd HH:mm:ss格式记录
     */
    private String operateTime;

    /**
     * 记录内容
     */
    private String logContent;

    public String getLogId() {
        return logId;
    }

    //构造方法
    private LogModel() {

    }

/*    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }*/

    // toString
    @Override
    public String toString() {
        return "LogModel{" +
                "logId='" + logId + '\'' +
                ", operateUser='" + operateUser + '\'' +
                ", operateTime='" + operateTime + '\'' +
                ", logContent='" + logContent + '\'' +
                '}';
    }

    /**
     * 日志数据对象构建器
     */
    public static class LogModelBuilder {
        LogModel lmInstance = new LogModel();

        public LogModelBuilder basicInfo(String logId, String operateUser, String operateTime, String logContent) {
            lmInstance.logId = logId;
            lmInstance.operateUser = operateUser;
            lmInstance.operateTime = operateTime;
            lmInstance.logContent = logContent;
            return this;
        }

        public LogModel build() {
            return lmInstance;
        }
    }
}
