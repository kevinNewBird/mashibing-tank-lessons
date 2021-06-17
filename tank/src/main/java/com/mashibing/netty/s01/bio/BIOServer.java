package com.mashibing.netty.s01.bio;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * description  s01 阶段 <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/7 14:43
 **/
public class BIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8099);
//        server.bind(new InetSocketAddress("127.0.0.1", 8099));

        while (true) {
            Socket client = server.accept();

            new Thread(() -> handle(client)).start();
        }
    }

    static void handle(Socket client) {
        try (InputStream is = client.getInputStream();
             OutputStream os = client.getOutputStream();) {


//            byte[] buf = new byte[1024];
//            int len = is.read(buf);
//            System.out.println(new String(buf, 0, len));
            byte[] buf = new byte[1024];
            int len = 0;
            StringBuilder sDatagram = new StringBuilder();
            while ((len = is.read(buf)) != -1) {
                sDatagram.append(new String(buf, 0, len));
            }
//            client.shutdownOutput();

            os.write("hello,client".getBytes());
            os.flush();
            //输出流必须要关闭
//            os.close();
            client.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
