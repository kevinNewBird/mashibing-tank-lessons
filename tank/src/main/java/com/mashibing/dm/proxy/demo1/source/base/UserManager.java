package com.mashibing.dm.proxy.demo1.source.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 实现示例要求的功能
 */
public class UserManager {

    public Collection<UserModel> getUserByDepId(String depId) {
        // 一次性获取所有查询结果（不分页）
        List<Map<String, String>> rs = UserDao.getInstance().queryAllFields(depId + "%");

        // 封装结果
        Collection<UserModel> col = new ArrayList<>();
        for (Map<String, String> data : rs) {
            UserModel um = new UserModel();
            um.setUserId(data.get("userId"));
            um.setUserName(data.get("name"));
            um.setDepId(data.get("depId"));
            um.setSex(data.get("sex"));

            col.add(um);
        }
        return col;
    }


}
