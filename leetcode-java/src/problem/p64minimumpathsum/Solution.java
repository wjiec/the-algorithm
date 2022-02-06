package problem.p64minimumpathsum;

import java.util.Arrays;

/**
 * 64. Minimum Path Sum
 *
 * https://leetcode-cn.com/problems/minimum-path-sum/
 *
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
 * which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 */

public class Solution {

    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int[][] dp = new int[m][n]; dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];
        for (int i = 1; i < n; i++) dp[0][i] = dp[0][i - 1] + grid[0][i];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        assert new Solution().minPathSum(new int[][]{
            {1,3,1},
            {1,5,1},
            {4,2,1}
        }) == 7;

        assert new Solution().minPathSum(new int[][]{
            {1,2,3},
            {4,5,6},
        }) == 12;
    }

}
