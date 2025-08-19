package com.mashibing.dm.mediator.demo2.base;

import com.mashibing.dm.mediator.demo2.pattern.DepUserMediatorImpl;
import lombok.Data;

/**
 * 部门类
 */
@Data
public class Dep {

    private String depId;
    private String depName;

    /**
     * 撤销部门
     * @return 是否撤销成功
     */
    public boolean deleteDep(){
        // 1.要先通过中介者去除掉所有与这个部门相关的部门和人员的关系
        DepUserMediatorImpl.getInstance().deleteDep(depId);

        // 2.然后才能真正地清除掉这个部门（实际开发中，应是作为历史数据保留）
        return true;
    }

}
