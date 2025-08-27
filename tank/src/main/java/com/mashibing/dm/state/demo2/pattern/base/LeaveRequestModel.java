package com.mashibing.dm.state.demo2.pattern.base;

import lombok.Data;

/**
 * description：请假单业务数据模型
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 17:20
 */
@Data
public class LeaveRequestModel {

    /**
     * 请假人
     */
    private String user;

    /**
     * 请假开始时间
     */
    private String beginDate;

    /**
     * 请假天数
     */
    private int leaveDays;

    /**
     * 审核结果
     */
    private String result;
}
