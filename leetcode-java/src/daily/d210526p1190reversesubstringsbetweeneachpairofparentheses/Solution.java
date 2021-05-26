package daily.d210526p1190reversesubstringsbetweeneachpairofparentheses;

/**
 * 1190. Reverse Substrings Between Each Pair of Parentheses
 *
 * https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/
 *
 * You are given a string s that consists of lower case English letters and brackets. 
 *
 * Reverse the strings in each pair of matching parentheses, starting from the innermost one.
 *
 * Your result should not contain any brackets.
 */

public class Solution {

    private int idx = 0;

    public String reverseParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        for (idx = 0; idx < s.length(); idx++) {
            char c = s.charAt(idx);
            if (c >= 'a') {
                sb.append(c);
            } else if (c == '(') {
                sb.append(reverse(s));
            }
        }
        return sb.toString();
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        while (idx < s.length()) {
            char c = s.charAt(++idx);
            if (c >= 'a') {
                sb.append(c);
            } else if (c == '(') {
                sb.append(reverse(s));
            } else if (c == ')') {
                break;
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        assert new Solution().reverseParentheses("(abcd)").equals("dcba");
        assert new Solution().reverseParentheses("(u(love)i)").equals("iloveu");
        assert new Solution().reverseParentheses("(ed(et(oc))el)").equals("leetcode");
        assert new Solution().reverseParentheses("a(bcdefghijkl(mno)p)q").equals("apmnolkjihgfedcbq");
    }

}
