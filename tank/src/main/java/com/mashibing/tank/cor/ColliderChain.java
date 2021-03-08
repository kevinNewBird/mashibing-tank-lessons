package com.mashibing.tank.cor;

import com.mashibing.tank.frame.GameObject;
import com.mashibing.tank.frame.PropertyMgr;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/4 23:46
 * @version: 1.0
 ***********************/
public class ColliderChain implements Collider {
    private static Logger logger = LoggerFactory.getLogger(ColliderChain.class);

    //为什么使用Linked,而不是ArrayList
    //ArrayList是固定的,当其满了后要去动态扩展
    //而Linked是链表只需要在尾部添加即可,而且我们不需要动态的去访问下标所以用linked好
    private List<Collider> container = new LinkedList<>();

    public ColliderChain() {
        Arrays.stream(PropertyMgr.getString("colliders").split(","))
                .forEach(clazz -> Try.run(() -> add((Collider) Class.forName(clazz).getDeclaredConstructor().newInstance()))
                        .onFailure(e -> logger.error("初始化失败碰撞器!", e)));
    }

    public ColliderChain add(Collider c) {
        container.add(c);
        return this;
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (Collider collider : container) {
            if (!collider.collide(o1, o2)) {
                return false;
            }
        }
        return true;
    }
}
