package problem.p712minimumasciideletesumfortwostrings;

/**
 * 712. Minimum ASCII Delete Sum for Two Strings
 *
 * https://leetcode-cn.com/problems/minimum-ascii-delete-sum-for-two-strings/
 *
 * Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.
 */

public class Solution {

    public int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = s1.length() - 1; i >= 0; i--) {
            dp[i][s2.length()] = dp[i + 1][s2.length()] + s1.charAt(i);
        }
        for (int i = s2.length() - 1; i >= 0; i--) {
            dp[s1.length()][i] = dp[s1.length()][i + 1] + s2.charAt(i);
        }

        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j] + s1.charAt(i), dp[i][j + 1] + s2.charAt(j));
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        assert new Solution().minimumDeleteSum("sea", "eat") == 231;
        assert new Solution().minimumDeleteSum("delete", "leet") == 403;
    }

}
