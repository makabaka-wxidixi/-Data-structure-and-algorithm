package single;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-01 20:39
 */
public class SearchNode {
    public Node head;
    public Node tail;

    public class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }

    //***************************************以下是测试函数*******************************************

    //头插法
    public void insertByHead(int val) {
        Node node = new Node(val);
        if (head == null) {
            tail = node;
        }
        node.next = head;
        head = node;
    }

    //尾插法
    public void insertByTail(int val) {
        Node node = new Node(val);
        if (head == null) {
            head = node;
            tail = node;
            return;
        }
        tail.next = node;
        tail = node;
    }

    //输出链表
    public void list(Node head) {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        while (head != null) {
            System.out.print(head.toString() + "->");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        SearchNode list = new SearchNode();
        for (int i = 0; i < 6; i++) {
//            list.insertByHead(i);
            list.insertByTail(i);
        }
        list.list(list.head);
        Node node = list.middleNode(list.head);
        System.out.println();
        list.list(node);

    }

    //***************************************查找倒数第k个结点*******************************************

    /**
     * 递归
     * 时间复杂度：On
     * 空间复杂度：On（栈帧）
     *
     * @param head 链表的头结点
     * @param k 倒数第k个结点
     * @return 返回目标结点的val值
     */
    public int i;

    public int searchKFromLast(Node head, int k) {
        if (head == null) {
            return 0;
        }
        int res = searchKFromLast(head.next, k);
        if (++i == k) {
            return head.val;
        }
        return res;
    }

    /**
     * 方式二：前后指针
     * 时间复杂度：On
     * 空间复杂度：O1
     *
     * @param head 传入的链表的头结点
     * @param k    倒数第k个结点
     * @return 返回目标节点的val
     */
    public int searchKFromLast2(Node head, int k) {
        if (head == null) {
            System.out.println("链表为空");
            return 0;
        }
        Node left = head, right = head;
        for (int i = 1; i < k; i++) {//设置偏移量
            right = right.next;
        }
        while (right.next != null) {//当右指针指向最后一个结点的时候，此时左指针指向的就是倒数第k个结点
            left = left.next;
            right = right.next;
        }
        return left.val;
    }

    /**
     * 方式三：迭代
     * 时间复杂度：On
     * 空间复杂度：O1
     *
     * @param head 传入的链表的头结点
     * @param k    倒数第k个结点
     * @return 返回目标节点的val
     */
    public int searchKFromLast3(Node head, int k) {
        int len = 0;
        Node curr = head;
        while (curr != null) {//得到链表长度
            len++;
            curr = curr.next;
        }
        curr = head;
        for (int i = 0; i < len - k; i++) {
            curr = curr.next;
        }
        return curr.val;
    }

    //***************************************查找链表中间结点*******************************************
    private int len;
    private int k;

    /**
     * 方式一：递归
     * 时间复杂度：On
     * 空间复杂度：On（栈帧）
     *
     * @param head 链表头结点
     * @return 返回修改之后的链表
     */
    public Node middleNode(Node head) {
        if (head == null) {
            return null;
        }
        len++;//链表长度
        Node curr = middleNode(head.next);
        k++;
        if (k == Math.ceil(len / 2.0)) {//向上取整
            return head;
        }
        return curr;
    }

    /**
     * 方式二：迭代
     * 时间复杂度：On
     * 空间复杂度：O1
     *
     * @param head 链表头结点
     * @return 修改后的链表头结点
     */
    public Node middleNode2(Node head) {
        int len = 0;
        Node curr = head;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        curr = head;
        for (int i = 0; i < len; i++) {
            if (i == len / 2) {
                return curr;
            }
            curr = curr.next;
        }
        return head;
    }

    /**
     * 方式三：快慢指针（一次遍历）
     * 时间复杂度：On
     * 空间复杂度：O1
     *
     * @param head 链表头结点
     * @return 修改后的链表头结点
     */
    public Node middleNode3(Node head) {
        Node fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    //***************************************查找链表中的下一个更大结点结点*******************************************

    /**
     * 单调找
     * 时间复杂度；On（遍历链表，每个数字进出单调栈只有一次）
     * 空间复杂度：On（使用了栈，和数组）
     *
     * @param head 链表头结点
     * @return 修改后的链表头结点
     */
    public int[] nextLargeNodes(Node head) {
        if (head == null) {
            return null;
        }
        int len = 1;//用于记录链表长度
        Node prev = null, curr = head, rear = head.next;
        while (rear != null) {//反转链表操作
            len++;
            curr.next = prev;
            prev = curr;
            curr = rear;
            rear = rear.next;
        }
        curr.next = prev;//curr就是链表反转后新的头结点
        int[] arr = new int[len];
        int index = len - 1;//从末尾开始填充数组
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        while (curr != null) {
            if (curr.val >= stack.peek()) {//当前节点的val值大于等于栈顶元素
                while (stack.size() > 1 && curr.val >= stack.peek()) {//循环查找比当前节点大的栈顶元素
                    stack.pop();//如果当前栈顶元素小于当前节点，那么就弹出当前栈顶元素，用更大的栈顶元素进行比较
                }
                arr[index--] = stack.peek();
                stack.push(curr.val);
            } else {
                arr[index--] = stack.peek();
                stack.push(curr.val);
            }
            curr = curr.next;//继续遍历下一个结点
        }
        return arr;
    }
}
