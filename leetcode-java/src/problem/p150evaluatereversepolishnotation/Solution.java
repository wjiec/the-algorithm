package problem.p150evaluatereversepolishnotation;

import java.util.Stack;

/**
 * 150. Evaluate Reverse Polish Notation
 *
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 *
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
 *
 * Note that division between two integers should truncate toward zero.
 *
 * It is guaranteed that the given RPN expression is always valid.
 *
 * That means the expression would always evaluate to a result,
 * and there will not be any division by zero operation.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            char op = token.charAt(0);
            if (('0' <= op && op <= '9') || (op == '-' && token.length() != 1)) {
                stack.push(Integer.parseInt(token));
            } else {
                int r = stack.pop(), l = stack.pop();
                switch (op) {
                    case '+' -> stack.push(l + r);
                    case '-' -> stack.push(l - r);
                    case '*' -> stack.push(l * r);
                    case '/' -> stack.push(l / r);
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        assert new Solution().evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}) == 22;

        assert new Solution().evalRPN(new String[]{"2","1","+","3","*"}) == 9;
        assert new Solution().evalRPN(new String[]{"4","13","5","/","+"}) == 6;
        assert new Solution().evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}) == 22;
    }

}
