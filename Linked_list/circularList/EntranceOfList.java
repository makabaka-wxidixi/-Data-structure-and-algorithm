package circularList;

import single.DeleteNode;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-05 23:04
 */
public class EntranceOfList {
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
    public void insertByHead(int val) {
        Node node = new Node(val);
        if (head == null) {
            tail = node;
        }
        node.next = head;
        head = node;
    }

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

    public void list(Node head) {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.toString());
            curr = curr.next;
        }
    }

    public static void main(String[] args) {
        EntranceOfList list = new EntranceOfList();
        ArrayList<Node> arr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.insertByTail(i);
        }
        Node curr = list.head;
        for (int i = 0; i < 3; i++) {
            curr = curr.next;
        }
        list.tail.next = curr;
        Node node = list.DetectCircle2(list.head);
        System.out.println(node);
    }

    //**************************************找链表中环的第一个结点********************************************
    //找到入口就返回进入环的第一个节点，否则就返回null

    /**
     * 方式一：哈希表
     * 时间复杂度：On
     * 空间复杂度：On
     *
     * @param head 输入的链表
     * @return 返回修改后的链表
     */
    public Node DetectCircle(Node head) {
        if (head == null) {
            return null;
        }
        HashSet<Node> set = new HashSet<>();
        Node curr = head;
        while (curr != null) {
            if (set.contains(curr)) {
                return curr;
            } else {
                set.add(curr);
            }
            curr = curr.next;
        }
        return null;
    }

    /**
     * 方式一：快慢指针
     * 时间复杂度：On
     * 空间复杂度：O1
     *
     * @param head 输入的链表
     * @return 返回修改后的链表
     */
    public Node DetectCircle2(Node head) {
        if (head == null) {
            return null;
        }
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                Node ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}
