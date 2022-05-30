package problem.p921minimumaddtomakeparenthesesvalid;

/**
 * 921. Minimum Add to Make Parentheses Valid
 *
 * https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid/
 *
 * A parentheses string is valid if and only if:
 *
 * It is the empty string,
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * You are given a parentheses string s. In one move, you can insert a parenthesis
 * at any position of the string.
 *
 * For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or
 * a closing parenthesis to be "())))".
 *
 * Return the minimum number of moves required to make s valid.
 */

public class Solution {

    public int minAddToMakeValid(String s) {
        int ans = 0, opens = 0;
        for (int i = 0, n = s.length(); i < n; i++) {
            opens += s.charAt(i) == '(' ? 1 : -1;
            if (opens == -1) {
                ans++;
                opens++;
            }
        }
        return ans + opens;
    }

    public static void main(String[] args) {
        assert new Solution().minAddToMakeValid("()))((") == 4;
        assert new Solution().minAddToMakeValid("())") == 1;
        assert new Solution().minAddToMakeValid("(((") == 3;
    }

}
