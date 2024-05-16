package com.mashibing.dm.interpreter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Context {

    private Document document = null;

    /**
     * 上一次被处理的多个元素
     */
    private List<Element> preEles = new ArrayList<Element>();

    public Context(String filePathName) throws Exception {
        this.document = XmlUtil.getRoot(filePathName);
    }

    /**
     * 重新初始化上下文
     */
    public void reInit() {
        preEles = new ArrayList<>();
    }

    public List<Element> getNowEles(Element pEle, String eleName) {
        List<Element> elements = new ArrayList<>();
        NodeList tmpNodeList = pEle.getChildNodes();
        for (int i = 0; i < tmpNodeList.getLength(); i++) {
            if (tmpNodeList.item(i) instanceof Element) {
                Element nowEle = (Element) tmpNodeList.item(i);
                if (nowEle.getTagName().equals(eleName)) {
                    elements.add(nowEle);
                }
            }
        }

        return elements;
    }

    public Document getDocument() {
        return document;
    }

    public List<Element> getPreEles() {
        return preEles;
    }

    public void setPreEles(List<Element> nowEles) {
        this.preEles = nowEles;
    }
}

