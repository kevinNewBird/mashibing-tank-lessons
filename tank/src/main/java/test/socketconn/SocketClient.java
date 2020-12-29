package test.socketconn;

import java.io.IOException;
import java.net.Socket;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/11/19 11:06
 * @version: 1.0
 ***********************/
public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);
        long start = System.currentTimeMillis();
        System.out.println(socket.getInputStream().read());
        System.out.println(System.currentTimeMillis() - start);

        socket.close();
    }




}
