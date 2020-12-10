package com.test.socketconn;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/11/19 11:05
 * @version: 1.0
 ***********************/
public class SocketServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        TimeUnit.SECONDS.sleep(10);
        socket.getOutputStream().write(-1);

    }
}
