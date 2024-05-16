package com.mashibing.dm.interpreter;

import org.w3c.dom.Element;

import java.util.List;

/**
 * 单个元素作为终结符的解释器
 */
public class ElementTerminalExpression extends ReadXmlExpression {

    private String eleName = "";

    public ElementTerminalExpression(String name) {
        this.eleName = name;
    }

    @Override
    public String[] interpret(Context c) {
        // 先取出上下文中的当前元素作为父级元素
        List<Element> pEles = c.getPreEles();
        // 查找当前元素名称所对应的xml元素
        Element ele = null;
        if (pEles.isEmpty()) {
            // 说明当前获取的是根元素
            ele = c.getDocument().getDocumentElement();
        } else {
            // 获取当前的元素
            ele = c.getNowEles(pEles.get(0), eleName).get(0);
        }

        // 然后来获取这个元素的值
        String[] ss = new String[1];
        ss[0] = ele.getFirstChild().getNodeValue();
        return ss;
    }


}
