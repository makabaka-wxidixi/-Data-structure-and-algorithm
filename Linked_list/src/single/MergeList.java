package single;

import java.util.ArrayList;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-10-21 22:19
 */
public class MergeList {
    public Node head;
    public Node tail;

    public class Node {
        int val;
        Node next;

        public Node() {
        }

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

    /**
     * 重排链表
     * 方式一：将链表存储到List中，通过数组来操作
     * 时间复杂度：On
     * 空间复杂度：On
     *
     * @param head 目标链表
     */
    public void reorderList(Node head) {
        if (head == null || head.next == null || head.next.next == null)
            return;
        ArrayList<Node> list = new ArrayList<>();
        Node curr = head;//用于遍历链表
        while (curr != null) {
            list.add(curr);
            curr = curr.next;
        }
        int left = 0, right = list.size() - 1;
        while (left < right) {
            list.get(left).next = list.get(right);
            left++;
            if (left == right) {
                break;
            }
            list.get(right).next = list.get(left);
            right--;
        }
        list.get(left).next = null;
    }

    /**
     * 重排链表
     * 方式二：找到中间结点，以中间结点为分界线，翻转中间结点后面的链表，然后再交替合并两个链表
     * 时间复杂度：On
     * 空间复杂度：O1
     *
     * @param head 要重排链表的头结点
     * @return 返回新的链表的头结点
     */
    public Node reorderList2(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node fast = head, slow = head;//利用快慢指针找到中间结点
        Node curr = null;
        while (fast != null && fast.next != null) {
            if (fast.next.next == null) {
                curr = slow;//前面链表的尾结点，用于后续置空，放置处向环
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        Node rightHead = null;//右链表的头结点
        if (fast == null) {
            rightHead = slow;
            curr.next = null;
        } else {
            rightHead = slow.next;
            slow.next = null;
        }
        rightHead = reversalList(rightHead);
        return mergeTwoList(head, rightHead);
    }

    //反转链表
    public Node reversalList(Node head) {
        if (head == null) {
            return head;
        }
        Node rear = null, curr = head, tail = curr.next;
        while (tail != null) {
            curr.next = rear;
            rear = curr;
            curr = tail;
            tail = tail.next;
        }
        curr.next = rear;
        return curr;
    }

    //交替合并两个链表
    public Node mergeTwoList(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        Node newHead = head1, curr = null;
        boolean flag = true;
        while (head1 != null && head2 != null) {
            if (flag) {
                curr = head1.next;
                head1.next = head2;
                head1 = curr;
                flag = false;
            } else {
                curr = head2.next;
                head2.next = head1;
                head2 = curr;
                flag = true;
            }
        }
        return newHead;
    }


    /**
     * 合并K个有序链表
     *
     * @param arr 链表数组
     * @return 返回合并之后的链表
     */
    public Node mergeKList(Node[] arr) {
        Node head = new Node();
        for (int i = 0; i < arr.length; i++) {
            head.next = mergeList(head.next, arr[i]);
        }
        return head.next;
    }

    /**
     * 合并两个链表
     *
     * @param head1 第一个链表的头结点
     * @param head2 第二个链表的头结点
     * @return 返回合并之后的链表的头结点
     */
    public Node mergeList(Node head1, Node head2) {
        Node head = new Node();//新的头结点，用于返回新链表
        Node curr = head;//指向链表尾部，用于连接结点
        while (head1 != null || head2 != null) {
            if (head1.val < head2.val) {
                curr.next = head1;
                head1 = head1.next;
            } else {
                curr.next = head2;
                head2 = head2.next;
            }
            curr = curr.next;//尾部指针后移
        }
        curr.next = head1 == null ? head2 : head1;
        return head.next;
    }
}
