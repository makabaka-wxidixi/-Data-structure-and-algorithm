package single;

import java.util.Stack;

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
        DeleteNode list = new DeleteNode();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                list.insertByHead(i);
            }
        }
        Node node = list.deleteDuplicate2(list.head);
        list.list(node);
    }

    //**************************************删除倒数第N个结点*******************************************

    /**
     * 方式一：迭代。先遍历链表，得到长度。然后for循环找前驱结点
     * 时间复杂度：On     <n,2n>
     * 空间复杂度：O1
     *
     * @param head 链表头结点
     * @param n    倒数第n个结点
     * @return 返回删除之后的链表头结点
     */
    public Node removeNthFromEnd(Node head, int n) {
        Node hair = new Node(-1, head);
        int length = getLength(head);//链表长度
        Node prev = hair;//要删除结点的前驱结点
        for (int i = 1; i < length - n + 1; ++i) {
            prev = prev.next;
        }
        prev.next = prev.next.next;
        return hair.next;
    }

    public int getLength(Node head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }

    /**
     * 方式二：栈。根据栈的先入后出的特性来找到要删除结点的前驱结点
     * 时间复杂度：On
     * 空间复杂度：On（主要是栈的开销）
     *
     * @param head 链表头结点
     * @param n    倒数第n个结点
     * @return 返回删除之后的链表头结点
     */
    public Node removeNthFromEnd2(Node head, int n) {
        Node hair = new Node();
        hair.next = head;
        Node curr = hair;
        Stack<Node> stack = new Stack<>();
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }//入栈
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        Node prev = stack.peek();//找到要删除结点的前驱结点
        prev.next = prev.next.next;
        return hair.next;
    }

    /**
     * 方式三：左右指针，设置一个偏移量，右指针到达右边界的时候，左指针就指向了要删除结点的前驱结点
     *
     * @param head 链表头结点
     * @param n    倒数第n个结点（偏移量）
     * @return 返回删除之后的链表头结点
     */
    public Node removeNthFromEnd3(Node head, int n) {
        Node hair = new Node();
        hair.next = head;
        Node left = hair, right = left;//左右指针
        for (int i = 0; i < n; i++) {
            right = right.next;
        }
        while (right.next != null) {//当右指针碰到右边界时，此时左指针指向的就是要删除结点的前驱结点
            right = right.next;
            left = left.next;
        }
        left.next = left.next.next;//left是前驱结点
        return hair.next;
    }

    /**
     * 方式四：递归。回溯过程中用一个变量和n作比较，来寻找目标结点
     * 时间复杂度：On
     * 空间复杂度：On（堆栈帧）
     *
     * @param head
     * @param n
     * @return
     */
    public int j;//用于回溯时寻找要删除的节点

    public Node removeNthFromEnd4(Node head, int n) {
        Node hair = new Node(0, head);
        recursion(hair, n);
        return hair.next;
    }

    private void recursion(Node head, int n) {
        if (head.next == null) {//最后一个结点就返回
            return;
        }
        recursion(head.next, n);
        j++;
        if (j == n) {
            head.next = head.next.next;//head就是倒数第j个结点的前驱结点
        }
    }

    //**************************************删除重复结点1.0*******************************************

    /**
     * 方式一：双指针
     * 删除重复结点，使所有节点只出现一次
     *
     * @param head 链表头结点
     * @return 返回修改后的链表
     */
    public Node deleteDuplicate(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node left = head, right = left.next;
        while (right != null) {
            if (left.val == right.val) {//两个结点内容相同
                if (right.next == null) {//最后一个结点
                    left.next = null;
                }
                right = right.next;
            } else {
                left.next = right;
                left = right;
                right = right.next;
            }
        }
        return head;
    }

    /**
     * 方式二：单指针
     * 时间复杂度：On
     * 空间复杂度：O1
     *
     * @param head 链表头结点
     * @return 放回修改后的头结点
     */
    public Node deleteDuplicate2(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node curr = head;
        while (curr.next != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }

    //**************************************删除重复结点2.0*******************************************

    /**
     * 方式一：三个指针，一次遍历
     * 时间复杂度：On（链表长度）
     * 空间复杂度：O1
     *
     * @param head 链表头结点
     * @return 返回删除之后的链表头结点
     */
    public Node deleteDuplicates(Node head) {
        if (head == null || head.next == null) {//链表为空或者只有一个结点
            return head;
        }
        Node hair = new Node(-1, head);
        Node prev = hair, curr = head, rear = head.next;
        while (rear != null) {
            while (rear != null && curr.val == rear.val) {//外层循环是考虑到可能有多组相同的结点挨在一起，例如3.3.4.4.5.5
                while (rear != null && curr.val == rear.val) {
                    rear = rear.next;
                }
                if (rear == null) {//说明curr到rear都是相同的，此时已经到达链表尾部
                    prev.next = null;
                    return hair.next;//head可能会被删除，后续可能还有结点，所以返回形式为hair.next
                }
                curr = rear;
                rear = rear.next;
            }
            prev.next = curr;
            prev = curr;
            curr = rear;
            if (rear != null) {
                rear = rear.next;
            }
        }
        return hair.next;//为什么不返回head？因为head有可能会被删除，此时如果再返回head，那么链表就丢失了。应该返回hair.next
    }

}
