package test.poi;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.model.PAPBinTable;
import org.apache.poi.hwpf.model.PAPX;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description  TODO <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/15 10:37
 **/
public class DocImportTest {

    public static void main(String[] args) throws Exception {

//        testGetWordContentByDoc("C:\\Users\\Kevin\\Desktop\\图集稿测试.doc");
        getWordContentByDoc2("C:\\Users\\Kevin\\Desktop\\图集稿测试.doc");
        System.out.println(getWordContentByDoc("C:\\Users\\Kevin\\Desktop\\图集稿测试.doc")._2);
//        System.out.println(wordContentByDoc);
    }

    public static void testGetWordContentByDoc(String _path) throws Exception {
        // 目前是图文分开处理的，图文混排处理没有找到好的处理方式
        String sContent = "";
        String sHTMLContent = "";
        // 获取文档中的所有文字
        // word2003的处理
        FileInputStream in = new FileInputStream(_path);
        WordExtractor extractor = null;
        try {
            extractor = new WordExtractor(in);
            sContent = extractor.getText();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("不能上传直接修改后缀的文件");
        }
        String[] paragraphText = extractor.getParagraphText();
        for (int i = 0; i < paragraphText.length; i++) {
            String paragraph = paragraphText[i];
            if (StringUtils.isBlank(paragraph)) {
                continue;
            }
            System.out.println(paragraph);
        }

    }

    static void getWordContentByDoc2(String _path) throws Exception {
        FileInputStream in = new FileInputStream(new File(_path));
        HWPFDocument doc = new HWPFDocument(in);
        int length = doc.characterLength();
        PicturesTable pTable = doc.getPicturesTable();
        Range allRange = doc.getRange();
        StringBuilder sHTMLContent = new StringBuilder();
        // int TitleLength=doc.getSummaryInformation().getTitle().length();
        for (int i = 0; i < allRange.numCharacterRuns(); i++) {
            CharacterRun cr = allRange.getCharacterRun(i);

            if (pTable.hasPicture(cr)) {
                Picture pic = pTable.extractPicture(cr, true);
                String afileName = pic.suggestFullFileName();
                sHTMLContent.append(afileName);
            }else{
                sHTMLContent.append(transDisplay(cr.text(), true));
            }
        }
        System.out.println(sHTMLContent.toString());

    }


    public static Tuple2<String, String> getWordContentByDoc(String _path) throws Exception {
        // 目前是图文分开处理的，图文混排处理没有找到好的处理方式
        String sContent = "";
        String sHTMLContent = "";
        // 获取文档中的所有文字
        // word2003的处理
        FileInputStream in = new FileInputStream(_path);
        WordExtractor extractor = null;
        try {
            extractor = new WordExtractor(in);
            sContent = extractor.getText();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("不能上传直接修改后缀的文件");
        }

        sHTMLContent = transDisplay(sContent, true);
        extractor.close();

        // 获取图片
        HWPFDocument doc = new HWPFDocument(new FileInputStream(_path));
        Range range = doc.getRange();

        // 得到word的数据流
        byte[] dataStream = doc.getDataStream();
        int numChar = range.numCharacterRuns();
        PicturesTable pTable = new PicturesTable(doc, dataStream, dataStream);

        for (int j = 0; j < numChar; ++j) {
            CharacterRun cRun = range.getCharacterRun(j);
            // 看看有没有图片
            boolean has = pTable.hasPicture(cRun);
            if (has) {
                Picture zhou = pTable.extractPicture(cRun, true);
                String picType = zhou.suggestFileExtension();
                String sUploadFileName = zhou.getDescription() + "." + picType;


                // 获取可用文件名,默认都安装jpg来存
                String sSaveFile = "C:\\Users\\Kevin\\Desktop\\test.jpg";
                // 将图片写入upload临时目录
                zhou.writeImageContent(new FileOutputStream(sSaveFile));

                // 往正文写入图片地址
                String sFileName = "test.jpg";
                File picture = new File(sSaveFile);
                if (!picture.exists()) {
                    continue;
                }
                BufferedImage sourceImg = ImageIO
                        .read(new FileInputStream(picture));
                String picWidth = "";
                if (null == sourceImg) {
                    picWidth = "550px";
                } else {
                    picWidth = sourceImg.getWidth() > 550 ? "550px"
                            : sourceImg.getWidth() + "px";
                }
                // 定义图片宽度
                // String picWidth = sourceImg.getWidth() > 550 ? "550px"
                // : sourceImg.getWidth() + "px";

//                String s = "<p><img src=\"/wcm/app/system/read_image.jsp?FileName="
//                        + sFileName + "\" width=\"" + picWidth
//                        + "\" uploadpic=\"" + sFileName + "\"/></p>";
                String s = "<p><img src=\"" + sFileName + "\" width=\"" + picWidth
                        + "\" uploadpic=\"" + sFileName + "\"/></p>";
                sHTMLContent += s;
            }
        }
        return Tuple.of(sContent, sHTMLContent);
    }

