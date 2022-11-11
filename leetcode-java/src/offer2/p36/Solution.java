package offer2.p36;

import java.util.ArrayDeque;

/**
 * 剑指 Offer II 036. 后缀表达式
 *
 * https://leetcode.cn/problems/8Zf90G/
 *
 * 根据 逆波兰表示法，求该后缀表达式的计算结果。
 *
 * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 说明：
 *
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int evalRPN(String[] tokens) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (var token : tokens) {
            switch (token.charAt(token.length() - 1)) {
                case '+' -> stack.push(stack.pop() + stack.pop());
                case '*' -> stack.push(stack.pop() * stack.pop());
                case '-' -> {
                    int b = stack.pop();
                    stack.push(stack.pop() - b);
                }
                case '/' -> {
                    int b = stack.pop();
                    stack.push(stack.pop() / b);
                }
                default -> stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        assert new Solution().evalRPN(new String[]{"2","1","+","3","*"}) == 9;
        assert new Solution().evalRPN(new String[]{"4","13","5","/","+"}) == 6;
        assert new Solution().evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}) == 22;
    }

}
