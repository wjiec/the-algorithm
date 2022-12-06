package problem.p32longestvalidparentheses;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 32. Longest Valid Parentheses
 *
 * https://leetcode.cn/problems/longest-valid-parentheses/
 *
 * Given a string containing just the characters '(' and ')', return
 * the length of the longest valid (well-formed) parentheses substring.
 */

public class Solution {

    public int longestValidParentheses(String s) {
        int ans = 0;
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length];
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ')') {
                if (chars[i - 1] == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && chars[i - dp[i - 1] - 1] == '(') {
                    dp[i] = dp[i - 1] + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }

                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }

    private static class Stack {
        public int longestValidParentheses(String s) {
            int ans = 0;
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(-1);

            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == ')') {
                    stack.pop();
                    if (stack.isEmpty()) {
                        stack.push(i);
                    } else {
                        ans = Math.max(ans, i - stack.peek());
                    }
                } else stack.push(i);
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().longestValidParentheses("(()(((()") == 2;
        assert new Solution().longestValidParentheses("()(()") == 2;

        assert new Solution().longestValidParentheses("(()") == 2;
        assert new Solution().longestValidParentheses(")()())") == 4;
        assert new Solution().longestValidParentheses("") == 0;

        assert new Solution().longestValidParentheses(")))(((") == 0;
        assert new Solution().longestValidParentheses("((())))") == 6;
        assert new Solution().longestValidParentheses("()(()())") == 8;

        assert new Stack().longestValidParentheses("(()(((()") == 2;
        assert new Stack().longestValidParentheses("()(()") == 2;
        assert new Stack().longestValidParentheses("(()") == 2;
        assert new Stack().longestValidParentheses(")()())") == 4;
        assert new Stack().longestValidParentheses("") == 0;
        assert new Stack().longestValidParentheses(")))(((") == 0;
        assert new Stack().longestValidParentheses("((())))") == 6;
        assert new Stack().longestValidParentheses("()(()())") == 8;
    }

}
