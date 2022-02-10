package problem.p10regularexpressionmatching;

/**
 * 10. Regular Expression Matching
 *
 * https://leetcode-cn.com/problems/regular-expression-matching/
 *
 * Given an input string (s) and a pattern (p),
 * implement regular expression matching with support for '.' and '*' where:
 *
 * '.' Matches any single character.​​​​
 * '*' Matches zero or more of the preceding element.
 *
 * The matching should cover the entire input string (not partial).
 */

public class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        char[] ss = s.toCharArray(), ps = p.toCharArray();

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (ps[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (isMatch(ss, ps, i, j - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    if (isMatch(ss, ps, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[m][n];
    }

    private boolean isMatch(char[] s, char[] p, int i, int j) {
        return i != 0 && (p[j - 1] == '.' || s[i - 1] == p[j - 1]);
    }

    public static void main(String[] args) {
        assert !new Solution().isMatch("aa", "a");
        assert new Solution().isMatch("aa", "a*");
        assert new Solution().isMatch("ab", ".*");
        assert new Solution().isMatch("aab", "c*a*b");
        assert !new Solution().isMatch("mississippi", "mis*is*p*.");
    }

}
