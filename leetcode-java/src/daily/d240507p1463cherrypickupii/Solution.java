package daily.d240507p1463cherrypickupii;

import java.util.Arrays;

/**
 * 1463. Cherry Pickup II
 *
 * https://leetcode.cn/problems/cherry-pickup-ii
 *
 * You are given a rows x cols matrix grid representing a field of cherries
 * where grid[i][j] represents the number of cherries that you can collect from the (i, j) cell.
 *
 * You have two robots that can collect cherries for you:
 *
 * Robot #1 is located at the top-left corner (0, 0), and
 * Robot #2 is located at the top-right corner (0, cols - 1).
 * Return the maximum number of cherries collection using both robots by following the rules below:
 *
 * From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
 * When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.
 * When both robots stay in the same cell, only one takes the cherries.
 * Both robots cannot move outside of the grid at any moment.
 * Both robots should reach the bottom row in grid.
 */

public class Solution {

    public int cherryPickup(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // dp[i][j][k] 表示当前在第 i 行且 机器人分别位于 j, k 时的最大值
        int[][][] dp = new int[m][n + 2][n + 2];
        for (var a : dp) for (var b : a) Arrays.fill(b, -1);
        dp[0][1][n] = grid[0][0] + grid[0][n - 1];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (j == k) continue;

                    int curr = max(
                        dp[i - 1][j][k], dp[i - 1][j][k - 1], dp[i - 1][j][k + 1],
                        dp[i - 1][j - 1][k], dp[i - 1][j - 1][k - 1], dp[i - 1][j - 1][k + 1],
                        dp[i - 1][j + 1][k], dp[i - 1][j + 1][k - 1], dp[i - 1][j + 1][k + 1]
                    );

                    // 可以从前一个转移到当前的 [j][k] 上
                    if (curr >= 0) {
                        dp[i][j][k] = max(dp[i][j][k], curr + grid[i][j - 1] + grid[i][k - 1]);
                    }
                }
            }
        }

        int ans = 0;
        for (var rows : dp[m - 1]) for (var v : rows) ans = max(ans, v);
        return ans;
    }

    private int max(int ...values) {
        int ans = values[0];
        for (var v : values) ans = Math.max(ans, v);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().cherryPickup(new int[][]{
            {0, 8,  7, 10, 9, 10, 0, 9,  6},
            {8, 7, 10,  8, 7,  4, 9, 6, 10},
            {8, 1,  1,  5, 1,  5, 5, 1,  2},
            {9, 4, 10,  8, 8,  1, 9, 5,  0},
            {4, 3,  6, 10, 9,  2, 4, 8, 10},
            {7, 3,  2,  8, 3,  3, 5, 9,  8},
            {1, 2,  6,  5, 6,  2, 0, 10, 0}
        }) == 96;

        assert new Solution().cherryPickup(new int[][]{{3,1,1},{2,5,1},{1,5,5},{2,1,1}}) == 24;
        assert new Solution().cherryPickup(new int[][]{{1,0,0,0,0,0,1},{2,0,0,0,0,3,0},{2,0,9,0,0,0,0},{0,3,0,5,4,0,0},{1,0,2,3,0,0,6}}) == 28;
    }

}
