package problem.p1021removeoutermostparentheses;

/**
 * 1021. Remove Outermost Parentheses
 *
 * https://leetcode-cn.com/problems/remove-outermost-parentheses/
 *
 * A valid parentheses string is either empty "", "(" + A + ")", or A + B,
 * where A and B are valid parentheses strings, and + represents string concatenation.
 *
 * For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
 * A valid parentheses string s is primitive if it is nonempty, and there does not exist
 * a way to split it into s = A + B, with A and B nonempty valid parentheses strings.
 *
 * Given a valid parentheses string s, consider its primitive decomposition: s = P1 + P2 + ... + Pk,
 * where Pi are primitive valid parentheses strings.
 *
 * Return s after removing the outermost parentheses of every primitive string in the primitive decomposition of s.
 */

public class Solution {

    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int start = 0, l = s.length(), opens = 0;
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) == '(') {
                if (start == 0) start = i + 1;
                opens++;
            } else {
                if (--opens == 0) {
                    sb.append(s, start, i);
                    start = 0;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().removeOuterParentheses("(()())(())").equals("()()()");
        assert new Solution().removeOuterParentheses("(()())(())(()(()))").equals("()()()()(())");
        assert new Solution().removeOuterParentheses("()()").equals("");
    }

}
