package com.mashibing.dm.flyweight.demo1.base;

import java.util.ArrayList;
import java.util.Collection;

/**
 * description：供测试用，在内存中模拟数据库中的值
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/29 12:02
 */
public class TestDB {

    /**
     * 用来存放授权数据的值
     */
    public static Collection<String> dbData = new ArrayList<>();

    static {
        // 填充模拟的数据
        dbData.add("张三,人员列表,查看");
        dbData.add("李四,人员列表,查看");
        dbData.add("李四,薪资数据,查看");
        dbData.add("李四,薪资数据,修改");

        // 增加更多的授权数据
        for (int i = 0; i < 3; i++) {
            dbData.add(String.format("张三%s,人员列表,查看", i));
        }
    }
}
