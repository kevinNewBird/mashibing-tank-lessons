package com.mashibing.dm.iterator.v1;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/20 23:11
 * @version: 1.0
 ***********************/
public class Main {

    public static void main(String[] args) {
        ArrayList_ list = new ArrayList_();
        for(int i=0; i<15; i++) {
            list.add(new String("s" + i));
        }
        System.out.println(list.size());
    }

    static class ArrayList_{
        Object[] objects = new Object[10];
        //objects中下一个空的位置在哪儿,或者说，目前容器中有多少个元素
        private int index = 0;

        public void add(Object ele) {
            if (index == objects.length) {
                Object[] newObjects = new Object[objects.length * 2];
                System.arraycopy(objects, 0, newObjects, 0, objects.length);
                objects = newObjects;
            }

            objects[index] = ele;
            index++;
        }

        public int size() {
            return index;
        }
    }
}