    public static String transDisplay(String var0, boolean var1) {
        if (var0 == null) {
            return "";
        } else {
            char[] var2 = var0.toCharArray();
            int var3 = var2.length;
            StringBuffer var4 = new StringBuffer(var3 * 2);

            for (int var5 = 0; var5 < var3; ++var5) {
                char var6 = var2[var5];
                switch (var6) {
                    case '\t':
                        var4.append(var1 ? "&nbsp;&nbsp;&nbsp;&nbsp;" : "    ");
                        break;
                    case '\n':
                        if (var1) {
                            var4.append("<br/>");
                        } else {
                            var4.append(var6);
                        }
                        break;
                    case ' ':
                        var4.append(var1 ? "&nbsp;" : " ");
                        break;
                    case '"':
                        var4.append("&quot;");
                        break;
                    case '&':
                        var4.append("&amp;");
                        break;
                    case '<':
                        var4.append("&lt;");
                        break;
                    case '>':
                        var4.append("&gt;");
                        break;
                    default:
                        var4.append(var6);
                }
            }

            if (var1) {
                return var4.toString();
            } else {
                return replaceParasStartEndSpaces(var4.toString());
            }
        }
    }

    public static String replaceParasStartEndSpaces(String var0) {
        Pattern var1 = Pattern.compile("\n\r|\n|\r");
        Matcher var2 = var1.matcher(var0);
        byte var3 = 30;
        StringBuffer var4 = new StringBuffer(var3 * 100 + var0.length());

        int var5;
        for (var5 = 0; var2.find(); var5 = var2.end()) {
            int var6 = var2.start();
            String var7 = var0.substring(var5, var6);
            var7 = replaceStartEndSpaces(var7);
            var4.append(var7);
            var4.append("<br/>");
        }

        String var8 = var0.substring(var5);
        var8 = replaceStartEndSpaces(var8);
        var4.append(var8);
        return var4.toString();
    }

    public static String replaceStartEndSpaces(String var0) {
        Pattern var1 = Pattern.compile("(?m)^(\\s*)(.*?)(\\s*)$");
        Matcher var2 = var1.matcher(var0);
        byte var3 = 30;
        StringBuffer var4 = new StringBuffer(var3 * 100 + var0.length());

        while (var2.find()) {
            String var5 = var2.group(1);

            char var7;
            for (int var6 = 0; var6 < var5.length(); ++var6) {
                var7 = var5.charAt(var6);
                if (var7 == ' ') {
                    var4.append("&nbsp;");
                } else {
                    var4.append(var7);
                }
            }

            var4.append(var2.group(2));
            String var9 = var2.group(3);
            boolean var10 = false;

            for (int var8 = 0; var8 < var9.length(); ++var8) {
                var7 = var9.charAt(var8);
                if (var7 == ' ') {
                    var4.append("&nbsp;");
                } else {
                    var4.append(var7);
                }
            }
        }

        return var4.toString();
    }
}
