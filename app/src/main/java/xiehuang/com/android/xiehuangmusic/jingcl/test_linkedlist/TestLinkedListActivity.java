package xiehuang.com.android.xiehuangmusic.jingcl.test_linkedlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import xiehuang.com.android.xiehuangmusic.R;

public class TestLinkedListActivity extends AppCompatActivity {

    LinkList list = new LinkList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_linked_list);

        //        initData();

        fixYsf();
    }

    private void fixYsf() {
        CycLink cyclink = new CycLink();
        cyclink.setLength(5);//5个人
        cyclink.createLink();
        cyclink.printLink();
        cyclink.setK(5);
        cyclink.setM(3);
        cyclink.countResult();
    }

    public void initData() {
        //向LinkedList中添加数据
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        // 从head节点开始遍历输出
        list.print(list.head);
        //从头结点开始计算链表的长度
        Logger.i("TestLinkedListActivity/length = " + list.getLength(list.head));

        /**
         * //current节点增加一个节点,需要被原来的current节点指向且新增的节点需要指向原来的current节点的next   原来1-》3；增加2变为 1-》2-》3
         * //创建新节点，新增的节点赋值给current节点的next，从而实现连接
         * //新增加的节点为current节点，所以需要更新尾节点：新增的节点赋值给原来的尾节点
         * Node node = new Node("node1data2");
         * 先连接后面的，再连接前面的
         * node.next = current.next;
         * current.next = node；
         */
    }

    class Node {
        int data;
        Node nextNode;

        public Node(int data) {
            this.data = data;
        }
    }


    /**
     * 链表：存储在内存中的连续数据，形成数据结构，如同map、list、set等也是一块连续的、有相关属性的数据结构。不同的
     * 数据结构具有不同的存储形式，数据与数据之间不同的关联关系。
     *
     * 1、慕课网的链表继续：倒叙等
     *
     *  约瑟夫问题是list链表的应用之一
     * 问题描述：N个人围成一个圆圈，每个人都有唯一的一个编号，编号从1到N，从编号为1的人开始报数，依次报到K，
     * 报数为K的人出列，他的下一个又从1开始报数，直到所有的人都出列，求这个出列的序列。
     * 环形链表：比如5个人，设定第k=1个人开始数，
     * 数到m=3的出列，下一个继续从1开始数
     * 5个人出列顺序为：3-1-5-2-4
     */
    class CycLink {
        //环形链表大小
        //指向第一个小孩的应用不能动
        int length = 0;
        int k = 0;//从哪里开始数
        int m = 0;//数到几

        //使用前，对头结点和当前节点初始化
        Node firstNode = null;
        Node temp = null;

        public void setK(int k) {
            this.k = k;
        }

        //设置链表大小
        public void setLength(int length) {
            this.length = length;
        }

        public void setM(int m) {
            this.m = m;
        }

        //初始化环形链表
        public void createLink() {
            for (int i = 1; i <= length; i++) {
                //创建第一个小孩
                if (i == 1) {
                    Node Node = new Node(i);
                    this.firstNode = Node;
                    this.temp = Node;
                } else {
                    Node Node = new Node(i);
                    this.temp.nextNode = Node;
                    this.temp = Node;
                    if (i == this.length) {
                        //创建最后一个小孩
                        this.temp.nextNode = this.firstNode;
                    }
                }
            }
        }

        public void printLink() {
            StringBuilder builder = new StringBuilder();
            Node temp = this.firstNode;
            do {
                builder.append(temp.data + "").append("->");
                temp = temp.nextNode;
            } while (temp != firstNode);

            Logger.i(builder.toString());//1->2->3->4->5->
        }

        public void countResult() {
            StringBuilder builder = new StringBuilder();
            Node temp = this.firstNode;

            //找到数数的结点
            for (int i = 1; i < k; i++) {
                temp = temp.nextNode;
            }

            while (this.length != 0) {
                //数m下，找到数到m的结点
                for (int j = 1; j < m; j++) {
                    temp = temp.nextNode;
                }
                //找到出圈的前一个结点
                Node temp1 = temp;
                while (temp1.nextNode != temp) {
                    temp1 = temp1.nextNode;
                }
                //将数m的结点删除  1.更新前节点的下一节点   2.更新当前节点
                builder.append(temp.data + "").append("->");
                temp1.nextNode = temp.nextNode;
                temp = temp.nextNode;
                this.length--;
            }

            //打印出节点删除的记录
            Logger.i(builder.toString());//3->1->5->2->4->

        }
    }

}
