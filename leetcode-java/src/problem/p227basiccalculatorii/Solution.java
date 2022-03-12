package problem.p227basiccalculatorii;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 227. Basic Calculator II
 *
 * https://leetcode-cn.com/problems/basic-calculator-ii/
 *
 * Given a string s which represents an expression, evaluate this expression and return its value.  
 *
 * The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid.
 * All intermediate results will be in the range of [-231, 231 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates
 * strings as mathematical expressions, such as eval().
 */

public class Solution {

    public int calculate(String s) {
        char prev = '+'; int val = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (var c : s.toCharArray()) {
            if ('0' <= c && c <= '9') {
                val = val * 10 + (c - '0');
            } else if (c != ' ') {
                switch (prev) {
                    case '+' -> stack.push(val);
                    case '-' -> stack.push(-val);
                    case '*' -> stack.push(stack.pop() * val);
                    case '/' -> stack.push(stack.pop() / val);
                }

                prev = c; val = 0;
            }
        }

        switch (prev) {
            case '+' -> stack.push(val);
            case '-' -> stack.push(-val);
            case '*' -> stack.push(stack.pop() * val);
            case '/' -> stack.push(stack.pop() / val);
        }

        int ans = 0;
        while (!stack.isEmpty()) ans += stack.pop();
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().calculate("3+2*2") == 7;
        assert new Solution().calculate(" 3/2 ") == 1;
        assert new Solution().calculate(" 3+5 / 2 ") == 5;
    }

}
