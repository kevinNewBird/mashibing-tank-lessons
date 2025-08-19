package com.mashibing.dm.proxy.demo1.source;

import com.mashibing.dm.proxy.demo1.source.base.UserManager;
import com.mashibing.dm.proxy.demo1.source.base.UserModel;

import java.util.Collection;

/**
 * 一次性获取所有查询结果（不分页）
 */
public class UserManagerClient {

    public static void main(String[] args) {

        /**
         * 存在问题：当一次性访问的数据条数过多，而且每条描述的数据量又很大的话，将会消耗较多的内存。
         */
        UserManager manager = new UserManager();
        Collection<UserModel> col = manager.getUserByDepId("0101");
        System.out.printf("查询结果为：%s\n", col);
    }
}
