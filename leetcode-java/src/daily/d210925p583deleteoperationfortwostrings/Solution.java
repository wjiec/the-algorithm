package daily.d210925p583deleteoperationfortwostrings;

/**
 * 583. Delete Operation for Two Strings
 *
 * https://leetcode-cn.com/problems/delete-operation-for-two-strings/
 *
 * Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
 *
 * In one step, you can delete exactly one character in either string.
 */

public class Solution {

    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                if (c == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else  {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return m + n - 2 * dp[m][n];
    }

    public static void main(String[] args) {
        assert new Solution().minDistance("sea", "eat") == 2;
        assert new Solution().minDistance("leetcode", "etco") == 4;
    }

}
