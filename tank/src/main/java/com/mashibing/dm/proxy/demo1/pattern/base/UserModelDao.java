package com.mashibing.dm.proxy.demo1.pattern.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户数据层
 */
public class UserModelDao {


    /**
     * select u.userId,u.name,u.depId,u.sex from tb_user u,tb1_dep d where u.depid = d.depId and d.depId like ?
     *
     * @return
     */
    public List<Map<String, String>> queryAllFields(String condition) {
        String sql = String.format("select u.userid,u.name,u.depid,u.sex " +
                "from tb_user u,tb1_dep d " +
                "where u.depid = d.depId and d.depId like %s", condition);
        System.out.printf("执行sql：%s\n", sql);

        return new ArrayList<Map<String, String>>() {{
            add(buildUser("user001", "张三1", "010101", "男"));
            add(buildUser("user002", "张三2", "010101", "男"));
            add(buildUser("user003", "张三3", "010102", "男"));
        }};
    }

    public Map<String, String> queryByUserId(String userId) {
        String sql = String.format("select u.userid,u.name,u.depid,u.sex " +
                "from tb_user u" +
                "where u.userId = %s", userId);
        System.out.printf("执行sql：%s\n", sql);

        // 只需要重新获取除了userId和name外的数据
        return buildUser("010101", "男");
    }

    private Map<String, String> buildUser(String depId, String sex) {
        return new HashMap<String, String>() {{
            put("depId", depId);
            put("sex", sex);
        }};
    }

    private Map<String, String> buildUser(String uid, String uname, String depId, String sex) {
        return new HashMap<String, String>() {{
            put("userId", uid);
            put("name", uname);
            put("depId", depId);
            put("sex", sex);
        }};
    }

    public static UserModelDao getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private static final UserModelDao INSTANCE = new UserModelDao();
    }
}
