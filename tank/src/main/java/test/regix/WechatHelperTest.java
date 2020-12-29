package test.regix;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***********************
 * Description: TODO <BR>
 * author: zhao.song
 * date: 2020/12/14 16:17
 * version: 1.0
 ***********************/
public class WechatHelperTest {

    //微信所有http链接正则匹配表达式
    public final static String ALL_HTTPS_REGEX = "\"((http|https):\\/\\/.+?)\"";

    //微信采编图片匹配表达式
    public final static String WCMPIC_HTTP_REGEX = "(http|https)://.*([WUP]0|ST)+[123]\\d{3}[01][0-9][0-3][0-9][\\S]*";

    //微信腾讯视频的地址正则匹配表达式
    public final static String TENCENTVIDEO_HTTP_REGEX = "(http|https)://v.qq.com[\\S]*]";


//    //微信iframe标签正则匹配表达式
//    public final static String ALL_IFRAME_REGEX = "<iframe\\s*[^>]*src=\"(http|https)://[^\">]\"/";
//
//    //微信采编图片匹配表达式
//    public final static String ALL_WCMPICS_REGEX = "(http|https)://.*([WUP]0|ST)+[123]\\d{3}[01][0-9][0-3][0-9][\\S]*";
//    public final static String ALL_TENCENTVIDEOS_REGEX = "(http|https)://v.qq.com[\\S]*]";

    public static boolean validateLegalOfHtmlContent2(String oHtmlContent) {
        Document jDocument = Jsoup.parse(oHtmlContent);
        Pattern httpPattern = Pattern.compile(ALL_HTTPS_REGEX);
        int allCount = 0;
        for (Element ele : jDocument.children()) {
            Matcher matcher = httpPattern.matcher(ele.toString());
            while (matcher.find()) {
                System.out.println(matcher.group());
                allCount++;
            }
        }
/*        Matcher allMatcher = httpPattern.matcher(oHtmlContent);
        while (allMatcher.find()) {
            System.out.println(allMatcher.group());
            allCount++;
        }*/
        System.out.println(allCount);

        System.out.println("-----------------------------------------");
        Elements imgs = jDocument.getElementsByTag("img");
        int imgTagCount = 0;
        for (Element ele : imgs) {
            Matcher matcher = httpPattern.matcher(ele.toString());
            while (matcher.find()) {
                System.out.println(matcher.group());
                imgTagCount++;
            }
        }
        System.out.println(imgTagCount);
        System.out.println("-----------------------------------------");
        Elements iframe = jDocument.getElementsByTag("iframe");
        int oIframeCount = 0;
        for (Element ele : iframe) {
            Matcher matcher = httpPattern.matcher(ele.toString());

            while (matcher.find()) {
                System.out.println(matcher.group());
                oIframeCount++;
            }
        }
        System.out.println(oIframeCount);
        return allCount == imgTagCount + oIframeCount;
    }

    /**
     * Description: 校验微信正文是否含有外链(除图片和iframe标签外,图片会自动下载) <BR>
     *
     * @author zhao.song    2020/12/15 9:38
     * @param oHtmlContent:
     * @return {@link boolean}
     */
    public static boolean validateLegalOfHtmlContent(String oHtmlContent) {
        if (StringUtil.isBlank(oHtmlContent)) {
            return true;
        }

        Document jDocument = Jsoup.parse(oHtmlContent);
        Pattern httpPattern = Pattern.compile(ALL_HTTPS_REGEX);
        // 1.获取正文中的所有的http的数量
        List<String> allLinkList = new ArrayList<>();
        for (Element ele : jDocument.children()) {
            Matcher matcher = httpPattern.matcher(ele.toString());
            while (matcher.find()) {
                allLinkList.add(matcher.group().replace("\"",""));
            }
        }

        // 2.获取iframe标签中的http,并移除allLinkList
        Elements iframe = jDocument.getElementsByTag("iframe");
        for (Element ele : iframe) {
            Matcher matcher = httpPattern.matcher(ele.toString());
            while (matcher.find()) {
                allLinkList.remove(matcher.group().replace("\"",""));
            }
        }

        // 3.判断allLinkList的元素是否为采编中心内部资源,是移除集合
        Pattern picPattern = Pattern.compile(WCMPIC_HTTP_REGEX);
        for (int i = allLinkList.size() - 1; i >= 0; i--) {
            String oLink = allLinkList.get(i);
            Matcher matcher = picPattern.matcher(oLink);
            if (matcher.find()) {
                allLinkList.remove(oLink);
            }
        }
        System.out.println(allLinkList);
        return allLinkList.size() == 0;
    }


