package com.mashibing.tank.frame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
    public static BufferedImage tankL,tankU,tankR,tankD;
    public static BufferedImage bulletL,bulletU,bulletR,bulletD;
    static {
        try {
//            int i = 1 / 0;
            tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
            bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
        } catch (Exception e) {
            logger.error("初始化资源加载失败!",e);
        }

    }

    public static void main(String[] args) {

    }
}
