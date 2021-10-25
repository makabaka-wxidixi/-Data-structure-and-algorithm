package single;


/**
 * @Author mkbk
 * @Description
 * @Create 2021-10-20 19:28
 */
public class SingleLinkedList {
    Node head = null;//头结点
    Node tailNode = head;//用于记录尾部结点，便于尾插法
    int numOfNode = 0;

    //内部节点类
    public class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "[" +
                    "val=" + val +
                    "]-->";
        }
    }

    //增(头插法)
    public void addFromHead(int val) {
        //要插入的结点
        Node newNode = new Node(val);
        if (isEmpty()) {//如果是链表为空，直接用头结点连接
            head = newNode;//头结点
            tailNode = newNode;//指向尾部结点
            numOfNode++;
            return;
        }
        newNode.next = head;
        head = newNode;
        numOfNode++;
    }

    //增(尾插法)
    public void addFromTail(int val) {
        Node newNode = new Node(val);
        if (isEmpty()) {//如果为空，那就直接用head连接
            head = newNode;//头结点
            tailNode = newNode;//跟踪尾结点
            numOfNode++;
            return;
        }
        tailNode.next = newNode;//尾结点连接
        tailNode = newNode;//更新尾结点
        numOfNode++;
    }

    //删
    public void delete(int val) {
        if (isEmpty())//链表为空直接返回
            return;
        Node rear = contains(val);
        if (rear == null)//rear等于null，就说明没有找到
            return;
        if (rear.next == tailNode)//要删除的结点如果是尾结点，那么就需要更新尾结点
            tailNode = rear;
        rear.next = rear.next.next;//前驱结点直接指向后继节点
        numOfNode--;
        delete(val);//递归删除重复结点
    }

    //查
    public void find(int val) {
        if (isEmpty())
            return;
        Node rear = contains(val);
        if (rear == null)
            return;
        while (rear.next != null) {//找到第一个指定结点，之后循环查找是否还有重复值
            if (rear.next.val == val)
                System.out.println(rear.next.toString());
            rear = rear.next;
        }
    }

    //修改
    public void modify(int oldVal, int newVal) {
        if (isEmpty())
            return;
        Node rear = contains(oldVal);
        if (rear == null)//说明没有指定结点
            return;
        rear.next.val = newVal;//更改新值
        modify(oldVal, newVal);//递归更改重复数据
    }

    //遍历
    public void orderList() {
        if (head == null)
            return;
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.toString());
            curr = curr.next;
        }
    }

    //判断链表是否为空
    public boolean isEmpty() {
        if (head == null)
            return true;
        return false;
    }

    //查找指定位置的结点
    public Node getNodeByIndex(int index) {
        if (isEmpty() || index + 1 > numOfNode)//链表为空
            return null;
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    //返回链表长度
    public int size() {
        return numOfNode;
    }

    //反转链表
    public void reversalList(Node head) {
        if (head == null || head.next == null)
            return ;
        Node rear = null, curr = head, tail = curr.next;
        while (tail != null) {
            curr.next = rear;
            rear = curr;
            curr = tail;
            tail = tail.next;
        }
        curr.next = rear;
        this.head = curr;
    }

    /**
     * 返回目标节点
     *
     * @param val 目标值
     * @return 找到就返回目标节点的前驱结点。找不到就返回null
     */
    public Node contains(int val) {
        Node curr = head;//指向头结点
        while (curr != null) {//curr只要不为null，就循环寻找
            if (curr.val == val)
                return curr;
            curr = curr.next;
        }
        //curr为null的时候都没有返回，就说明没有要找的结点
        return null;
    }

    /**
     * 链表向右旋转
     *
     * @param step 向右旋转的步数
     */
    public void rightRotate(int step) {
        if (isEmpty() || head.next == null)//链表为空或者只有一个结点
            return;
        Node Head = head, curr = Head;
        int len = 0;//链表长度
        while (curr != null) {
            ++len;
            curr = curr.next;
        }
        step %= len;//右移的步数
        if (step == 0) {//说明不用移动链表
            return;
        }
        curr = Head;
        for (int i = 1; i < len - step; i++) {
            curr = curr.next;
        }
        //curr指向新的尾部
        Node newNode = curr.next;//新的头结点
        curr.next = null;//新的尾结点后继节点置为null
        curr = newNode;//找到链表最后的节点，再将该节点连到原始头结点上
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = Head;//链表最后的结点指向头结点
        head = newNode;
    }

    /**
     * 链表向左旋转
     *
     * @param step 左旋转的步数
     */
    public void leftRotate(int step) {
        if (isEmpty() || head.next == null) //链表为空或者只有一个结点
            return;
        int len = 0;
        Node Head = head, curr = Head;
        while (curr != null) {
            ++len;
            curr = curr.next;
        }
        step %= len;
        if (step == 0)
            return;
        curr = Head;
        for (int i = 1; i < step; i++) {
            curr = curr.next;
        }
        //curr指向新链表的尾结点，curr的后继节点就是新的首元结点
        Node newHead = curr.next;//新的首元结点
        curr.next = null;//尾结点next域置空
        curr = newHead;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = Head;
        head = newHead;
    }

}




