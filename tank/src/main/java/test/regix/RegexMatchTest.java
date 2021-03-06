package test.regix;

import com.sun.prism.impl.shape.BasicRoundRectRep;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/11/20 13:35
 * @version: 1.0
 ***********************/
public class RegexMatchTest {


    final static String regex = "url\\((&quot;|'|\")?https://mmbiz.qpic.cn/[\\S]*(&quot;|'|\")?\\)";

    final static String NUMBER_REGEX = "[1-9][0-9]*";

    public static void main(String[] args) {

        System.out.println(abstractContentByCount("12123", 2));

//        String str = "2321,2321|null|null";
//        String[] array = str.split("[||,]");
//        List<String> collect = Arrays.stream(array).filter(tmpStr -> Pattern.compile(NUMBER_REGEX).matcher(tmpStr).find()).distinct().collect(Collectors.toList());
//        System.out.println(collect);


/*        System.out.println(getStr("I我", "utf-8"));
//        String regex = "(&quot;|')(&amp;|,)";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher("background:url(\"https://mmbiz.qpic.cn/sz_mmbiz_png/iabkKvbOHSesE3IyT5xw3NteY3jkCqAichkbm2lPw0WmlICq7bQTSPQibzZTrS4UQ0WKQnUBNSsW3Vpo01KfPZpIw/640\")");
        while( matcher.find()){
            System.out.println(matcher.group());
        }*/
    }

    private static String abstractContentByCount(String srcContent, int tLen) {
        if (Objects.isNull(srcContent)) {
            return srcContent;
        }
        int oLen = srcContent.length();
        if (oLen <= tLen) {
            return srcContent;
        }else {
            return srcContent.substring(0, tLen);
        }
    }


    public static String getStr(String var0, String var1) {
        if (var1 != null && var1.length() != 0) {
            try {
                byte[] var2 = new byte[var0.length()];
                char[] var3 = var0.toCharArray();

                for(int var4 = var2.length - 1; var4 >= 0; --var4) {
                    System.out.println(var3[var4]);
                    var2[var4] = (byte)var3[var4];
                    System.out.println(var2[var4]);
                }

                return new String(var0.getBytes("UTF-16"), "UTF-16");
            } catch (Exception var5) {
                return var0;
            }
        } else {
            return var0;
        }
    }


}
