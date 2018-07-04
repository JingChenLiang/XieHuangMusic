package xiehuang.com.android.xiehuangmusic.jingcl.test_linkedlist;

import com.orhanobut.logger.Logger;

/**
 * Created by erliang on 2018/6/4.
 */

public class LinkList {
    public Node head;
    public Node current;
    private int size = 0;

    class Node {
        //注：此处的两个成员变量权限不能为private，因为private的权限是仅对本类访问。
        int data; //数据域
        Node next;//指针域

        public Node(int data) {
            this.data = data;
        }
    }

    //方法：向链表中添加数据
    public void add(int data) {
        size++;
        //判断链表为空的时候
        if (head == null) {//如果头结点为空，说明这个链表还没有创建，那就把新的结点赋给头结点
            head = new Node(data);
            current = head;
        } else {
            //创建新的结点，放在当前节点的后面（把新的结点和链表进行关联）
            current.next = new Node(data);
            //把链表的当前索引向后移动一位
            current = current.next; //此步操作完成之后，current结点指向新添加的那个结点

        }
    }

    //linklist size
    public int size() {
        return size;
    }

    //方法：遍历链表（打印输出链表。方法的参数表示从节点node开始进行遍历
    public void print(Node node) {
        if (node == null) {
            return;
        }

        current = node;
        while (current != null) {
            Logger.i("head = " + current.data);
            current = current.next;
        }
    }

    //方法：获取单链表的长度
    public int getLength(Node head) {
        if (head == null) {
            return 0;
        }

        int length = 0;
        Node current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        return length;
    }

}



