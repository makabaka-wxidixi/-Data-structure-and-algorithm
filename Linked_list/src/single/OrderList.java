package single;

import java.util.*;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-10-23 20:06
 */
public class OrderList {
    ListNode head = null;
    ListNode tail = head;


    public void list() {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.toString());
            curr = curr.next;
        }
    }

    //尾插法
    public void insertByRear(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            insertByRear(arr[i]);
        }
    }

    //尾插法
    public void insertByRear(int val) {
        ListNode node = new ListNode(val);
        if (head == null) {
            head = node;
            tail = node;
            return;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    //头插法
    public void insertByHead(int val) {
        ListNode node = new ListNode(val);
        if (head == null) {
            head = node;
            tail = node;
            return;
        } else {
            node = head.next;
            head.next = node;
        }
    }

    public void insertByHead(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            insertByHead(arr[i]);
        }
    }

    /**
     * 对链表进行排序——方法一
     *
     * @param Head 传入头结点
     * @return 返回头结点
     */
    public ListNode sortList(ListNode Head) {
        if (Head == null || Head.next == null)
            return Head;
        List<ListNode> list = new ArrayList<>();
        ListNode curr = Head;
        while (curr != null) {//将链表放入到一个集合中
            list.add(curr);
            curr = curr.next;
        }
        Collections.sort(list, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o2.val - o1.val;
            }
        });
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).next = list.get(i + 1);
        }
        list.get(list.size() - 1).next = null;
        return Head;
    }

    /**
     * 对链表进行遍历——方法二：归并排序
     *
     * @param head 传入头结点
     * @return 返回新链表的头结点
     */
    public ListNode sortListByMerge(ListNode head) {
        return sortList(head, null);
    }

    private ListNode sortList(ListNode start, ListNode end) {
        if (start == end) return end;
        ListNode fast = start, slow = start;
        while (fast != end && fast.next != end) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode l1 = sortList(slow.next, end);
        slow.next = null;
        ListNode l2 = sortList(start, slow);
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        if (l1.val > l2.val) {
            l2.next = merge(l1, l2.next);
            return l2;
        } else {
            l1.next = merge(l1.next, l2);
            return l1;
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "[" +
                "val=" + val +
                "]->";
    }
}
