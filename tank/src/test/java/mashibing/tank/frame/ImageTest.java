package mashibing.tank.frame;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/***********************
 * Description: TODO <BR>
 * author: zhao.song
 * date: 2020/12/16 18:57
 * version: 1.0
 ***********************/
public class ImageTest {

    private Map<String, Object> map = new HashMap<String,Object>(){
        {
            put("a", 1);
            put("b", 2);
        }
    };

    @Test
    public void testLock() throws IOException, InterruptedException {
        new Thread(() -> {
            synchronized (this.map) {
                try {
                    System.out.println("上锁成功！");
                    TimeUnit.SECONDS.sleep(20);
                    System.out.println("持有锁20s！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        TimeUnit.SECONDS.sleep(3);
        System.out.println("主线程结束休眠，确保线程优先获取锁！");
        System.out.println(this.map.get("a"));
        System.in.read();
    }

    @Test
    public void test() {


        try {
            FileOutputStream fos = new FileOutputStream("w12.txt");
//            fos.write("ssssss".getBytes(StandardCharsets.UTF_8));
            fos.flush();
            fos.close();
            //绝对路径的局限性:如果打包给用户,也必须要求用户的电脑上存在该目录
//            BufferedImage bi = ImageIO.read(new File("E:\\deskFile\\trs_work\\工作任务\\新疆融媒体云--新疆日报\\素材\\picture\\W020200602512596132635.jpeg"));
//            BufferedImage bi = ImageIO.read(ImageTest.class.getClassLoader()
//                    .getResourceAsStream("images/0.gif"));
//            assertNotNull(bi);
            ArrayList<Integer> list = new ArrayList<>();
            Collections.addAll(list, 1, 2, 4, 5, 6, 7, 8, 10, 12);
            int pointsDataLimit = 2;
            int size = list.size();
            // 判断是否有必要分批
            if (pointsDataLimit < size) {
                int part = size / pointsDataLimit;// 分批数
                for (int i = 0; i < part; i++) {
                    List<Integer> listPage = list.subList(0, pointsDataLimit);
                    System.out.println(listPage);
                    list.subList(0, pointsDataLimit).clear();
                }
                System.out.println(list);
            }
        } catch (Exception e) {

        }
    }
}
