package problem.p63uniquepathsii;

import java.util.Arrays;

/**
 * 63. Unique Paths II
 *
 * https://leetcode-cn.com/problems/unique-paths-ii/
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the
 * bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and space is marked as 1 and 0 respectively in the grid.
 */

public class Solution {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if (obstacleGrid[m - 1][n - 1] == 1) return 0;

        int[][] dp = new int[m][n];
        for (int i = 0; i < m && obstacleGrid[i][0] != 1; i++) dp[i][0] = 1;
        for (int i = 0; i < n && obstacleGrid[0][i] != 1; i++) dp[0][i] = 1;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        assert new Solution().uniquePathsWithObstacles(new int[][]{
            {0,0,0},
            {0,1,0},
            {0,0,0}
        }) == 2;

        assert new Solution().uniquePathsWithObstacles(new int[][]{
            {0,1},
            {0,0}
        }) == 1;
    }

}
