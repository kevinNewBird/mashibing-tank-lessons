package com.mashibing.dm.visitor.demo4.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description： B对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 16:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class B implements A{

    private String name;

    private C c;

    private E e;
}
