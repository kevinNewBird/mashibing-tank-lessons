package com.mashibing.netty.s01.bio;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * description  s01 阶段学习 <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/7 14:34
 **/
public class BIOClient {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 8099);
        try (OutputStream os = client.getOutputStream();
             InputStream is = client.getInputStream();) {
            os.write("hello, i'm client.".getBytes());
            os.flush();

            byte[] buf = new byte[1024];
            int len = is.read(buf);
            System.out.println(new String(buf, 0, len));
//            StringBuilder isDatagram = new StringBuilder();
//            while ((len = is.read(buf)) != -1) {
//                isDatagram.append(new String(buf, 0, len));
//            }
//            System.out.println(isDatagram.toString());
//            client.shutdownInput();
//            client.shutdownOutput();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            client.close();
        }

    }
}
