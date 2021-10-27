package single;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-10-27 22:09
 */
public class ReverseList {
    public Node head;
    public Node tail;


    public class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }

    //头插法
    public void insertByHead(int val) {
        Node node = new Node(val);
        if (head == null) {
            head = node;
            tail = node;
            return;
        }
        node.next = head;
        head = node;
    }

    //尾插法
    public void insertByRear(int val) {
        Node node = new Node(val);
        if (head == null) {
            head = node;
            tail = node;
            return;
        }
        tail.next = node;
        tail = node;
    }

    public void list() {
        Node curr = head;
        while (curr != null) {
            System.out.println(curr.toString());
            curr = curr.next;
        }
    }

    public static void main(String[] args) {
        ReverseList list = new ReverseList();
        for (int i = 0; i < 10; i++) {
            list.insertByRear(i);
        }
        list.list();
    }

    /**
     * 反转链表
     * 方式一：迭代，从头到尾遍历链表，遍历途中进行翻转
     * 时间复杂度：On
     * 空间复杂度：O1
     *
     * @param head 链表头结点
     * @return 链表头结点
     */
    public Node reverseList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node prev = null, curr = head, rear = null;
        while (curr != null) {
            rear = curr.next;
            curr.next = prev;
            prev = curr;
            curr = rear;
        }
        return prev;
    }

    /**
     * 反转链表
     * 方式二：转换成数组，再从尾部开始逐一连接结点
     * 时间复杂度：On
     * 空间复杂度：On（使用额外数组）
     *
     * @param head 链表头结点
     * @return 链表头结点
     */
    public Node reverseList2(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node curr = head;
        List<Node> list = new ArrayList<>();
        while (curr != null) {
            list.add(curr);
            curr = curr.next;
        }
        for (int i = list.size() - 1; i > 0; i--) {
            list.get(i).next = list.get(i - 1);
        }
        list.get(0).next = null;
        return list.get(list.size() - 1);
    }

    /**
     * 反转链表
     * 方式三：递归实现
     * 时间复杂度：On
     * 空间复杂度：On（用了栈）
     * @param head 链表头结点
     * @return 链表头结点
     */
    public Node reverseList3(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node Head = reverseList3(head.next);
        head.next.next = head;
        head.next = null;
        return Head;
    }
}
