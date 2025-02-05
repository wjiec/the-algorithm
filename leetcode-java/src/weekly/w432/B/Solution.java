package weekly.w432.B;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 3418. Maximum Amount of Money Robot Can Earn
 *
 * https://leetcode.cn/contest/weekly-contest-432/problems/maximum-amount-of-money-robot-can-earn/
 *
 * You are given an m x n grid. A robot starts at the top-left corner of the grid (0, 0) and wants
 * to reach the bottom-right corner (m - 1, n - 1). The robot can move either right or down at any point in time.
 *
 * The grid contains a value coins[i][j] in each cell:
 *
 * If coins[i][j] >= 0, the robot gains that many coins.
 *
 * If coins[i][j] < 0, the robot encounters a robber, and the robber steals the absolute value of coins[i][j] coins.
 *
 * The robot has a special ability to neutralize robbers in at most 2 cells on its path, preventing them
 * from stealing coins in those cells.
 *
 * Note: The robot's total coins can be negative.
 *
 * Return the maximum profit the robot can gain on the route.
 */

public class Solution {

    public int maximumAmount(int[][] coins) {
        int m = coins.length, n = coins[0].length;
        // [i, j, c, s]
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 0, coins[0][0]});
        queue.add(new int[]{0, 0, 1, Math.max(coins[0][0], 0)});
        queue.add(new int[]{0, 0, 2, Math.max(coins[0][0], 0)});

        int ans = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            var curr = queue.remove();
            int i = curr[0], j = curr[1], c = curr[2], s = curr[3];
            if (i == m - 1 && j == n - 1) ans = Math.max(ans, s);

            if (i + 1 < m) {
                queue.add(new int[]{i + 1, j, c, s + coins[i + 1][j]}); // op1
                if (coins[i + 1][j] < 0 && c + 1 <= 2) queue.add(new int[]{i + 1, j, c + 1, s}); // op2
            }

            if (j + 1 < n) {
                queue.add(new int[]{i, j + 1, c, s + coins[i][j + 1]}); // op1
                if (coins[i][j + 1] < 0 && c + 1 <= 2) queue.add(new int[]{i, j + 1, c + 1, s}); // op2
            }
        }

        return ans;
    }

    private static class DynamicProgramming {
        public int maximumAmount(int[][] coins) {
            int m = coins.length, n = coins[0].length;
            // dp[i][j][k] 表示在 coins[i][j] 位置时使用 k 次感化能力的最大值
            int[][][] dp = new int[m + 1][n + 1][3];
            for (var r : dp) for (var c : r) c[0] = c[1] = c[2] = Integer.MIN_VALUE / 2;
            dp[0][1][0] = dp[1][0][0] = 0;

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    int curr = coins[i - 1][j - 1];

                    dp[i][j][0] = curr + Math.max(dp[i - 1][j][0], dp[i][j - 1][0]);

                    dp[i][j][1] = curr + Math.max(dp[i - 1][j][1], dp[i][j - 1][1]);
                    dp[i][j][1] = Math.max(dp[i][j][1], Math.max(dp[i - 1][j][0], dp[i][j - 1][0]));

                    dp[i][j][2] = curr + Math.max(dp[i - 1][j][2], dp[i][j - 1][2]);
                    dp[i][j][2] = Math.max(dp[i][j][2], Math.max(dp[i - 1][j][1], dp[i][j - 1][1]));
                }
            }

            return Math.max(dp[m][n][0], Math.max(dp[m][n][1], dp[m][n][2]));
        }
    }

    public static void main(String[] args) {
        assert new DynamicProgramming().maximumAmount(new int[][]{
            {-7, 12,  12, 13},
            {-6, 19,  19, -6},
            { 9, -2, -10, 16},
            {-4, 14, -10, -9}
        }) == 60;
        assert new DynamicProgramming().maximumAmount(new int[][]{
            {0,  1, -1},
            {1, -2,  3},
            {2, -3,  4}
        }) == 8;
        assert new DynamicProgramming().maximumAmount(new int[][]{
            {10,10,10},
            {10,10,10}
        }) == 40;

        assert new Solution().maximumAmount(new int[][]{
            {-7, 12,  12, 13},
            {-6, 19,  19, -6},
            { 9, -2, -10, 16},
            {-4, 14, -10, -9}
        }) == 60;
        assert new Solution().maximumAmount(new int[][]{
            {0,  1, -1},
            {1, -2,  3},
            {2, -3,  4}
        }) == 8;
        assert new Solution().maximumAmount(new int[][]{{10,10,10},{10,10,10}}) == 40;
    }

}
