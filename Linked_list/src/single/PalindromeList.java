package single;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
     * 判断链表是否回文
     * 方式二：利用递归，左指针指针指向链表头部，右指针递归找到链表尾部。然后回溯判断回文
     * 时间复杂度：On
     * 空间复杂度：On由于用到递归，会使用堆栈帧
     *
     * @param head 传入的链表头部
     * @return 是回文返回true，否则返回false
     */
    public boolean isPalindrome2(Node head) {
        leftPointer = head;
        return recursion(head.next);
    }

    private Node leftPointer;

    private boolean recursion(Node rightPointer) {
        if (rightPointer != null) {//递归找到链表尾部
            if (!recursion(rightPointer.next)) {//如果后面的结点不匹配，就直接返回false
                return false;
            }
            if (rightPointer.val != leftPointer.val) {//内容不匹配就返回false
                return false;
            }
            leftPointer = leftPointer.next;//左指针配合右指针（回溯移动）右移
        }
        return true;
    }


    /**
     * 判断是否回文
     * 方式一：将链表存入集合中，用左右指针来扫描判断
     * 时间复杂度：On
     * 空间复杂度：On
     *
     * @param head 传入的链表头部
     * @return 是回文返回true，否则返回false
     */
    public boolean isPalindrome1(Node head) {
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

    /**
     * 方式三：栈：链表前半段入栈，和后半段比较
     * 时间复杂度：On
     * 空间复杂度：On
     *
     * @param head 传入的链表头部
     * @return 是回文返回true，否则返回false
     */
    public boolean isPalindrome3(Node head) {
        Stack<Integer> stack = new Stack<>();
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        Node newHead;
        int max = stack.size();
        if (fast.next == null) {//结点个数为奇数
            newHead = slow.next;
            for (int i = 0; i < max; i++) {
                if (stack.peek() == newHead.val) {
                    stack.pop();
                    newHead = newHead.next;
                } else {
                    return false;
                }
            }
        } else {//偶数
            stack.push(slow.val);
            newHead = slow.next;
            for (int i = 0; i < max + 1; i++) {
                if (stack.peek() == newHead.val) {
                    stack.pop();
                    newHead = newHead.next;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 方式四：反转链表
     * 时间复杂度：On
     * 空间复杂度：O1
     *
     * @param head 传入的链表头部
     * @return 是回文返回true，否则返回false
     */
    public boolean isPalindrom4(Node head) {
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        Node curr = head, Rcurr, Rhead;
        if (fast.next == null) {//结点是奇数
            Rhead = reverse(slow.next);
            Rcurr = Rhead;
            while (Rcurr != null) {
                if (curr.val != Rcurr.val) {
                    Rhead = reverse(Rhead);
                    slow.next = Rhead;
                    return false;
                } else {
                    curr = curr.next;
                    Rcurr = Rcurr.next;
                }
            }
        } else {//结点是偶数
            Rhead = reverse(slow.next);
            Rcurr = Rhead;
            while (Rcurr != null) {
                if (curr.val != Rcurr.val) {
                    Rhead = reverse(Rhead);
                    slow.next = Rhead;
                    return false;
                } else {
                    curr = curr.next;
                    Rcurr = Rcurr.next;
                }
            }
        }
        Rhead = reverse(Rhead);
        slow.next = Rhead;
        return true;
    }

    //翻转链表
    private Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node prev = null, curr = head, rear = head.next;
        while (rear != null) {
            curr.next = prev;
            prev = curr;
            curr = rear;
            rear = rear.next;
        }
        curr.next = prev;
        return curr;
    }
}
