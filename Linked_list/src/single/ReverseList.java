package single;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

        public Node() {
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }

    //***************************************以下是测试函数*****************************************
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

    //打印链表
    public void list(Node head) {
        if (head == null) return;
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.toString());
            curr = curr.next;
        }
    }

    public static void main(String[] args) {
        ReverseList list = new ReverseList();
        for (int i = 0; i < 10; i++) {
            list.insertByHead(i);
        }
        list.list(list.head);
        System.out.println();
        list.list(list.reverseBetween2(list.head, 2, 9));
    }

    //***************************************以下是主体函数*****************************************

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
     *
     * @param head 链表头结点
     * @return 链表头结点
     */
    public Node reverseList3(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node Head = reverseList3(head.next);//传递头结点
        head.next.next = head;
        head.next = null;
        return Head;
    }

    /**
     * 反转链表
     * 方式四：栈实现，先进后出，后进先出
     * 时间复杂度：On
     * 空间复杂度：On
     *
     * @param head 将要翻转的链表的头结点
     * @return 返回排序之后的链表的头结点
     */
    public Node reverseList4(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Stack<Node> stack = new Stack<>();
        Node curr = head;
        while (curr != null) {//所有节点入栈
            stack.push(curr);
            curr = curr.next;
        }
        head = stack.pop();
        curr = head;
        while (!stack.isEmpty()) {
            curr.next = stack.pop();
            curr = curr.next;
        }
        curr.next = null;
        return head;
    }

    /**
     * 翻转部分链表
     * 方式一：转换成数组
     * 时间复杂度：On
     * 空间复杂度：On（链表长度）
     *
     * @param head  链表头结点
     * @param left  左边界
     * @param right 右边界
     * @return 反转之后的链表的头结点
     */
    public Node reverseBetween(Node head, int left, int right) {
        if (head == null || head.next == null || right <= left) {
            return head;
        }
        Node curr = head;
        List<Node> list = new ArrayList<>();
        while (curr != null) {
            list.add(curr);
            curr = curr.next;
        }
        if (right > list.size()) {//右边界大于链表长度
            return head;
        }
        for (int i = right - 1; i >= left; i--) {//将链表重新连接
            list.get(i).next = list.get(i - 1);
        }
        if (list.size() == right && left == 1) {//翻转整个链表
            list.get(0).next = null;
            return list.get(right - 1);
        }
        if (list.size() > right && left == 1) {//left最小，right不是最大
            list.get(left - 1).next = list.get(right);
            return list.get(right - 1);
        }
        if (list.size() == right && left > 1) {//right最大，left不是最小
            list.get(left - 1).next = null;
            list.get(left - 2).next = list.get(right - 1);
            return list.get(0);
        }
        //剩下的是一般情况
        list.get(left - 1).next = list.get(right);
        list.get(left - 2).next = list.get(right - 1);
        return list.get(0);
    }

    /**
     * 翻转部分链表
     *
     * @param head  链表头结点
     * @param left  左边界
     * @param right 右边界
     * @return 返回反转之后的链表
     */
    public Node reverseBetween2(Node head, int left, int right) {
        if (head == null || head.next == null || left >= right) {
            return head;
        }
        Node hair = new Node();
        hair.next = head;
        Node prev = hair;
        for (int i = 1; i < left; i++) {//prev指向left的前驱结点
            prev = prev.next;
        }
        Node rightNode = prev;
        for (int i = 0; i < right - left + 1; i++) {//找到right指向的节点
            rightNode = rightNode.next;
        }
        Node leftNode = prev.next;//left指向的节点
        Node rear = rightNode.next;//right指向的节点的后继节点
        //断开链表
        prev.next = null;
        rightNode.next = null;
        reverse1(leftNode);//这里用reverse1和reverse2都可以
        prev.next = rightNode;
        leftNode.next = rear;
        return hair.next;
    }

    //翻转指定链表，迭代
    private void reverse1(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node rear = curr.next;
            curr.next = prev;
            prev = curr;
            curr = rear;
        }
    }

    //翻转指定链表，递归
    private void reverse2(Node head) {
        if (head == null || head.next == null)
            return;
        reverse2(head.next);
        head.next.next = head;
        head.next = null;//主要是为了防止尾结点形成环
    }

}