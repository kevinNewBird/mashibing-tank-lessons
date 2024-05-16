package mashibing.tank.frame;

import java.util.*;

/**
 * description  MaxMinAllocate <BR>
 * <p>
 * author: zhao.song
 * date: created in 14:53  2022/7/25
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class MaxMinAllocate {

    public static void main(String[] args) {
        //总共资源数
        int total = 137;
//        List<Integer> needList = new ArrayList<>(Arrays.asList(10, 22, 33, 45, 1, 2, 34, 12));
        List<Integer> needList = new ArrayList<>(Arrays.asList(17, 17, 17, 17, 17, 17, 17, 17));
        int n = needList.size();
        //求出平均值
        int average = total / n;
        System.out.println("资源的平均数为：" + average);
        //结果集
        List<Integer> result = new ArrayList<>();
        //剩余数目
        int remain = 0;
        //存入满足的坐标
        Map<Integer, Boolean> okMap = new HashMap<>();
//        第一轮分配
        for (int i = 0; i < n; i++) {
            if (needList.get(i) >= average) {
                result.add(i, average);
            } else {
                result.add(i, needList.get(i));
                okMap.put(i, true);
                remain += average - needList.get(i);
            }
        }
        System.out.println("分配需求数据：" + needList);
        System.out.println("第一次分配结果：" + result + ",剩下的结果：" + remain);
        //如果还有剩余继续分配
        while (remain > 0) {
            if (n == okMap.size()) break;
            average = remain / (n - okMap.size());
            remain = 0;
            for (int i = 0; i < n; i++) {
                if (Objects.nonNull(okMap.get(i)) && okMap.get(i)) {
                    continue;
                }
                if (needList.get(i) >= average + result.get(i)) {
                    result.set(i, average + result.get(i));
                } else {
                    System.out.println("第" + i + "个已经满足需求");
                    okMap.put(i, true);
                    remain += average + result.get(i) - needList.get(i);
                    result.set(i, needList.get(i));
                }
            }
        }
        System.out.println("分配需求数据：" + needList);
        System.out.println("最终分配结果：" + result + ",剩下资源：" + remain);
    }
}
