package problem.p1249minimumremovetomakevalidparentheses;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * 1249. Minimum Remove to Make Valid Parentheses
 *
 * https://leetcode.cn/problems/minimum-remove-to-make-valid-parentheses/
 *
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that
 * the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 */

public class Solution {

    public String minRemoveToMakeValid(String s) {
        Set<Integer> removes = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0, n = s.length(); i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') stack.push(i);
            if (c == ')' && stack.isEmpty()) removes.add(i);
            if (c == ')' && !stack.isEmpty()) stack.pop();
        }
        while (!stack.isEmpty()) removes.add(stack.pop());

        StringBuilder sb = new StringBuilder();
        for (int i = 0, n = s.length(); i < n; i++) {
            if (!removes.contains(i)) sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().minRemoveToMakeValid("())()(((").equals("()()");
        assert new Solution().minRemoveToMakeValid("(a(b(c)d)").equals("a(b(c)d)");
        assert new Solution().minRemoveToMakeValid("a(b)(c)(d").equals("a(b)(c)d");

        assert new Solution().minRemoveToMakeValid("lee(t(c)o)de)").equals("lee(t(c)o)de");
        assert new Solution().minRemoveToMakeValid("a)b(c)d").equals("ab(c)d");
        assert new Solution().minRemoveToMakeValid("))((").equals("");
    }

}
