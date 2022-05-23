package problem.p856scoreofparentheses;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 856. Score of Parentheses
 *
 * https://leetcode.cn/problems/score-of-parentheses/
 *
 * Given a balanced parentheses string s, return the score of the string.
 *
 * The score of a balanced parentheses string is based on the following rule:
 *
 * "()" has score 1.
 * AB has score A + B, where A and B are balanced parentheses strings.
 * (A) has score 2 * A, where A is a balanced parentheses string.
 */

public class Solution {

    public int scoreOfParentheses(String s) {
        return score(s.toCharArray(), 0, s.length());
    }

    private int score(char[] chars, int l, int r) {
        if (l >= r) return 0;

        int opens = 1, idx = l + 1;
        for (; opens != 0 && idx < r; idx++) {
            opens += chars[idx] == ')' ? -1 : 1;
        }

        int ans = 2 * score(chars, l + 1, idx - 1);
        if (ans == 0) ans = 1;
        if (idx < r) ans += score(chars, idx, r);

        return ans;
    }

    private static class Stack {
        public int scoreOfParentheses(String s) {
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(0);

            for (var c : s.toCharArray()) {
                if (c == ')') {
                    int curr = stack.pop();
                    int prev = stack.pop();
                    stack.push(prev + Math.max(2 * curr, 1));
                } else stack.push(0);
            }
            return stack.pop();
        }
    }

    public static void main(String[] args) {
        assert new Solution().scoreOfParentheses("()") == 1;
        assert new Solution().scoreOfParentheses("(())") == 2;
        assert new Solution().scoreOfParentheses("()()") == 2;
        assert new Solution().scoreOfParentheses("()()(())") == 4;
        assert new Solution().scoreOfParentheses("(()()(()))") == 8;

        assert new Stack().scoreOfParentheses("()") == 1;
        assert new Stack().scoreOfParentheses("(())") == 2;
        assert new Stack().scoreOfParentheses("()()") == 2;
        assert new Stack().scoreOfParentheses("()()(())") == 4;
        assert new Stack().scoreOfParentheses("(()()(()))") == 8;
    }

}
