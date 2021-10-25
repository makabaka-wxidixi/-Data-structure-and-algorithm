package single;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-10-25 22:52
 */
public class PalindromeList {
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

    /**
     * 判断是否回文
     * 方式一：将链表存入集合中，用左右指针来扫描判断
     * 时间复杂度：On
     * 空间复杂度：On
     * @param head
     * @return
     */
    public boolean isPalindrome(Node head) {
        if (head == null) {
            return false;
        }
        List<Node> list = new ArrayList<>();
        Node curr = head;
        while (curr != null) {
            list.add(curr);
            curr = curr.next;
        }
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (list.get(left).val != list.get(right).val) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
