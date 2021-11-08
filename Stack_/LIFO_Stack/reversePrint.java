package LIFO_Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-07 20:55
 */
public class reversePrint {
    public Node head;
    public Node tail;

    class Node {
        int val;
        Node next;

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
                    "val=" + val + '}';
        }
    }

    //**********************************测试函数***************************************
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

    //打印链表
    public void List(Node head) {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        Node curr = head;
        while (curr.next != null) {
            System.out.print(curr);
            curr = curr.next;
        }
        System.out.println(curr);
    }

    public static void main(String[] args) {
        reversePrint list = new reversePrint();
        for (int i = 0; i < 10; i++) {
//            list.insertByHead(i);
            list.insertByTail(i);
        }
        list.List(list.head);
        int[] arr = list.reversePrint_2(list.head);
        System.out.println(Arrays.toString(arr));
    }

    //**********************************从尾到头链表****************************************

    /**
     * 方式一：栈
     * 时间复杂度：On
     * 空间复杂度：On
     *
     * @param head 传入的链表的头结点
     * @return 以数组形式返回反转之后的结点值
     */
    public int[] reversePrint_1(Node head) {
        if (head == null) {
            return null;
        }
        Node curr = head;
        Stack<Node> stack = new Stack<>();
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }
        int[] arr = new int[stack.size()];
        int index = 0;
        while (!stack.isEmpty()) {
            curr = stack.pop();
            arr[index++] = curr.val;
        }
        return arr;
    }

    /**
     * 方式二：递归
     * 时间复杂度：On
     * 空间复杂度：On
     *
     * @param head 传入的链表的头结点
     * @return 以数组形式返回反转之后的结点值
     */
    public int[] reversePrint_2(Node head) {
        reverse(head);
        int[] arr = new int[list.size()];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[index++] = list.get(i);
        }
        return arr;
    }

    public List<Integer> list = new ArrayList<>();

    public void reverse(Node head) {
        if (head == null)
            return;
        reverse(head.next);
        list.add(head.val);
    }
}
