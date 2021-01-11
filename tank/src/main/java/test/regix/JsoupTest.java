package test.regix;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/***********************
 * Description: TODO <BR>
 * author: zhao.song
 * date: 2021/1/4 19:31
 * version: 1.0
 ***********************/
public class JsoupTest {

    private static final String oHtmlTag = "<img url='ss'/><p>文字</p><img alt=';'/>";

    public static void main(String[] args) {
        Document jDoc = Jsoup.parse(oHtmlTag);
        //禁用文档的格式优化-开启的话html代码会被格式化，再返回给前端的时候会多出[/n][空格]等
        jDoc.outputSettings(new Document.OutputSettings().prettyPrint(false));
        Elements img = jDoc.getElementsByTag("img");
        for (Element ele : img) {
            System.out.println(ele.hasAttr("alt"));
            System.out.println(ele.attr("url"));
            ele.attr("alt", "新");
        }
        System.out.println(jDoc.body().html());


    }
}
