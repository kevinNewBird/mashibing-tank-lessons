package mashibing.tank.frame;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/***********************
 * Description: TODO <BR>
 * author: zhao.song
 * date: 2020/12/16 18:57
 * version: 1.0
 ***********************/
public class ImageTest {

    @Test
    public void test() {

        try {
            //绝对路径的局限性:如果打包给用户,也必须要求用户的电脑上存在该目录
//            BufferedImage bi = ImageIO.read(new File("E:\\deskFile\\trs_work\\工作任务\\新疆融媒体云--新疆日报\\素材\\picture\\W020200602512596132635.jpeg"));
            BufferedImage bi = ImageIO.read(ImageTest.class.getClassLoader()
                    .getResourceAsStream("images/0.gif"));
            assertNotNull(bi);
        } catch (IOException e) {

        }
    }
}
