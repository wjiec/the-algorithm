package problem.p439ternaryexpressionparser;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 439. Ternary Expression Parser
 *
 * https://leetcode.cn/problems/ternary-expression-parser/
 *
 * Given a string expression representing arbitrarily nested ternary
 * expressions, evaluate the expression, and return the result of it.
 *
 * You can always assume that the given expression is valid and only contains
 * digits, '?', ':', 'T', and 'F' where 'T' is true and 'F' is false.
 *
 * All the numbers in the expression are one-digit numbers (i.e., in the range [0, 9]).
 *
 * The conditional expressions group right-to-left (as usual in most languages), and the
 * result of the expression will always evaluate to either a digit, 'T' or 'F'.
 */

public class Solution {

    private int idx = 0;
    private char[] chars;

    public String parseTernary(String expression) {
        chars = expression.toCharArray();
        return String.valueOf(parseTernary());
    }

    private char parseTernary() {
        char flag = chars[idx++];
        idx++; // skip ?
        char armT = parseTrue();
        idx++; // skip :
        char armF = parseFalse();
        return flag == 'T' ? armT : armF;
    }

    private char parseTrue() {
        if (chars[idx + 1] == ':') {
            return chars[idx++];
        }
        return parseTernary();
    }

    private char parseFalse() {
        if (idx + 1 == chars.length || chars[idx + 1] == ':') {
            return chars[idx++];
        }
        return parseTernary();
    }

    private static class Foreach {
        public String parseTernary(String expression) {
            char[] chars = expression.toCharArray();
            Deque<Character> stack = new ArrayDeque<>();
            for (int i = chars.length - 1; i >= 0; i--) {
                if (chars[i] == '?') {
                    char top = stack.pop();
                    if (chars[--i] == 'T') {
                        stack.pop();
                        stack.push(top);
                    }
                } else if (chars[i] != ':') {
                    stack.push(chars[i]);
                }
            }
            return String.valueOf(stack.pop());
        }
    }

    public static void main(String[] args) {
        assert new Solution().parseTernary("T?2:3").equals("2");
        assert new Solution().parseTernary("F?1:T?4:5").equals("4");
        assert new Solution().parseTernary("T?T?F:5:3").equals("F");

        assert new Foreach().parseTernary("T?2:3").equals("2");
        assert new Foreach().parseTernary("F?1:T?4:5").equals("4");
        assert new Foreach().parseTernary("T?T?F:5:3").equals("F");
    }

}
