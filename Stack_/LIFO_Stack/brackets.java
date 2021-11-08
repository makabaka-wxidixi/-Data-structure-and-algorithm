package LIFO_Stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-08 20:44
 */
public class brackets {
    //**************************************测试函数******************************************
    public static void main(String[] args) {
        brackets b = new brackets();
        String s = "()()(){}}{}[]";
        System.out.println(b.isValid3(s));
    }

    //***********************************有效的括号**************************************

    /**
     * 方式一：栈
     * 时间复杂度：On
     * 空间复杂度：On
     *
     * @param s 输入的字符串
     * @return 是否有效
     */
    public boolean isValid(String s) {
        if (s.length() % 2 == 1) {//奇数肯定不匹配
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {//左括号就入栈
                stack.push(ch);
            } else {
                if (ch == ')') {
                    if (!stack.isEmpty() && stack.peek() == '(')
                        stack.pop();
                    else
                        return false;
                } else if (ch == ']') {
                    if (!stack.isEmpty() && stack.peek() == '[')
                        stack.pop();
                    else
                        return false;
                } else {
                    if (!stack.isEmpty() && stack.peek() == '{')
                        stack.pop();
                    else
                        return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 方式二：栈、哈希
     * 时间复杂度：On
     * 空间复杂度：On
     *
     * @param s 输入的字符串
     * @return 是否有效
     */
    public boolean isValid2(String s) {
        if (s.length() % 2 == 1) return false;//奇数
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {//包含键
                if (stack.isEmpty() || stack.peek() != map.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 方式三：API——效率很差，不推荐。提供一个思路
     *
     * @param s 输入的字符串
     * @return 是否有效
     */
    public boolean isValid3(String s) {
        if (s.length() % 2 == 1)
            return false;
        while (s.contains("()") || s.contains("[]") || s.contains("{}")) {
            if (s.contains("()")) {
                s = s.replace("()", "");
            } else if (s.contains("{}")) {
                s = s.replace("{}", "");
            } else {
                s = s.replace("[]", "");
            }
        }
        return s.length() == 0;
    }

    //************************************最长有效括号****************************************

    /**
     * 方式一：栈
     * 时间复杂度：On
     * 空间复杂度：On
     * @param s 输入的字符串
     * @return 长度
     */
    public int longestValidBrackets(String s) {
        if (s.length() == 0 || s.length() == 1) return 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int max = 0;
        char ch = ' ';
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }
}
