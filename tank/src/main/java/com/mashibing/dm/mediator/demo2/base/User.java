package com.mashibing.dm.mediator.demo2.base;

import com.mashibing.dm.mediator.demo2.pattern.DepUserMediatorImpl;
import lombok.Data;

/**
 * 人员类
 */
@Data
public class User {
    private String userId;
    private String userName;

    /**
     * 人员离职
     * @return 是否处理成功
     */
    public boolean dimission(){
        // 1.要先通过中介者去除掉所有与这个人员相关的部门和人员的关系
        DepUserMediatorImpl.getInstance().deleteUser(userId);

        // 2.然后才能真正地清除掉这个人员（实际开发中，逻辑删除人员记录）
        return true;
    }
}
