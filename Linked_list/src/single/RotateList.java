package single;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-10-20 22:19
 */
public class RotateList {
    public Node head = null;

    /**
     * 链表向左旋转
     *
     * @param step 向左旋转的步数
     */
    public void leftRotate(int step) {
        if (head == null || head.next == null)
            return;
        int len = 0;
        Node curr = head;
        while (curr != null) {
            ++len;
            curr = curr.next;
        }
        step %= len;
        if (step == 0) {
            return;
        }
        curr = head;
        for (int i = 1; i < step; i++) {
            curr = curr.next;
        }
        //curr指向新的尾结点
        Node Head = head;
        head = curr.next;
        curr.next = null;
        curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        //右边链表尾结点连接到左链表的头结点
        curr.next = Head;
    }

    /**
     * 向右旋转链表
     *
     * @param step 向右旋转的步数
     */
    public void rightRotate(int step) {
        if (head == null || head.next == null)//没有节点或者只有一个节点
            return;
        int len = 0;
        Node curr = head;
        while (curr != null) {
            ++len;
            curr = curr.next;
        }
        //len 是链表长度
        step %= len;
        if (step == 0)
            return;
        curr = head;//将curr指向头部，重新遍历
        for (int i = 1; i < len - step; i++) {
            curr = curr.next;
        }
        //curr指向新链表的尾结点
        Node Head = head;
        head = curr.next;//新的头结点
        curr.next = null;
        curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        //curr指向右链表的最后一个结点
        curr.next = Head;
    }

    //增（头插法）
    public void add(int val) {
        Node node = new Node(val);
        if (head == null) { //头节点如果为空，就充当头结点
            head = node;
            return;
        }
        node.next = head.next;
        head.next = node;
    }

    //遍历
    public void list() {
        if (head == null)
            return;
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.toString());
            curr = curr.next;
        }
    }

    public class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "[" +
                    "val=" + val +
                    "]->";
        }
    }

}
