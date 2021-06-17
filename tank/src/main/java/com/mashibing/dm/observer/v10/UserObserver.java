package com.mashibing.dm.observer.v10;

/**
 * description  观察者的注册 <BR>
 * <p>
 * TIP: 考虑在工程启动的时候加入到MyListener中, 如果是springboot工程,注解即可实现
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/11 10:48
 **/
public class UserObserver extends AbstractObserver<BaseEvent<User>> {

    @Override
    public void onEvent(BaseEvent<User> event) {
        //
        System.out.println("事件监听:" + event.getSource().toString());
    }
}
