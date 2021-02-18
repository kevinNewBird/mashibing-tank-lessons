package test.regix;

import com.sun.prism.impl.shape.BasicRoundRectRep;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
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

    private static final Pattern QUERY_PARAM_PATTERN = Pattern.compile("([^&=]+)(=?)([^&]+)?");

    private static final String SCHEME_PATTERN = "([^:/?#]+):";

    private static final String HTTP_PATTERN = "(?i)(http|https):";

    private static final String USERINFO_PATTERN = "([^@\\[/?#]*)";

    private static final String HOST_IPV4_PATTERN = "[^\\[/?#:]*";

    private static final String HOST_IPV6_PATTERN = "\\[[\\p{XDigit}\\:\\.]*[%\\p{Alnum}]*\\]";

    private static final String HOST_PATTERN = "(" + HOST_IPV6_PATTERN + "|" + HOST_IPV4_PATTERN + ")";

    private static final String PORT_PATTERN = "(\\d*(?:\\{[^/]+?\\})?)";

    private static final String PATH_PATTERN = "([^?#]*)";

    private static final String QUERY_PATTERN = "([^#]*)";

    private static final String LAST_PATTERN = "(.*)";



    final static String regex = "url\\((&quot;|'|\")?https://mmbiz.qpic.cn/[\\S]*(&quot;|'|\")?\\)";

    final static String NUMBER_REGEX = "[1-9][0-9]*";


    final static String URL_REGEX = "(^.+?\\.\\w+\\.[a-z]+$)";

    private static final Pattern URI_PATTERN = Pattern.compile(
            "^(" + SCHEME_PATTERN + ")?" + "(//(" + USERINFO_PATTERN + "@)?" + HOST_PATTERN + "(:" + PORT_PATTERN +
                    ")?" + ")?" + PATH_PATTERN + "(\\?" + QUERY_PATTERN + ")?" + "(#" + LAST_PATTERN + ")?");

    private static final Pattern HTTP_URL_PATTERN = Pattern.compile(
            "^" + HTTP_PATTERN + "(//(" + USERINFO_PATTERN + "@)?" + HOST_PATTERN + "(:" + PORT_PATTERN + ")?" + ")?" +
                    PATH_PATTERN + "(\\?" + LAST_PATTERN + ")?");

    public static void main(String[] args) {

//        System.out.println(abstractContentByCount("12123", 2));

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
        System.out.println(new Date().getTime());
        fromUriString("http://hk.cannews.com.cn/mcn/#!backstage");
        fromHttpUrl("http://hk.cannews.com.cn/mcn/#!backstage");
        String IDSRedirectToMCN = "https://sly.trscd.com.cn/mcnadmin/web/user/ids.do";

        final Pattern pattern = Pattern.compile(URL_REGEX,Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher("www.baidu");
        while( matcher.find()){
            System.out.println(matcher.group(1));
        }
    }

    public static void fromUriString(String uri) {
        Assert.notNull(uri, "URI must not be null");
        Matcher matcher = URI_PATTERN.matcher(uri);
        if (matcher.matches()) {
            String scheme = matcher.group(2);
            String userInfo = matcher.group(5);
            String host = matcher.group(6);
            String port = matcher.group(8);
            String path = matcher.group(9);
            String query = matcher.group(11);
            String fragment = matcher.group(13);
            boolean opaque = false;
            if (StringUtils.hasLength(scheme)) {
                String rest = uri.substring(scheme.length());
                if (!rest.startsWith(":/")) {
                    opaque = true;
                }
            }
            if (opaque) {
                String ssp = uri.substring(scheme.length()).substring(1);
                if (StringUtils.hasLength(fragment)) {
                    ssp = ssp.substring(0, ssp.length() - (fragment.length() + 1));
                }
            }
        }
        else {
            throw new IllegalArgumentException("[" + uri + "] is not a valid URI");
        }
    }

    public static void fromHttpUrl(String httpUrl) {
        Assert.notNull(httpUrl, "HTTP URL must not be null");
        Matcher matcher = HTTP_URL_PATTERN.matcher(httpUrl);
        if (matcher.matches()) {
            String scheme = matcher.group(1);
            String g4 = matcher.group(4);
            String host = matcher.group(5);
            if (StringUtils.hasLength(scheme) && !StringUtils.hasLength(host)) {
                throw new IllegalArgumentException("[" + httpUrl + "] is not a valid HTTP URL");
            }
            String port = matcher.group(7);

            final String path = matcher.group(8);
            final String group = matcher.group(10);
        }
        else {
            throw new IllegalArgumentException("[" + httpUrl + "] is not a valid HTTP URL");
        }
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
