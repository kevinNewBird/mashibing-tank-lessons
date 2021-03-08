package com.mashibing.tank.cor;

import com.mashibing.tank.frame.GameObject;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/4 23:39
 * @version: 1.0
 ***********************/
public interface Collider {

   /**
    * Description: 有返回值的目的? <BR>
    *     如果tank和子弹相撞了,就不应该在执行tank间的相撞,否则会导致不可预知的bug
    *
    * @author zhao.song    2021/3/8 22:54
    * @param o1:
    * @param o2:
    * @return {@link boolean}
    */
   public boolean collide(GameObject o1, GameObject o2);
}
