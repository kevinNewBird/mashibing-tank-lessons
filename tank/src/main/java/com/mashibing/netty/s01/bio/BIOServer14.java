package com.mashibing.netty.s01.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * description  BIOServer14 <BR>
 * <p>
 * author: zhao.song
 * date: created in 1:53  2021/6/30
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class BIOServer14 {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8090); //监听端口8090，本机作为服务端
        System.out.println("step1 new ServerSocket(8090)");
        while(true) {
            Socket client = serverSocket.accept(); //一旦客户端连接上来，新开辟一个线程处理客户端输入
            System.out.println("client port :" + client.getPort());

            new Thread(new Runnable() {
                Socket ss;

                public Runnable setSS(Socket s) {
                    ss = s;
                    return this;
                }

                public void run() {
                    try {
                        InputStream is = ss.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                        while(true) {
                            System.out.println(reader.readLine());
                        }
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }.setSS(client)).start();
        }
    }
}
