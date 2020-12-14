package com.test.regix;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***********************
 * Description: TODO <BR>
 * author: zhao.song
 * date: 2020/12/14 16:17
 * version: 1.0
 ***********************/
public class WechatHelperTest {

    //微信所有链接正则匹配表达式
//    public final static String ALL_HTTPS_REGEX = "(http|https)://.*";
    public final static String ALL_HTTPS_REGEX = "\"((http|https):\\/\\/.+?)\"";


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


    public static void main(String[] args) {
        String oStr = "<p>" +
                "    <br/>" +
                "</p>" +
                "<p>" +
                "    <img src=\"http://172.16.25.71//webpic/W0202010/W020201030/W020201030543534061358.gif\" style=\"max-width: 470px;\" oldsrc=\"W020201030543534061358.gif\"/>" +
                "</p>" +
                "<p>" +
                "    <iframe data-vidtype=\"1\" style=\"position: relative;z-index:1;height:480px;width:720px;\" src=\"http://v.qq.com/iframe/preview.html?vid=j31532v8ryg\" frameborder=\"0\" align=\"center\" allowfullscreen=\"1\"></iframe><iframe data-vidtype=\"1\" style=\"position: relative;z-index:1;height:480px;width:720px;\" src=\"http://v.qq.com/iframe/preview.html?vid=j31532v8ryg\" frameborder=\"0\" align=\"center\" allowfullscreen=\"1\"></iframe>" +
                "</p>" +
                "<p>" +
                "    <br/>" +
                "</p>" +
                "<p>" +
                "    　　\"http://172.16.25.71//webpic/W0202010/W020201030/W020201030543534061358.gif\" 近日，人力资源社会保障部发布登记失业人员就业创业扶持政策清单，旨在保障失业人员失业期间的基本生活，促进其再就业。其中，失业保险作为我国社会保障制度的重要组成部分，发挥着“保生活”的重要作用，但许多人却对这一惠民政策的认识和理解存在误区和偏差，对此，小编为您解读真相，澄清谬误。" +
                "</p>" +
                "<p>" +
                "    <br/>" +
                "</p>" +
                "<p>" +
                "    <img src=\"http://172.16.25.71//webpic/W0202010/W020201030/W020201030543594990515.gif\" style=\"max-width:600px;\" oldsrc=\"W020201030543594990515.gif\"/>" +
                "</p>" +
                "<p>" +
                "    <br/>" +
                "</p>" +
                "<p>" +
                "    　　01" +
                "</p>" +
                "<p>" +
                "    　　失业保险金or失业补助金傻傻分不清？" +
                "</p>" +
                "<p>" +
                "    <br/>" +
                "</p>" +
                "<p>" +
                "    <img src=\"http://172.16.25.71//webpic/W0202010/W020201030/W020201030543600777818.png\" style=\"max-width:600px;\" oldsrc=\"W020201030543600777818.png\"/>" +
                "</p>" +
                "<p>" +
                "    <br/>" +
                "</p>" +
                "<p>" +
                "    　　失业保险金属于常规的失业保险待遇，而失业补助金是疫情防控期间，国务院新出台的一项临时性、阶段性举措，申领时限截至2020年12月31日。具体区别如下：" +
                "</p>" +
                "<p>" +
                "    　　申领条件" +
                "</p>" +
                "<p>" +
                "    　　失业保险金：缴纳失业保险费满一年并且非因本人意愿中断就业的参保人员。" +
                "</p>" +
                "<p>" +
                "    　　失业补助金：领取失业保险金期满仍未就业或不符合领取失业保险金条件的参保失业人员。" +
                "</p>" +
                "<p>" +
                "    　　发放标准" +
                "</p>" +
                "<p>" +
                "    　　失业保险金：按照低于当地最低工资标准、高于城市居民最低生活保障标准的水平发放，最长不超过24个月。领取期间可以同时享受其他失业保险待遇。" +
                "</p>" +
                "<p>" +
                "    　　失业补助金：标准不高于当地失业保险金的80%，领取期限6个月。领取期间不享受失业保险金、代缴基本医疗保险费、丧葬补助金和抚恤金。" +
                "</p>" +
                "<p>" +
                "    　　02" +
                "</p>" +
                "<p>" +
                "    　　失业保险金不能“随意”领，小心养老金“被减少”" +
                "</p>" +
                "<p>" +
                "    <br/>" +
                "</p>" +
                "<p>" +
                "    <img src=\"http://172.16.25.71//webpic/W0202010/W020201030/W020201030543606937137.png\" style=\"max-width:600px;\" oldsrc=\"W020201030543606937137.png\"/>" +
                "</p>" +
                "<p>" +
                "    <br/>" +
                "</p>" +
                "<p>" +
                "    　　退休职工养老金的计算，仅与缴费基数、缴费比例、缴费年限和退休时上年度在岗职工月平均工资等因素有关，与是否领取失业保险金或社保补贴无关。" +
                "</p>" +
                "<p>" +
                "    　　需要注意的是，失业人员在领取失业保险金期间，参加职工基本医疗保险，享受基本医疗保险待遇，医疗保险费由失业保险基金支付，个人不缴纳基本医疗保险费。此外，失业人员如果不想社保断缴，可以以灵活就业或自由从业人员身份参保，仅需再单独缴纳个人养老保险即可。" +
                "</p>" +
                "<p>" +
                "    　　03" +
                "</p>" +
                "<p>" +
                "    　　主动辞职可以领取失业保险金？" +
                "</p>" +
                "<p>" +
                "    <br/>" +
                "</p>" +
                "<p>" +
                "    <img src=\"http://172.16.25.71//webpic/W0202010/W020201030/W020201030543615249628.png\" style=\"max-width:600px;\" oldsrc=\"W020201030543615249628.png\"/>" +
                "</p>" +
                "<p>" +
                "    <br/>" +
                "</p>" +
                "<p>" +
                "    　　一般来说，劳动者出于本人主观意愿向用人单位提出辞职的，不符合领取失业保险金的条件。申领失业保险金必须满足“非因本人意愿中断就业”的条件。根据《实施&lt;中华人民共和国社会保险法&gt;若干规定》第十三条，非因本人意愿中断就业的是指下列人员：<!--中华人民共和国社会保险法-->" +
                "</p>" +
                "<p>" +
                "    　　一是劳动合同终止的。" +
                "</p>" +
                "<p>" +
                "    　　二是用人单位提出并与劳动者协商一致解除劳动合同的。" +
                "</p>" +
                "<p>" +
                "    　　三是由用人单位提出解除聘用合同或者被用人单位辞退、除名、开除的。" +
                "</p>" +
                "<p>" +
                "    　　四是被用人单位解除劳动合同的。" +
                "</p>" +
                "<p>" +
                "    　　五是劳动者本人依照《劳动合同法》第三十八条规定解除劳动合同的。" +
                "</p>" +
                "<p>" +
                "    　　六是法律、法规、规章规定的其他情形。" +
                "</p>" +
                "<p>" +
                "    　　04" +
                "</p>" +
                "<p>" +
                "    　　失业保险金最多只能领24个月？一生只能领一次？" +
                "</p>" +
                "<p>" +
                "    <br/>" +
                "</p>" +
                "<p>" +
                "    <img src=\"http://172.16.25.71//webpic/W0202010/W020201030/W020201030543620502489.png\" style=\"max-width:600px;\" oldsrc=\"W020201030543620502489.png\"/>" +
                "</p>" +
                "<p>" +
                "    <br/>" +
                "</p>" +
                "<p>" +
                "    　　失业保险金单次领取期限最长24个月，而非终生只能领取24个月。劳动者领金期满后重新找到工作并参保，再次失业后符合申领条件的，可以继续申请失业保险金。关于领取期限，《社会保险法》第四十六条和《失业保险条例》第十七条规定：" +
                "</p>" +
                "<p>" +
                "    　　失业人员失业前所在单位和本人按照规定累计缴费时间满１年不足５年的，领取失业保险金的期限最长为12个月；" +
                "</p>" +
                "<p>" +
                "    　　累计缴费时间满５年不足10年的，领取失业保险金的期限最长为18个月；" +
                "</p>" +
                "<p>" +
                "    　　累计缴费时间10年以上的，领取失业保险金的期限最长为24个月。" +
                "</p>" +
                "<p>" +
                "    　　此外，失业人员在领取失业保险金期间如有重新就业等相关情况，应主动告知社保经办机构，违法领取失业保险金将承担相应法律责任。" +
                "</p>" +
                "<p>" +
                "    　　05" +
                "</p>" +
                "<p>" +
                "    　　再次就业后，原来剩余的失业保险待遇享受月数会作废？" +
                "</p>" +
                "<p>" +
                "    <br/>" +
                "</p>" +
                "<p>" +
                "    <img src=\"http://172.16.25.71//webpic/W0202010/W020201030/W020201030543626081877.png\" style=\"max-width:600px;\" oldsrc=\"W020201030543626081877.png\"/>" +
                "</p>" +
                "<p>" +
                "    <br/>" +
                "</p>" +
                "<p>" +
                "    　　重新就业后再次失业的，失业保险缴费时间重新计算。领取失业保险金的期限与前次应当领取而尚未领取的失业保险金的期限合并计算，最长不超过24个月。" +
                "</p>" +
                "<p>" +
                "    　　也就是说，根据之前累计缴费年限确定领取失业保险金期限后，之前的累计缴费时间都将清零，新的缴费年限按照重新就业后的缴费时间另行计算。前次失业中应领取而尚未领取的失业保险金期限予以保留，可以与按照再次失业时的累计缴费年限计算出来的领取失业保险金的期限合并计算，但本次累计领取期限不超过24个月。" +
                "</p>" +
                "<p>" +
                "    　　小贴士：" +
                "</p>" +
                "<p>" +
                "    　　为切实做好疫情防控期间失业人员基本生活保障，确保失业保险待遇按时足额发放，全国所有地市目前均已实现失业保险金网上申领，失业保险金网上申领全国统一入口也已上线运行。" +
                "</p>" +
                "<p>" +
                "    　　失业保险金网上申领渠道" +
                "</p>" +
                "<p>" +
                "    　　1.中国政府网——国家政务服务平台——就业服务专栏——就业保障——失业保险金申领查询" +
                "</p>" +
                "<p>" +
                "    　　2.国务院客户端——疫情防控和复工复产服务专区——复工复产服务——失业金申领" +
                "</p>" +
                "<p>" +
                "    　　3.人力资源社会保障部官网——专题专栏——国家社会保险公共服务平台——失业保险金网上申领/失业补助金网上申领" +
                "</p>" +
                "<p>" +
                "    　　4.国家社会保险公共服务平台——失业保险金网上申领/失业补助金网上申领" +
                "</p>" +
                "<p>" +
                "    　　5.“掌上12333”APP——服务——社会保障——失业金网上申领" +
                "</p>" +
                "<p>" +
                "    　　6.直接扫码↓" +
                "</p>" +
                "<p>" +
                "    <br/>" +
                "</p>" +
                "<p>" +
                "    <img src=\"http://172.16.25.71//webpic/W0202010/W020201030/W020201030543633264019.png\" style=\"max-width:600px;\" oldsrc=\"W020201030543633264019.png\"/>" +
                "</p>" +
                "<p>" +
                "    <br/>" +
                "</p>" +
                "<p>" +
                "    　　7.电子社保卡申领" +
                "</p>" +
                "<p>" +
                "    <br/>" +
                "</p>" +
                "<p>" +
                "    <img src=\"http://172.16.25.71//webpic/W0202010/W020201030/W020201030543641486668.png\" style=\"max-width: 470px;\" oldsrc=\"W020201030543641486668.png\"/>" +
                "</p>" +
                "<p>" +
                "    <br/>" +
                "</p>" +
                "<p>" +
                "    　　（来源：中国互联网联合辟谣平台）" +
                "</p>" +
                "<p>" +
                "    　　编辑：蒋明镏 李娟" +
                "</p>" +
                "<p>" +
                "    　　统筹：晏海艳" +
                "</p>" +
                "<p>" +
                "    　　编审：田旻佳" +
                "</p>" +
                "<p>" +
                "    　　更多新闻" +
                "</p>";
        System.out.println(validateLegalOfHtmlContent2(oStr));
    }
}
