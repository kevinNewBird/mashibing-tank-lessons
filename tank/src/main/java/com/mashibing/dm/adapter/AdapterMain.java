package com.mashibing.dm.adapter;



import java.io.*;
import java.nio.charset.Charset;
import java.util.Optional;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/31 0:55
 * @version: 1.0
 ***********************/
public class AdapterMain {

    public static void main(String[] args) throws IOException {
        try (FileInputStream fis = new FileInputStream("C:\\Users\\Kevin\\Desktop\\Java学习链接地址.txt");) {
            byte[] cache = new byte[1024];
            int len = 0;
            while ((len = fis.read(cache)) != -1) {
                System.out.println(new String(cache, 0, len,"gb2312"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileReader fr = new FileReader("C:\\Users\\Kevin\\Desktop\\Java学习链接地址.txt");){
            char[] cache = new char[1024];
            int len = 0;
            while ((len = fr.read(cache)) != -1) {
                System.out.println(new String(cache, 0, len));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Optional.of("--------------------------------").ifPresent(System.out::println);

        try(FileInputStream fis = new FileInputStream("C:\\Users\\Kevin\\Desktop\\Java学习链接地址.txt");
            //使用适配器
            InputStreamReader isr = new InputStreamReader(fis,"gb2312");
            BufferedReader br = new BufferedReader(isr)) {

            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
