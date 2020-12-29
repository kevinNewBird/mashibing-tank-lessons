package test.tomcat;

import java.io.IOException;
import java.net.Socket;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/11/19 11:39
 * @version: 1.0
 ***********************/
public class TomcatSocketTest {

    public static void main(String[] args) throws IOException {
        doValidateTomcatConnectTimeout();
    }


    /**
     * Description: 验证tomcat的connectTimeout参数 <BR>
     *
     * @author zhao.song    2020/11/19 11:38
     * @param :
     * @return
     */
    private static void doValidateTomcatConnectTimeout() throws IOException {
        //连接tomcat(该链接需连接新疆vpn)
        Socket socket = new Socket("172.16.25.25", 8080);

        long start = System.currentTimeMillis();
        //不发送http报文,阻塞到timcat服务器主动断开连接
        System.out.println(socket.getInputStream().read());//-1关闭连接标识

        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
