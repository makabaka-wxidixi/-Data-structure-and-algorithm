package product_of_number;

import java.util.Stack;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-13 21:47
 */
public class ProductOfNumbers {
    /*
    写一个类，可以add数据，并且可以得到最后k个数据的乘积
     */

    /*
    方式一：栈+前缀积
     */
    class Demo1 {
        Stack<Integer> stack = new Stack<>();

        public Demo1() {
            stack.push(1);
        }

        public void add(int num) {
            if (num == 0) {
                stack.clear();//清空栈
                stack.push(1);
            } else {
                stack.push(stack.peek() * num);
            }
        }

        public int ProductOfNumbers1(int k) {
            if (k <= 0) {
                return 0;
            }
            if (k > stack.size() - 1) {//说明，k个数据中有0存在
                return 0;
            } else {
                if (stack.size() - 1 == k) {
                    return stack.peek();
                } else {
                    int temp;
                    int top = stack.peek();//栈顶元素
                    int[] arr = new int[k];
                    for (int i = 0; i < k; i++) {//将数据弹出，直至找到除数
                        arr[i] = stack.pop();
                    }
                    temp = stack.peek();
                    for (int i = k - 1; i >= 0; i--) {//将数据填回到栈中
                        stack.push(arr[i]);
                    }
                    return top / temp;
                }
            }
        }
    }

    /*
    方法二：链表
     */

    class Demo2 {

        public Demo2() {
        }

        //内部类
        class Node {
            int val;
            Node next;

            public Node(int val, Node next) {
                this.val = val;
                this.next = next;
            }
        }

        Node head = null;

        public void add(int num) {
            Node node = new Node(num, head);
            head = node;
        }

        public int ProductOfNumbers2(int k) {
            if (k <= 0) {
                return 0;
            }
            Node left = head, right = head;
            for (int i = 0; i < k; i++) {
                left = left.next;
            }
            int sum = 1;
            while (right != left) {
                sum *= right.val;
                right = right.next;
            }
            return sum;
        }
    }
}
