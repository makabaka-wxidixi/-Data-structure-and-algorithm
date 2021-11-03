package single;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-02 14:34
 */
public class SwapNode {
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

    //****************************************测试函数******************************************

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

    public void list(Node head) {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        Node curr = head;
        while (curr.next != null) {
            System.out.print(curr.toString() + "->");
            curr = curr.next;
        }
        System.out.println(curr.toString());
    }

    public static void main(String[] args) {
        SwapNode list = new SwapNode();
        for (int i = 0; i < 10; i++) {
//            list.insertByHead(i);
            list.insertByTail(i);
        }
        list.list(list.head);
        Node node = list.swapNode(list.head,5);
        list.list(node);
    }

    //*******************************************交换相邻奇偶结点*******************************************

    /**
     * 方式一：迭代
     * 时间复杂度：On
     * 空间复杂度：O1
     *
     * @param head 链表头结点
     * @return 返回修改后的链表
     */
    public Node swapNodes(Node head) {
        if (head == null || head.next == null) {//链表为空或者只有一个头结点
            return head;
        }
        Node hair = new Node(-1, head);
        Node prev = hair, curr = head, rear = head.next;
        while (prev.next != null && prev.next.next != null) {//prev后面还有两个结点
            curr = prev.next;
            rear = prev.next.next;
            //交换操作
            prev.next = rear;
            curr.next = rear.next;
            rear.next = curr;
            //更新prev
            prev = curr;
        }
        return hair.next;
    }

    /**
     * 方式二：递归
     * 时间复杂度：On
     * 空间复杂度：On（栈帧）
     *
     * @param head 链表头结点
     * @return 返回修改后的链表
     */
    public Node swapNodes2(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node temp = head.next;
        head.next = swapNodes2(temp.next);
        temp.next = head;
        return temp;
    }

    //*****************************************交换链表对称结点*****************************************

    /**
     * 方式一：双指针（一次遍历）
     * 时间复杂度：On（整个过程fast从头到尾遍历一遍，之后就是判断）
     * 空间复杂度：O1
     *
     * @param head 链表头结点
     * @return 返回修改后的链表
     */
    public Node swapNode(Node head, int k) {
        if (head == null) {
            return null;
        }
        Node hair = new Node(-1, head);//创建哑结点，因为头结点可能会被交换
        Node slow = hair, fast = hair;//前后指针，定位两个结点，
        for (int i = 1; i < k; i++) {
            fast = fast.next;
        }
        Node leftPrev = fast, left = fast.next, leftRear = fast.next.next;//左节点前驱，左节点，左节点后继节点
        while (fast.next.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        Node rightPrev = slow, right = slow.next, rightRear = slow.next.next;//右结点前驱，右结点，右结点后继节点
        if (leftRear == right) {//左节点是右结点的前驱
            leftPrev.next = right;
            right.next = left;
            left.next = rightRear;
        } else if (leftPrev == right) {//左节点是右结点的后继
            rightPrev.next = left;
            left.next = right;
            right.next = leftRear;
        } else {//普通情况
            //左节点的前驱指向右结点，右结点的后继指向原左节点的后继
            leftPrev.next = right;
            right.next = leftRear;
            //右结点的前驱指向左节点，左节点的后继指向原右结点的后继
            rightPrev.next = left;
            left.next = rightRear;
        }
        return hair.next;
    }
}
