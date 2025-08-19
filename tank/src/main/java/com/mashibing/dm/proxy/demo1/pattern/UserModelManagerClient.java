package com.mashibing.dm.proxy.demo1.pattern;

import com.mashibing.dm.proxy.demo1.pattern.base.UserModelApi;
import com.mashibing.dm.proxy.demo1.pattern.base.UserModelManager;

import java.util.Collection;

/**
 * 一次性获取所有查询结果（不分页）
 */
public class UserModelManagerClient {

    /**
     * 潜在问题：如果客户对每条用户数据都要求查看详细数据的话，那么总的查询数据库的次数会是1+N次之多。
     * 适用场景：客户大多数情况下只需要查看用户编号和姓名，而少量的数据需要查看详细数据。这样既节省了内存，又减少了操作数据库的次数。
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 使用代理模式
         */
        UserModelManager manager = new UserModelManager();
        Collection<UserModelApi> col = manager.getUserByDepId("0101");
        System.out.printf("查询结果为：%s\n", col);
        /**
         * [UserModelImpl(userId=user001, userName=张三1, sex=null, depId=null), UserModelImpl(userId=user002, userName=张三2, sex=null, depId=null)
         * , UserModelImpl(userId=user003, userName=张三3, sex=null, depId=null)]
         */

        for (UserModelApi userModelApi : col) {
            System.out.printf("用户编号：%s，用户姓名：%s，所属部门：%s，性别：%s\n", userModelApi.getUserId()
                    , userModelApi.getUserName(), userModelApi.getDepId(), userModelApi.getSex());
        }

        /**
         * 重新查询数据库获取完整的用户数据， userId=user001执行sql：select u.userid,u.name,u.depid,u.sex from tb_user uwhere u.userId = user001
         * 用户编号：user001，用户姓名：张三1，所属部门：010101，性别：男
         * 重新查询数据库获取完整的用户数据， userId=user002执行sql：select u.userid,u.name,u.depid,u.sex from tb_user uwhere u.userId = user002
         * 用户编号：user002，用户姓名：张三2，所属部门：010101，性别：男
         * 重新查询数据库获取完整的用户数据， userId=user003执行sql：select u.userid,u.name,u.depid,u.sex from tb_user uwhere u.userId = user003
         * 用户编号：user003，用户姓名：张三3，所属部门：010101，性别：男
         */
    }
}