    public static void main(String[] args) {

        String oStr0 = "<section class=\"_135editor\" data-tools=\"135编辑器\" data-id=\"96075\" style=\"box-sizing: border-box; color: rgb(42, 42, 42); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Hiragino Sans GB&quot;, &quot;Hiragino Sans GB W3&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;WenQuanYi Micro Hei&quot;, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">" +
                "    <section style=\"box-sizing: border-box; display: flex; justify-content: flex-start; align-items: center; padding: 1em 0px;\">" +
                "        <section style=\"box-sizing: border-box; width: 2.5em; transform: rotate(0deg);\">" +
                "            <img class=\"assistant\" src=\"http://172.16.25.100//webpic/W0202012/W020201207/W020201207562380536632.gif\" data-width=\"100%\" data-ratio=\"1.0192307692307692\" data-w=\"52\" oldimg=\"true\" oldsrc=\"W020201207562380536632.gif\" style=\"box-sizing: border-box; border: 0px none; vertical-align: middle; width: 40px;\"/>" +
                "        </section>" +
                "        <section style=\"box-sizing: border-box; background: url(http://172.16.25.100//webpic/W0202012/W020201207/W020201207562378403783.jpg) center bottom / 60px no-repeat;\">" +
                "            <section class=\"135brush\" data-brushtype=\"text\" hm_fix=\"244:614\" style=\"box-sizing: border-box; font-size: 14px; text-align: center; letter-spacing: 1.5px; line-height: 1.75em; color: rgb(62, 62, 62); padding: 20px 1em 0px 0px;\">" +
                "                点击上方<span style=\"box-sizing: border-box; font-size: 16px; color: rgb(84, 141, 212);\">蓝字</span>关注我们" +
                "            </section>" +
                "        </section>" +
                "    </section>" +
                "</section>" +
                "<section class=\"_135editor\" style=\"box-sizing: border-box; color: rgb(42, 42, 42); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Hiragino Sans GB&quot;, &quot;Hiragino Sans GB W3&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;WenQuanYi Micro Hei&quot;, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">" +
                "    <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px; text-align: center;\">" +
                "        <br/>" +
                "    </p>" +
                "    <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px; text-align: center;\">" +
                "        <br/>" +
                "    </p>" +
                "    <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px; text-align: center;\">" +
                "        <br style=\"box-sizing: border-box;\"/>" +
                "    </p>" +
                "</section>" +
                "<section data-tplid=\"101226\" data-tools=\"135编辑器\" style=\"box-sizing: border-box; color: rgb(42, 42, 42); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Hiragino Sans GB&quot;, &quot;Hiragino Sans GB W3&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;WenQuanYi Micro Hei&quot;, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">" +
                "    <section style=\"box-sizing: border-box; background: rgb(183, 25, 21); font-family: 思源黑体;\">" +
                "        <section style=\"box-sizing: border-box; background: url(http://172.16.25.100//webpic/W0202012/W020201207/W020201207562380176948.png) 0% 0% / 100% no-repeat;\">" +
                "            <section class=\"\" style=\"box-sizing: border-box;\">" +
                "                <section style=\"box-sizing: border-box; margin-left: 20px;\">" +
                "                    <section style=\"box-sizing: border-box; width: 1093.94px;\">" +
                "                        <img class=\"assistant\" src=\"http://172.16.25.100//webpic/W0202012/W020201207/W020201207562381748765.png\" data-ratio=\"0.189010989010989\" data-w=\"455\" oldimg=\"true\" oldsrc=\"W020201207562381748765.png\" style=\"box-sizing: border-box; border: 0px none; vertical-align: middle; width: 1093.94px; display: block;\"/>" +
                "                    </section>" +
                "                </section>" +
                "                <section class=\"_135editor\" style=\"box-sizing: border-box;\">" +
                "                    <section class=\"\" style=\"box-sizing: border-box; display: flex; justify-content: center; align-items: center; margin: 1em 0px 0px;\">" +
                "                        <section class=\"\" style=\"box-sizing: border-box; width: 100px;\">" +
                "                            <br/>" +
                "                        </section>" +
                "                    </section>" +
                "                </section>" +
                "                <section class=\"_135editor\" style=\"box-sizing: border-box;\">" +
                "                    <section class=\"\" style=\"box-sizing: border-box; text-align: center;\">" +
                "                        <section class=\"135brush\" data-brushtype=\"text\" style=\"box-sizing: border-box; font-size: 50px; letter-spacing: 4px; padding: 0px 0em; color: rgb(255, 234, 139); font-weight: bold; text-shadow: rgb(162, 14, 10) 1px 3px 1px;\">" +
                "                            <br/>" +
                "                        </section>" +
                "                        <section class=\"135brush\" data-brushtype=\"text\" style=\"box-sizing: border-box; font-size: 28px; letter-spacing: 1.5px; padding: 0px 0em; color: rgb(255, 234, 139); font-weight: bold;\">" +
                "                            2020-12-04" +
                "                        </section>" +
                "                        <section style=\"box-sizing: border-box; display: inline-block; margin-top: 5px;\">" +
                "                            <section class=\"135brush\" data-brushtype=\"text\" style=\"box-sizing: border-box; font-size: 20px; letter-spacing: 1.5px; padding: 5px 1em; color: rgb(255, 234, 139); font-weight: bold; border-top: 1px solid rgb(255, 234, 139); border-bottom: 1px solid rgb(255, 234, 139);\">" +
                "                                弘扬宪法精神 维护" +
                "                            </section>" +
                "                        </section>" +
                "                    </section>" +
                "                </section>" +
                "                <section class=\"\" style=\"box-sizing: border-box;\">" +
                "                    <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "                        <br style=\"box-sizing: border-box;\"/>" +
                "                    </p>" +
                "                </section><a href=\"https://haokan.baidu.com/v?pd=wisenatural&vid=5033749015112446083\">我是一个超链接</a>" +
                "                <section class=\"assistant\" style=\"box-sizing: border-box; width: 1113.94px;\"></section>" +
                "            </section>" +
                "        </section>" +
                "        <section style=\"box-sizing: border-box; background: rgb(155, 8, 0);\">" +
                "            <section class=\"\" style=\"box-sizing: border-box; padding: 0px 15px;\">" +
                "                <section class=\"\" style=\"box-sizing: border-box;\">" +
                "                    <section class=\"_135editor\" data-id=\"88401\" style=\"box-sizing: border-box;\">" +
                "                        <section data-autoskip=\"1\" class=\"135brush\" style=\"box-sizing: border-box; text-align: justify; line-height: 1.75em; letter-spacing: 1.5px; font-size: 14px; color: rgb(255, 234, 139);\">" +
                "                            <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 14px; box-sizing: border-box; padding: 0px;\">" +
                "                                <br style=\"box-sizing: border-box;\"/>" +
                "                            </p>" +
                "                            <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 14px; box-sizing: border-box; padding: 0px; text-align: center; display: inline-block; width: 1083.94px;\">" +
                "                                <iframe data-vidtype=\"1\" src=\"http://v.qq.com/iframe/preview.html?vid=j3209zijwfm\" frameborder=\"0\" align=\"center\" allowfullscreen=\"1\" style=\"box-sizing: border-box; position: relative; z-index: 1; height: 261px; width: 464px;\"></iframe>" +
                "                            </p><br style=\"box-sizing: border-box;\"/>" +
                "                            <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 14px; box-sizing: border-box; padding: 0px;\">" +
                "                                <span style=\"box-sizing: border-box; font-size: 16px;\">国家宪法日（National Constitution Day）是为了增强全社会的宪法意识，弘扬宪法精神，加强宪法实施，全面推进依法治国而设立的节日，于十二届全国人大常委会第十一次会议表决通过，时间为每年的12月4日。2014年12月4日是中国首个国家宪法日</span>" +
                "                            </p>" +
                "                        </section>" +
                "                    </section>" +
                "                    <section class=\"\" style=\"box-sizing: border-box;\">" +
                "                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "                            <br style=\"box-sizing: border-box;\"/>" +
                "                        </p>" +
                "                    </section>" +
                "                    <section class=\"\" style=\"box-sizing: border-box;\">" +
                "                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "                            <br style=\"box-sizing: border-box;\"/>" +
                "                        </p>" +
                "                    </section>" +
                "                    <section class=\"_135editor\" data-id=\"99144\" style=\"box-sizing: border-box;\">" +
                "                        <section style=\"box-sizing: border-box; margin: 0px auto;\">" +
                "                            <section class=\"\" style=\"box-sizing: border-box; text-align: center;\">" +
                "                                <section class=\"assistant\" style=\"box-sizing: border-box; width: 65px; margin: 0px auto;\">" +
                "                                    <section class=\"assistant\" style=\"box-sizing: border-box; width: 65px;\">" +
                "                                        <img class=\"assistant\" src=\"http://172.16.25.100//webpic/W0202012/W020201207/W020201207562382101921.png\" data-width=\"100%\" width=\"65\" height=\"36\" data-ratio=\"0.8484848484848485\" data-w=\"66\" oldimg=\"true\" oldsrc=\"W020201207562382101921.png\" style=\"box-sizing: border-box; border: 0px none; vertical-align: middle; width: 65px; display: block; height: 30px;\"/>" +
                "                                    </section>" +
                "                                </section>" +
                "                                <section class=\"135brush\" data-brushtype=\"text\" hm_fix=\"302:223\" style=\"box-sizing: border-box; display: inline-block; padding: 6px 1em; letter-spacing: 1.5px; background-image: -webkit-linear-gradient(left, rgb(250, 242, 205), rgb(250, 242, 205)); font-weight: bold; color: rgb(155, 8, 0);\">" +
                "                                    国家宪法日的设立背景" +
                "                                </section>" +
                "                            </section>" +
                "                        </section>" +
                "                    </section>" +
                "                    <section class=\"\" style=\"box-sizing: border-box;\">" +
                "                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "                            <br style=\"box-sizing: border-box;\"/>" +
                "                        </p>" +
                "                    </section>" +
                "                    <section class=\"\" style=\"box-sizing: border-box;\">" +
                "                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "                            <br style=\"box-sizing: border-box;\"/>" +
                "                        </p>" +
                "                    </section>" +
                "                    <section style=\"box-sizing: border-box;\">" +
                "                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "                            <br style=\"box-sizing: border-box;\"/>" +
                "                        </p>" +
                "                    </section>" +
                "                    <section class=\"_135editor\" data-id=\"100086\" style=\"box-sizing: border-box;\">" +
                "                        <section style=\"box-sizing: border-box; margin: 10px auto;\">" +
                "                            <section style=\"box-sizing: border-box; margin-top: -50px; transform: rotate(0deg);\">" +
                "                                <section class=\"\" style=\"box-sizing: border-box; background-image: -webkit-linear-gradient(left, rgb(250, 242, 205), rgb(250, 242, 205)); padding: 1.2em 1em;\">" +
                "                                    <section class=\"\" style=\"box-sizing: border-box; text-align: justify; line-height: 1.75em; letter-spacing: 1.5px; font-size: 14px; color: rgb(255, 255, 255);\">" +
                "                                        <section data-autoskip=\"1\" class=\"135brush\" style=\"box-sizing: border-box; line-height: 1.75em; color: rgb(155, 8, 0);\">" +
                "                                            <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 14px; box-sizing: border-box; padding: 0px;\">" +
                "                                                <span style=\"box-sizing: border-box; font-size: 16px;\">12月4日是中国的“全国法制宣传日”。之所以确定这一天为“全国法制宣传日”，是因为中国现行的宪法在1982年12月4日正式实施。</span>" +
                "                                            </p>" +
                "                                        </section>" +
                "                                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 14px; box-sizing: border-box; padding: 0px;\">" +
                "                                            <br style=\"box-sizing: border-box;\"/>" +
                "                                        </p>" +
                "                                        <section data-autoskip=\"1\" class=\"135brush\" style=\"box-sizing: border-box; line-height: 1.75em; color: rgb(155, 8, 0);\">" +
                "                                            <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 14px; box-sizing: border-box; padding: 0px;\">" +
                "                                                <span style=\"box-sizing: border-box; font-size: 16px;\">2014年10月20日至23日，中国共产党第十八届中央委员会第四次全体会议召开，“每年12月4日定为国家宪法日”成为中央全会的共识，并写进了《中共中央关于全面推进依法治国若干重大问题的决定》这一历史性文件中。</span>" +
                "                                            </p>" +
                "                                        </section>" +
                "                                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 14px; box-sizing: border-box; padding: 0px;\">" +
                "                                            <br style=\"box-sizing: border-box;\"/>" +
                "                                        </p>" +
                "                                        <section data-autoskip=\"1\" class=\"135brush\" style=\"box-sizing: border-box; line-height: 1.75em; color: rgb(155, 8, 0);\">" +
                "                                            <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 14px; box-sizing: border-box; padding: 0px;\">" +
                "                                                <span style=\"box-sizing: border-box; font-size: 16px;\">2014年11月1日，十二届全国人大常委会第十一次会议表决通过决定，将12月4日设立为“国家宪法日”。</span>" +
                "                                            </p>" +
                "                                        </section>" +
                "                                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 14px; box-sizing: border-box; padding: 0px;\">" +
                "                                            <br style=\"box-sizing: border-box;\"/>" +
                "                                        </p>" +
                "                                    </section>" +
                "                                </section>" +
                "                            </section>" +
                "                            <section class=\"assistant\" style=\"box-sizing: border-box; display: flex; justify-content: flex-end; align-items: flex-start; transform: rotate(0deg);\">" +
                "                                <section class=\"assistant\" style=\"box-sizing: border-box; width: 100px; margin-top: -48px;\">" +
                "                                    <img class=\"assistant\" src=\"http://172.16.25.100//webpic/W0202012/W020201207/W020201207562382268390.png\" data-ratio=\"0.46\" data-w=\"122\" data-width=\"100%\" data-op=\"change\" oldimg=\"true\" oldsrc=\"W020201207562382268390.png\" style=\"box-sizing: border-box; border: 0px none; vertical-align: middle; display: block; width: 100px;\"/>" +
                "                                </section>" +
                "                            </section>" +
                "                        </section>" +
                "                    </section>" +
                "                    <section class=\"\" style=\"box-sizing: border-box;\">" +
                "                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "                            <br style=\"box-sizing: border-box;\"/>" +
                "                        </p>" +
                "                    </section>" +
                "                    <section style=\"box-sizing: border-box;\">" +
                "                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "                            <br style=\"box-sizing: border-box;\"/>" +
                "                        </p>" +
                "                    </section>" +
                "                    <section class=\"_135editor\" style=\"box-sizing: border-box;\">" +
                "                        <section style=\"box-sizing: border-box; margin: 10px auto;\">" +
                "                            <section style=\"box-sizing: border-box; text-align: center;\">" +
                "                                <section class=\"assistant\" style=\"box-sizing: border-box; width: 65px; margin: 0px auto;\">" +
                "                                    <section class=\"assistant\" style=\"box-sizing: border-box; width: 65px;\">" +
                "                                        <img class=\"assistant\" src=\"http://172.16.25.100//webpic/W0202012/W020201207/W020201207562382434917.png\" data-ratio=\"0.5611510791366906\" data-w=\"139\" data-width=\"100%\" width=\"65\" height=\"36\" oldimg=\"true\" oldsrc=\"W020201207562382434917.png\" style=\"box-sizing: border-box; border: 0px none; vertical-align: middle; width: 65px; display: block; height: 30px;\"/>" +
                "                                    </section>" +
                "                                </section>" +
                "                                <section class=\"135brush\" data-brushtype=\"text\" hm_fix=\"302:223\" style=\"box-sizing: border-box; display: inline-block; padding: 6px 1em; letter-spacing: 1.5px; background-image: -webkit-linear-gradient(left, rgb(250, 242, 205), rgb(250, 242, 205)); font-weight: bold; color: rgb(155, 8, 0);\">" +
                "                                    国家宪法日宣传周" +
                "                                </section>" +
                "                            </section>" +
                "                        </section>" +
                "                    </section>" +
                "                    <section class=\"\" style=\"box-sizing: border-box;\">" +
                "                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "                            <br style=\"box-sizing: border-box;\"/>" +
                "                        </p>" +
                "                    </section>" +
                "                    <section style=\"box-sizing: border-box;\">" +
                "                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "                            <br style=\"box-sizing: border-box;\"/>" +
                "                        </p>" +
                "                    </section>" +
                "                    <section style=\"box-sizing: border-box;\">" +
                "                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "                            <br style=\"box-sizing: border-box;\"/>" +
                "                        </p>" +
                "                    </section>" +
                "                    <section class=\"_135editor\" data-id=\"100086\" style=\"box-sizing: border-box;\">" +
                "                        <section style=\"box-sizing: border-box; margin: 10px auto;\">" +
                "                            <section style=\"box-sizing: border-box; margin-top: -50px; transform: rotate(0deg);\">" +
                "                                <section class=\"\" style=\"box-sizing: border-box; background-image: -webkit-linear-gradient(left, rgb(250, 242, 205), rgb(250, 242, 205)); padding: 1.2em 1em;\">" +
                "                                    <section class=\"\" style=\"box-sizing: border-box; text-align: justify; line-height: 1.75em; letter-spacing: 1.5px; font-size: 14px; color: rgb(51, 51, 51);\">" +
                "                                        <section data-autoskip=\"1\" class=\"135brush\" style=\"box-sizing: border-box; line-height: 1.75em; color: rgb(155, 8, 0);\">" +
                "                                            <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 14px; box-sizing: border-box; padding: 0px;\">" +
                "                                                <span style=\"box-sizing: border-box; font-size: 16px;\">2018年12月4日是第五个国家宪法日，也是第一个“宪法宣传周”。</span>" +
                "                                            </p>" +
                "                                        </section>" +
                "                                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 14px; box-sizing: border-box; padding: 0px;\">" +
                "                                            <br style=\"box-sizing: border-box;\"/>" +
                "                                        </p>" +
                "                                        <section data-autoskip=\"1\" class=\"135brush\" style=\"box-sizing: border-box; line-height: 1.75em; color: rgb(155, 8, 0);\">" +
                "                                            <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 14px; box-sizing: border-box; padding: 0px;\">" +
                "                                                <span style=\"box-sizing: border-box; font-size: 16px;\">2019年12月1日，由中宣部、司法部、全国普法办举办的全国“宪法宣传周”活动主场活动在北京、上海两地同时启动，标志着今年的“宪法宣传周”正式拉开帷幕。从12月1日至7日，中宣部、司法部、全国普法办在全国部署开展以“弘扬宪法精神，推进国家治理体系和治理能力现代化”为主题的“宪法宣传周”活动，这也是我国第二次开展“宪法宣传周”活动。</span>" +
                "                                            </p>" +
                "                                        </section>" +
                "                                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 14px; box-sizing: border-box; padding: 0px;\">" +
                "                                            <br style=\"box-sizing: border-box;\"/>" +
                "                                        </p>" +
                "                                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 14px; box-sizing: border-box; padding: 0px;\">" +
                "                                            <br style=\"box-sizing: border-box;\"/>" +
                "                                        </p>" +
                "                                    </section>" +
                "                                </section>" +
                "                            </section>" +
                "                            <section class=\"assistant\" style=\"box-sizing: border-box; display: flex; justify-content: flex-end; align-items: flex-start; transform: rotate(0deg);\">" +
                "                                <section class=\"assistant\" style=\"box-sizing: border-box; width: 100px; margin-top: -48px;\">" +
                "                                    <img class=\"assistant\" src=\"http://172.16.25.100//webpic/W0202012/W020201207/W020201207562382580172.png\" data-width=\"100%\" data-op=\"change\" data-ratio=\"0.47101449275362317\" data-w=\"138\" oldimg=\"true\" oldsrc=\"W020201207562382580172.png\" style=\"box-sizing: border-box; border: 0px none; vertical-align: middle; display: block; width: 100px;\"/>" +
                "                                </section>" +
                "                            </section>" +
                "                        </section>" +
                "                    </section>" +
                "                    <section style=\"box-sizing: border-box;\">" +
                "                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "                            <br style=\"box-sizing: border-box;\"/>" +
                "                        </p>" +
                "                    </section>" +
                "                    <section class=\"\" style=\"box-sizing: border-box;\">" +
                "                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "                            <br style=\"box-sizing: border-box;\"/>" +
                "                        </p>" +
                "                    </section>" +
                "                    <section class=\"_135editor\" data-id=\"99144\" style=\"box-sizing: border-box;\">" +
                "                        <section style=\"box-sizing: border-box; margin: 10px auto;\">" +
                "                            <section style=\"box-sizing: border-box; text-align: center;\">" +
                "                                <section class=\"assistant\" style=\"box-sizing: border-box; width: 65px; margin: 0px auto;\">" +
                "                                    <section class=\"assistant\" style=\"box-sizing: border-box; width: 65px;\">" +
                "                                        <img class=\"assistant\" src=\"http://172.16.25.100//webpic/W0202012/W020201207/W020201207562382856937.png\" data-width=\"100%\" width=\"65\" height=\"36\" data-ratio=\"0.8484848484848485\" data-w=\"66\" oldimg=\"true\" oldsrc=\"W020201207562382856937.png\" style=\"box-sizing: border-box; border: 0px none; vertical-align: middle; width: 65px; display: block; height: 30px;\"/>" +
                "                                    </section>" +
                "                                </section>" +
                "                                <section class=\"135brush\" data-brushtype=\"text\" hm_fix=\"302:223\" style=\"box-sizing: border-box; display: inline-block; padding: 6px 1em; letter-spacing: 1.5px; background-image: -webkit-linear-gradient(left, rgb(250, 242, 205), rgb(250, 242, 205)); font-weight: bold; color: rgb(155, 8, 0);\">" +
                "                                    国家宪法日的设计意义" +
                "                                </section>" +
                "                            </section>" +
                "                        </section>" +
                "                    </section>" +
                "                    <section style=\"box-sizing: border-box;\">" +
                "                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "                            <br style=\"box-sizing: border-box;\"/>" +
                "                        </p>" +
                "                    </section>" +
                "                    <section class=\"\" style=\"box-sizing: border-box;\">" +
                "                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "                            <br style=\"box-sizing: border-box;\"/>" +
                "                        </p>" +
                "                    </section>" +
                "                    <section style=\"box-sizing: border-box;\">" +
                "                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "                            <br style=\"box-sizing: border-box;\"/>" +
                "                        </p>" +
                "                    </section>" +
                "                    <section class=\"_135editor\" data-id=\"100086\" style=\"box-sizing: border-box;\">" +
                "                        <section style=\"box-sizing: border-box; margin: 10px auto;\">" +
                "                            <section style=\"box-sizing: border-box; margin-top: -50px; transform: rotate(0deg);\">" +
                "                                <section class=\"\" style=\"box-sizing: border-box; background-image: -webkit-linear-gradient(left, rgb(250, 242, 205), rgb(250, 242, 205)); padding: 1.2em 1em;\">" +
                "                                    <section class=\"\" style=\"box-sizing: border-box; text-align: justify; line-height: 1.75em; letter-spacing: 1.5px; font-size: 14px; color: rgb(255, 255, 255);\">" +
                "                                        <section data-autoskip=\"1\" class=\"135brush\" style=\"box-sizing: border-box; line-height: 1.75em; color: rgb(155, 8, 0);\">" +
                "                                            <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 14px; box-sizing: border-box; padding: 0px;\">" +
                "                                                <span style=\"box-sizing: border-box; font-size: 16px; caret-color: red;\">设立国家宪法日，不仅是增加一个纪念日，更要使这一天成为全民的宪法“教育日、普及日、深化日”，形成举国上下尊重宪法、宪法至上、用宪法维护人民权益的社会氛围。</span><br style=\"box-sizing: border-box;\"/>" +
                "                                            </p>" +
                "                                        </section>" +
                "                                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 14px; box-sizing: border-box; padding: 0px;\">" +
                "                                            <br style=\"box-sizing: border-box;\"/>" +
                "                                        </p>" +
                "                                        <section data-autoskip=\"1\" class=\"135brush\" style=\"box-sizing: border-box; line-height: 1.75em; color: rgb(155, 8, 0);\">" +
                "                                            <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 14px; box-sizing: border-box; padding: 0px;\">" +
                "                                                <span style=\"box-sizing: border-box; font-size: 16px;\">设立国家宪法日，也是让宪法思维内化于所有国家公职人员心中。权力属于人民，权力服从宪法。公职人员只有为人民服务的义务，没有凌驾于人民之上的特权。一切违反宪法和法律的行为都必须予以追究和纠正</span>" +
                "                                            </p>" +
                "                                        </section>" +
                "                                        <section style=\"box-sizing: border-box; margin: 10px auto; text-align: center;\">" +
                "                                            <section style=\"box-sizing: border-box; display: flex; align-items: flex-start; justify-content: flex-end;\">" +
                "                                                <section style=\"box-sizing: border-box; flex: 1 1 0%;\">" +
                "                                                    <section data-width=\"100%\" style=\"box-sizing: border-box; width: 520.969px; margin-top: 6px; height: 0px; overflow: hidden;\"></section>" +
                "                                                    <section style=\"box-sizing: border-box; display: flex; justify-content: flex-start; align-items: center; margin-top: 10px; border-bottom: 1px solid rgb(155, 8, 0); padding-bottom: 6px;\">" +
                "                                                        <section class=\"assistant\" style=\"box-sizing: border-box; width: 40px; height: 10px; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial; overflow: hidden;\"></section>" +
                "                                                    </section>" +
                "                                                </section>" +
                "                                                <section style=\"box-sizing: border-box; margin-left: 10px; flex: 1 1 0%;\">" +
                "                                                    <section class=\"assistant\" data-width=\"100%\" style=\"box-sizing: border-box; width: 520.969px; height: 0px; overflow: hidden;\"></section>" +
                "                                                    <section class=\"assistant\" data-width=\"100%\" style=\"box-sizing: border-box; width: 520.969px; margin-top: 15px; height: 0px; overflow: hidden;\"></section>" +
                "                                                </section>" +
                "                                            </section>" +
                "                                        </section>" +
                "                                        <p hm_fix=\"292:281\" style=\"margin-top: 0px; margin-bottom: 0px; font-size: 14px; box-sizing: border-box; padding: 0px;\">" +
                "                                            <br style=\"box-sizing: border-box;\"/>" +
                "                                        </p>" +
                "                                    </section>" +
                "                                </section>" +
                "                            </section>" +
                "                            <section class=\"assistant\" style=\"box-sizing: border-box; display: flex; justify-content: flex-end; align-items: flex-start; transform: rotate(0deg);\">" +
                "                                <section class=\"assistant\" style=\"box-sizing: border-box; width: 100px; margin-top: -48px;\">" +
                "                                    <img class=\"assistant\" src=\"http://172.16.25.100//webpic/W0202012/W020201207/W020201207562383023023.png\" data-width=\"100%\" data-op=\"change\" data-ratio=\"0.47101449275362317\" data-w=\"138\" oldimg=\"true\" oldsrc=\"W020201207562383023023.png\" style=\"box-sizing: border-box; border: 0px none; vertical-align: middle; display: block; width: 100px;\"/>" +
                "                                </section>" +
                "                            </section>" +
                "                        </section>" +
                "                    </section>" +
                "                    <section class=\"\" style=\"box-sizing: border-box;\">" +
                "                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "                            <br style=\"box-sizing: border-box;\"/>" +
                "                        </p>" +
                "                    </section>" +
                "                    <section class=\"\" style=\"box-sizing: border-box;\">" +
                "                        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "                            <br style=\"box-sizing: border-box;\"/>" +
                "                        </p>" +
                "                    </section>" +
                "                    <section class=\"_135editor\" data-id=\"96755\" style=\"box-sizing: border-box;\">" +
                "                        <section class=\"\" style=\"box-sizing: border-box; display: flex; justify-content: center; align-items: center; flex-direction: column;\">" +
                "                            <section class=\"\" style=\"box-sizing: border-box; width: 80px;\">" +
                "                                <img src=\"http://172.16.25.100//webpic/W0202012/W020201207/W020201207562383205138.png\" data-ratio=\"0.3434343434343434\" data-width=\"100%\" data-w=\"99\" oldimg=\"true\" oldsrc=\"W020201207562383205138.png\" style=\"box-sizing: border-box; border: 0px none; vertical-align: middle; width: 80px; display: block;\"/>" +
                "                            </section><span style=\"box-sizing: border-box; color: rgb(255, 234, 139); text-align: center;\">END</span>" +
                "                        </section>" +
                "                    </section>" +
                "                    <section class=\"_135editor\" style=\"box-sizing: border-box;\">" +
                "                        <section style=\"box-sizing: border-box; margin: 10px auto;\">" +
                "                            <section class=\"assistant\" style=\"box-sizing: border-box; display: flex; justify-content: flex-end; align-items: flex-start; height: 0px; overflow: hidden; transform: rotate(0deg);\"></section>" +
                "                        </section>" +
                "                    </section>" +
                "                </section>" +
                "            </section>" +
                "        </section>" +
                "    </section>" +
                "</section>" +
                "<section class=\"_135editor\" data-tools=\"135编辑器\" data-id=\"us-3803722\" style=\"box-sizing: border-box; color: rgb(42, 42, 42); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Hiragino Sans GB&quot;, &quot;Hiragino Sans GB W3&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;WenQuanYi Micro Hei&quot;, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">" +
                "    <section class=\"_135editor\" data-role=\"paragraph\" style=\"box-sizing: border-box;\">" +
                "        <section class=\"_135editor\" data-tools=\"135编辑器\" data-id=\"us-3715365\" style=\"box-sizing: border-box; height: 0px; overflow: hidden;\"></section>" +
                "        <section class=\"_135editor\" data-tools=\"135编辑器\" data-id=\"us-3880754\" style=\"box-sizing: border-box; height: 0px; overflow: hidden;\"></section>" +
                "        <p hm_fix=\"226:191\" style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "            编辑：王&nbsp; &nbsp;蓓" +
                "        </p>" +
                "        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "            责编：陆&nbsp; &nbsp;勤" +
                "        </p>" +
                "        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px;\">" +
                "            监审：段永平" +
                "        </p>" +
                "    </section>" +
                "</section>" +
                "<p style=\"margin-top: 0px; margin-bottom: 0px; box-sizing: border-box; padding: 0px; color: rgb(42, 42, 42); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Hiragino Sans GB&quot;, &quot;Hiragino Sans GB W3&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;WenQuanYi Micro Hei&quot;, sans-serif; white-space: normal; background-color: rgb(255, 255, 255); text-align: center;\">" +
                "    <br style=\"box-sizing: border-box;\"/>" +
                "</p>" +
                "<section class=\"_135editor\" data-tools=\"135编辑器\" data-id=\"us-3891646\" style=\"box-sizing: border-box; color: rgb(42, 42, 42); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Hiragino Sans GB&quot;, &quot;Hiragino Sans GB W3&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;WenQuanYi Micro Hei&quot;, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">" +
                "    <section class=\"_135editor\" style=\"box-sizing: border-box;\">" +
                "        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px; text-align: center;\">" +
                "            <img src=\"http://172.16.25.100//webpic/W0202012/W020201207/W020201207562383367800.jpeg\" alt=\"\" data-ratio=\"0.62265625\" data-w=\"1280\" hm_fix=\"277:212\" oldimg=\"true\" oldsrc=\"W020201207562383367800.jpeg\" style=\"box-sizing: border-box; border: 0px none; vertical-align: middle;\"/>" +
                "        </p>" +
                "    </section>" +
                "</section>" +
                "<section class=\"_135editor\" data-tools=\"135编辑器\" data-id=\"us-3803721\" style=\"box-sizing: border-box; color: rgb(42, 42, 42); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Hiragino Sans GB&quot;, &quot;Hiragino Sans GB W3&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;WenQuanYi Micro Hei&quot;, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">" +
                "    <section class=\"_135editor\" style=\"box-sizing: border-box;\">" +
                "        <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 16px; box-sizing: border-box; padding: 0px; text-align: center;\">" +
                "            <img src=\"http://172.16.25.100//webpic/W0202012/W020201207/W020201207562383661577.jpeg\" alt=\"\" data-ratio=\"0.375\" data-w=\"640\" hm_fix=\"242:287\" oldimg=\"true\" oldsrc=\"W020201207562383661577.jpeg\" style=\"box-sizing: border-box; border: 0px none; vertical-align: middle;\"/>" +
                "        </p>" +
                "    </section>" +
                "</section>" +
                "<p>" +
                "    <br style=\"white-space: normal;\"/>" +
                "</p>" +
                "<p>" +
                "    <br/>" +
                "</p>";
        System.out.println(validateLegalOfHtmlContent(oStr0));
    }
}
