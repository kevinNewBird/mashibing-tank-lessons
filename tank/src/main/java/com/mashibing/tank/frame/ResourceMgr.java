package com.mashibing.tank.frame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/***********************
 * Description: 资源管理类 <BR>
 *     <p>
 *         类似图片这种,一般都是先将需要用的资源加载进来(loading)
 *     </p>
 * author: zhao.song
 * date: 2020/12/22 0:33
 * version: 1.0
 ***********************/
public class ResourceMgr {
    private static Logger logger = LoggerFactory.getLogger(ResourceMgr.class);
    public static BufferedImage tankL, tankU, tankR, tankD;
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;
    //爆炸效果
    public static BufferedImage[] explodes = new BufferedImage[16];
    public static BufferedImage boom0, boom1, boom2, boom3, boom4, boom5, boom6, boom7, boom8, boom9, boom10;

    static {
        try {
//            int i = 1 / 0;
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            tankL = ImageUtil.rotateImage(tankU, -90);
            tankR = ImageUtil.rotateImage(tankU, 90);
            tankD = ImageUtil.rotateImage(tankU, 180);
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);
            for (int i = 0; i < explodes.length; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
        } catch (Exception e) {
            logger.error("初始化资源加载失败!", e);
        }

    }

    public static void main(String[] args) {

    }
}
