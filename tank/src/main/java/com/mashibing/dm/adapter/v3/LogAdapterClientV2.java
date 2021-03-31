package com.mashibing.dm.adapter.v3;

import com.mashibing.dm.adapter.v1.LogModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***********************
 * @Description: 类适配器客户端<BR>
 * @author: zhao.song
 * @since: 2021/3/31 16:45
 * @version: 1.0
 ***********************/
public class LogAdapterClientV2 {

    public static void main(String[] args) {
        //准备日志内容,也就是测试数据
        LogModel lm = new LogModel.LogModelBuilder()
                .basicInfo("005", "admin5", "2021-03-31 16:47:56", "我是适配器测试5!")
                .build();
        List<LogModel> list = new ArrayList<>(Arrays.asList(lm));

        DbClassAdapter dca0 = new DbClassAdapter("AdapterLog2.log");
        dca0.createLog(lm);
        System.out.println(dca0.getAllLog());

    }
}
