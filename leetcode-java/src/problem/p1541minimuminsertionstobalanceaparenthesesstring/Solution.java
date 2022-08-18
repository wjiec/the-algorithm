package problem.p1541minimuminsertionstobalanceaparenthesesstring;

/**
 * 1541. Minimum Insertions to Balance a Parentheses String
 *
 * https://leetcode.cn/problems/minimum-insertions-to-balance-a-parentheses-string/
 *
 * Given a parentheses string s containing only the characters '(' and ')'. A parentheses string is balanced if:
 *
 * Any left parenthesis '(' must have a corresponding two consecutive right parenthesis '))'.
 * Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))'.
 * In other words, we treat '(' as an opening parenthesis and '))' as a closing parenthesis.
 *
 * For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and "(()))" are not balanced.
 * You can insert the characters '(' and ')' at any position of the string to balance it if needed.
 *
 * Return the minimum number of insertions needed to make s balanced.
 */

public class Solution {

    public int minInsertions(String s) {
        int ans = 0, open = 0, close = 0;
        for (var c : s.toCharArray()) {
            if (c == ')') {
                if (++close == 2) {
                    if (open > 0) open--;
                    else ans++; // 前面插入 '('
                    close = 0;
                }
            } else {
                if (close != 0) {
                    ans += open == 0 ? 2 : 1; // 补一个 ')' 或 '('
                    close = 0;
                    if (open != 0) open--;
                }
                open++;
            }
        }

        if (open != 0) ans += open * 2 - close;
        else if (close != 0) ans += 2;

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minInsertions(")))()))))))((()))())))()))))()))()())((()()))()(())()())()()))))))()()((()))(") == 28;
        assert new Solution().minInsertions("()()()()()(") == 7;

        assert new Solution().minInsertions("(()))") == 1;
        assert new Solution().minInsertions("())") == 0;
        assert new Solution().minInsertions("))())(") == 3;
        assert new Solution().minInsertions("((((((") == 12;
        assert new Solution().minInsertions(")))))))") == 5;
    }

}
