package problem.p224basiccalculator;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 224. Basic Calculator
 *
 * https://leetcode.cn/problems/basic-calculator
 *
 * Given a string s representing a valid expression, implement a basic calculator to
 * evaluate it, and return the result of the evaluation.
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as
 * mathematical expressions, such as eval().
 */

public class Solution {

    public int calculate(String s) {
        Deque<Integer> ops = new ArrayDeque<>();
        ops.push(1);

        int sign = 1, ans = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            assert !ops.isEmpty();
            switch (s.charAt(i)) {
                case '+' -> sign = ops.peek();
                case '-' -> sign = -ops.peek();
                case '(' -> ops.push(sign);
                case ')' -> ops.pop();
                default -> {
                    if (s.charAt(i) == ' ') continue;

                    long number = 0;
                    while (i < n && Character.isDigit(s.charAt(i))) {
                        number = number * 10 + (s.charAt(i++) - '0');
                    }
                    ans += sign * number; i--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().calculate("1-(     -2)") == 3;
        assert new Solution().calculate("  -2  ") == -2;

        assert new Solution().calculate("1 + 1") == 2;
        assert new Solution().calculate(" 2-1 + 2 ") == 3;
        assert new Solution().calculate("(1+(4+5+2)-3)+(6+8)") == 23;
    }

}
