package problem.p20validparentheses;

import java.util.Stack;

/**
 * 20. Valid Parentheses
 *
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 */

public class Solution {

    public boolean isValid(String s) {
        Stack<Character> match = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                match.add(c);
            } else if (match.size() != 0) {
                char v = match.pop();
                if ((c == ')' && v != '(') || (c == ']' && v != '[') || (c == '}' && v != '{')) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return match.size() == 0;
    }

    public static void main(String[] args) {
        assert !new Solution().isValid("))))))");
        assert !new Solution().isValid("(((((((((((((((((((())");
        assert new Solution().isValid("");
        assert new Solution().isValid("(({{}})){}{}(())(())");
    }

}
