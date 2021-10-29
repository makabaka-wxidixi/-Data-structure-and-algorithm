package single;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-10-29 22:08
 */
public class DeleteNode {
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

    public void list() {
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
        DeleteNode list = new DeleteNode();
        for (int i = 0; i < 10; i++) {
            list.insertByHead(i);
        }
        list.list();
    }

    //***************************************以下是主体函数*******************************************

    /**
     * 删除倒数第N个结点
     * @param head 链表头结点
     * @param n 倒数第n个结点
     * @return 返回删除之后的链表头结点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }

}
