package minStack;

import java.util.Stack;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-10 20:52
 */
public class Demo1 {

    //******************************************测试*********************************************
    public static void main(String[] args) {

    }

    //***************************************单调找（一）*******************************************
    /*
    时间复杂度：O1
    空间复杂度：On
     */
    class MinStack1 {
        Stack<Integer> stack = null;
        Stack<Integer> minStack = null;

        public MinStack1() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || minStack.peek() >= val) {
                minStack.push(val);
            }
        }

        public void pop() {
            int temp = stack.pop();
            if (temp == minStack.peek()) {
                minStack.pop();
            }
        }

        public int getMin() {
            return minStack.peek();
        }

        public int top() {
            return stack.peek();
        }

    }


    //***************************************单调找（二）*******************************************
    /*
    时间复杂度：O1
    空间复杂度：On
     */
    class MinStack2 {
        Stack<Integer> stack = null;
        Stack<Integer> minStack = null;

        public MinStack2() {
            stack = new Stack<>();
            minStack = new Stack<>();
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int val) {
            stack.push(val);
            minStack.push(Math.min(minStack.peek(), val));
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }


    //***************************************单调找（三）*******************************************
    /*
    利用差值，实现最优解
    时间复杂度：O1
    空间复杂度：O1
     */
    class MinStack3 {
        Stack<Long> stack = new Stack<>();
        long min;

        public MinStack3() {
        }

        public void push(long val) {
            if (stack.isEmpty()) {
                stack.push(0L);//存放和min的差值
                min = val;
            } else {
                long compare = val - min;//该差值是和上一个min所形成的差值
                min = compare < 0 ? val : min;//如果小于min，就更新min
                stack.push(compare);
            }
        }

        public void pop() {
            long top = stack.pop();
            min = top < 0 ? min - top : min;
        }

        public int top() {
            //如果差值小于0，就说明更新之后的min就是原来的值；否则就说明min用的还是前一个min，那么就需要用前一个min加上差值
            return (int) (stack.peek() < 0 ? min : min + stack.peek());
        }

        public int getMin() {
            return (int) min;
        }
    }

}
