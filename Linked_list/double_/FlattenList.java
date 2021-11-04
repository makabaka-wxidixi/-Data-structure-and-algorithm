package double_;

import single.DeleteNode;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-04 19:40
 */
public class FlattenList {
    public Node head;
    public Node tail;

    public class Node {
        public int val;
        public Node next;
        public Node prev;
        public Node child;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        public Node(int val, Node next, Node prev, Node child) {
            this.val = val;
            this.next = next;
            this.prev = prev;
            this.child = child;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }

    //***************************************以下是测试函数*******************************************

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
        FlattenList list = new FlattenList();
        Node node1 = list.new Node(1);
        Node node2 = list.new Node(2);
        Node node3 = list.new Node(3);
        Node node4 = list.new Node(4);
        Node node5 = list.new Node(5);
        Node node6 = list.new Node(6);
        Node node7 = list.new Node(7);
        Node node8 = list.new Node(8);
        Node node11 = list.new Node(11);
        Node node12 = list.new Node(12);
        node1.prev = null;
        node1.next = node2;
        node2.prev = node1;
        node2.next = node3;
        node3.prev = node2;
        node3.next = node4;
        node3.child = node7;
        node4.prev = node3;
        node4.next = node5;
        node5.prev = node4;
        node5.next = node6;
        node6.prev = node5;
        node6.next = null;
        node7.prev = node3;
        node7.next = node8;
        node8.prev = node7;
        node8.next = null;
        node8.child = node11;
        node11.prev = node7;
        node11.next = node12;
        node12.prev = node11;
        node12.next = null;

        Node flatten = list.flatten(node1);
        list.list(flatten);
    }

    //***************************************多级链表扁平化********************************************

    /**
     * 方式一：递归
     * 时间复杂度：On（遍历链表一遍）
     * 空间复杂度：On（栈帧）
     *
     * @param head 传入的头结点
     * @return 返回修改之后的链表
     */
    public Node flatten(Node head) {
        return head == null ? null : flatten1(head);
    }

    private Node temp = null;

    private Node flatten1(Node head) {
        Node rear = head.next;//主链表后继节点
        boolean flag = false;
        if (head.child != null) {//当前节点有副链表
            flag = true;
            Node Child = head.child;
            head.next = flatten1(head.child);//当前节点连接到副链表头结点
            head.child = null;
            Child.prev = head;
        }
        if (rear == null) {//当前节点是主表中的最后一个节点
            if (rear == head.next) {//说明当前节点没有副链表
                temp = head;//记录当前节点，temp指向字表的最后一个节点
            }
            return head;
        }
        if (flag) {//如果有副表，那么就应该用子链表的最后一个节点连接到当前节点的后继节点
            rear.prev = temp;
            temp.next = flatten1(rear);
        } else {
            rear.prev = head;
            head.next = flatten1(rear);
        }
        return head;
    }

}
