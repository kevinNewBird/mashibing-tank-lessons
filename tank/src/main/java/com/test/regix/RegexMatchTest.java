package com.test.regix;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/11/20 13:35
 * @version: 1.0
 ***********************/
public class RegexMatchTest {


    final static String regex = "url\\((&quot;|'|\")?https://mmbiz.qpic.cn/[\\S]*(&quot;|'|\")?\\)";

    public static void main(String[] args) {
//        String regex = "(&quot;|')(&amp;|,)";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher("background:url(\"https://mmbiz.qpic.cn/sz_mmbiz_png/iabkKvbOHSesE3IyT5xw3NteY3jkCqAichkbm2lPw0WmlICq7bQTSPQibzZTrS4UQ0WKQnUBNSsW3Vpo01KfPZpIw/640\")");
        while( matcher.find()){
            System.out.println(matcher.group());
        }
    }


}
