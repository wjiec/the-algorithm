package problem.p1770maximumscorefromperformingmultiplicationoperations;

import common.Tag;

/**
 * 1770. Maximum Score from Performing Multiplication Operations
 *
 * https://leetcode.cn/problems/maximum-score-from-performing-multiplication-operations/
 *
 * You are given two integer arrays nums and multipliers of size n and m
 * respectively, where n >= m. The arrays are 1-indexed.
 *
 * You begin with a score of 0. You want to perform exactly m operations.
 * On the ith operation (1-indexed), you will:
 *
 * Choose one integer x from either the start or the end of the array nums.
 * Add multipliers[i] * x to your score.
 * Remove x from the array nums.
 *
 * Return the maximum score after performing m operations.
 */

public class Solution {

    @Tag({"数组前后取值", "暴力2^N复杂度"})
    public int maximumScore(int[] nums, int[] multipliers) {
        int m = multipliers.length, n = nums.length;

        // dp[i][j] 表示取前 i 个和后 j 个的最大分数
        int[][] dp = new int[m + 1][m + 1];
        for (int i = 1; i <= m; i++) dp[i][0] = dp[i - 1][0] + nums[i - 1] * multipliers[i - 1];
        for (int j = 1; j <= m; j++) dp[0][j] = dp[0][j - 1] + nums[n - j] * multipliers[j - 1];

        // dp[i][j] 可由 dp[i - 1][j] 和 dp[i][j - 1] 转移得来
        for (int i = 1; i <= m; i++) {
            for (int j = 1; i + j <= m; j++) {
                dp[i][j] = Math.max(
                    dp[i - 1][j] + nums[i - 1] * multipliers[i + j - 1],
                    dp[i][j - 1] + nums[n - j] * multipliers[i + j - 1]
                );
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i <= m; i++) ans = Math.max(ans, dp[i][m - i]);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumScore(new int[]{1,2,3}, new int[]{3,2,1}) == 14;
        assert new Solution().maximumScore(new int[]{-5,-3,-3,-2,7,1}, new int[]{-10,-5,3,4,6}) == 102;
    }

}
