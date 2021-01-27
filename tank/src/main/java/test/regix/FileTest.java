package test.regix;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/***********************
 * Description: 文件测试类 <BR>
 * author: zhao.song
 * date: 2021/1/7 18:06
 * version: 1.0
 ***********************/
public class FileTest {

    public static void main(String[] args) throws IOException {

        //txt文档的文件头不固定
        File file = new File("C:\\Users\\Kevin\\Desktop\\Java学习链接地址.txt");
        FileInputStream fis = new FileInputStream(file);
        byte[] fileHeadCache = new byte[8];
        fis.read(fileHeadCache, 0, fileHeadCache.length);

        String fileCode = bytesToHexString(fileHeadCache);
        System.out.println(fileCode);


    }

    private static byte[] readInputStreamAt(FileInputStream fis, long skiplength, int length) throws IOException {
        byte[] buf = new byte[length];
        fis.skip(skiplength);  //
        int read = fis.read(buf, 0, length);
        return buf;
    }


    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v).toUpperCase();
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
