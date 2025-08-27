package com.mashibing.dm.visitor.demo4.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description：C对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 17:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class C implements A{

    private String name;

    private D d;
}
