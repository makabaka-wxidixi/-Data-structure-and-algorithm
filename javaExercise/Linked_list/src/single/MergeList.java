package single;

import java.util.ArrayList;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-10-21 22:19
 */
public class MergeList {
    public class Node {
        int val;
        Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 重排链表
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
