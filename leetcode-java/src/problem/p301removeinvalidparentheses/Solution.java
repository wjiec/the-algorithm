package problem.p301removeinvalidparentheses;

import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * 301. Remove Invalid Parentheses
 *
 * https://leetcode.cn/problems/remove-invalid-parentheses/description/
 *
 * Given a string s that contains parentheses and letters, remove the minimum
 * number of invalid parentheses to make the input string valid.
 *
 * Return a list of unique strings that are valid with the minimum number of removals.
 *
 * You may return the answer in any order.
 */

public class Solution {

    public List<String> removeInvalidParentheses(String s) {
        int lRemove = 0, rRemove = 0;
        for (var c : s.toCharArray()) {
            if (c == '(') lRemove++;
            if (c == ')') {
                if (lRemove == 0) rRemove++;
                else lRemove--;
            }
        }

        if (lRemove + rRemove == s.length()) {
            return List.of("");
        }

        backtrace(s.toCharArray(), 0, lRemove, rRemove);
        return ans;
    }

    private final List<String> ans = new ArrayList<>();

    private void backtrace(char[] chars, int i, int lRemove, int rRemove) {
        if (lRemove == 0 && rRemove == 0) {
            if (isValid(chars)) {
                StringBuilder sb = new StringBuilder();
                for (var c : chars) {
                    if (c != ' ') sb.append(c);
                }
                ans.add(sb.toString());
            }
            return;
        }

        for (; i + lRemove + rRemove <= chars.length; i++) {
            if (i != 0 && chars[i] == chars[i - 1]) continue;

            if (lRemove > 0 && chars[i] == '(') {
                chars[i] = ' ';
                backtrace(chars, i + 1, lRemove - 1, rRemove);
                chars[i] = '(';
            }
            if (rRemove > 0 && chars[i] == ')') {
                chars[i] = ' ';
                backtrace(chars, i + 1, lRemove, rRemove - 1);
                chars[i] = ')';
            }
        }
    }

    private boolean isValid(char[] chars) {
        int opens = 0;
        for (var c : chars) {
            if (c == '(') opens++;
            if (c == ')') opens--;
            if (opens < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().removeInvalidParentheses("x("));

        PrettyPrinter.println(new Solution().removeInvalidParentheses("()())()"));
        PrettyPrinter.println(new Solution().removeInvalidParentheses("(a)())()"));
        PrettyPrinter.println(new Solution().removeInvalidParentheses(")("));
    }

}
