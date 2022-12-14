package problem.p72editdistance;

import common.Tag;

/**
 * 72. Edit Distance
 *
 * https://leetcode.cn/problems/edit-distance/
 *
 * Given two strings word1 and word2, return the minimum number of operations
 * required to convert word1 to word2.
 *
 * You have the following three operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 */

public class Solution {

    @Tag({"编辑距离"})
    public int minDistance(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();
        if (n1 == 0 || n2 == 0) return n1 + n2;

        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();

        // dp[i][j] 表示将 word1[0 ~ i) 转换为 word2[0 ~ j) 的最小操作次数
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) dp[i][0] = i;
        for (int j = 1; j <= n2; j++) dp[0][j] = j;

        for (int i = 1; i <= n1; i++) {
            char c1 = chars1[i - 1];
            for (int j = 1; j <= n2; j++) {
                int a = dp[i - 1][j] + 1;
                int b = dp[i][j - 1] + 1;
                int c = dp[i - 1][j - 1];
                if (chars2[j - 1] != c1) c += 1;

                dp[i][j] = Math.min(a, Math.min(b, c));
            }
        }

        return dp[n1][n2];
    }

    public static void main(String[] args) {
        assert new Solution().minDistance("horse", "ros") == 3;
        assert new Solution().minDistance("intention", "execution") == 5;
    }

}
