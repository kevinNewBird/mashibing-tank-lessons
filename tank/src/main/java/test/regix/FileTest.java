package test.regix;

import java.io.File;

/***********************
 * Description: 文件测试类 <BR>
 * author: zhao.song
 * date: 2021/1/7 18:06
 * version: 1.0
 ***********************/
public class FileTest {

    public static void main(String[] args) {
        File file = new File("E:\\wallpapers\\tunnel.png");
        if (file.exists() && file.isFile()) {
            System.out.println(file.length() / ((double) 1024 * 1024));
        }
    }
}
