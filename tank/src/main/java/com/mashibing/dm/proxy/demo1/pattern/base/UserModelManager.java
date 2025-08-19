package com.mashibing.dm.proxy.demo1.pattern.base;

import com.mashibing.dm.proxy.demo1.source.base.UserDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 实现示例要求的功能
 */
public class UserModelManager {

    public Collection<UserModelApi> getUserByDepId(String depId) {
        // 一次性获取所有查询结果（不分页）
        List<Map<String, String>> rs = UserDao.getInstance().queryAllFields(depId + "%");

        // 封装结果
        Collection<UserModelApi> col = new ArrayList<>();
        for (Map<String, String> data : rs) {
            UserModelProxy proxy = new UserModelProxy(new UserModelImpl());
            proxy.setUserId(data.get("userId"));
            proxy.setUserName(data.get("name"));

            col.add(proxy);
        }
        return col;
    }


}
