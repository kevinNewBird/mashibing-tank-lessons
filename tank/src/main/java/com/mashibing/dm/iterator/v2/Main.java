package com.mashibing.dm.iterator.v2;


import java.util.stream.IntStream;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/21 19:38
 * @version: 1.0
 ***********************/
public class Main {

    public static void main(String[] args) {
        LinkedList_ list = new LinkedList_();
        IntStream.range(0, 5).forEach(index -> list.add(index));
        System.out.println(list);
        System.out.println(list.size());
    }


}

class LinkedList_ {

    Node_ head;
    Node_ tail;

    public int size(){
        return size;
    }

    private int size;

    public void add(Object o) {
        Node_ newNode = new Node_(o);
        newNode.next = null;
        if (head == null) {
            this.head = newNode;
            this.tail = newNode;
        }

        tail.next = newNode;
        tail = newNode;
        size++;
    }

    class Node_ {
        Object v;//节点存储值
        Node_ next;//下一个节点

        public Node_(Object v) {
            this.v = v;
        }
    }

}

