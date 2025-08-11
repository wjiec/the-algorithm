package weekly.bw160.B;

import java.util.Arrays;

/**
 * Q2. Minimum Cost Path with Alternating Directions II©leetcode
 *
 * https://leetcode.cn/contest/biweekly-contest-160/problems/minimum-cost-path-with-alternating-directions-ii/description/
 *
 * You are given two integers m and n representing the number of rows and columns of a grid, respectively.
 *
 * The cost to enter cell (i, j) is defined as (i + 1) * (j + 1).
 *
 * You are also given a 2D integer array waitCost where waitCost[i][j] defines the cost to wait on that cell.
 *
 * The path will always begin by entering cell (0, 0) on move 1 and paying the entrance cost.
 *
 * At each step, you follow an alternating pattern:
 *
 * On odd-numbered seconds, you must move right or down to an adjacent cell, paying its entry cost.
 * On even-numbered seconds, you must wait in place for exactly one second and pay waitCost[i][j] during that second.
 *
 * Return the minimum total cost required to reach (m - 1, n - 1).
 */

public class Solution {

    public long minCost(int m, int n, int[][] waitCost) {
        // 只能在奇数秒移动到右边或者下边, 且必须等待一秒 waitCost[i][j]
        long[][] dp = new long[m][n];
        for (var row : dp) Arrays.fill(row, Long.MAX_VALUE);
        dp[0][0] = 1;
        if (m > 1) dp[1][0] = dp[0][0] + 2; // i = 0, j = 1
        if (n > 1) dp[0][1] = dp[0][0] + 2; // i = 1, j = 0

        // 初始化, 必须要等待 waitCost[from_i][from_j] 才可以移动到 i, j
        for (int j = 2; j < n; j++) dp[0][j] = dp[0][j - 1] + waitCost[0][j - 1] + j + 1;
        for (int i = 2; i < m; i++) dp[i][0] = dp[i - 1][0] + waitCost[i - 1][0] + i + 1;

        // 接下来开始移动
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(
                    dp[i - 1][j] + waitCost[i - 1][j] + (i + 1L) * (j + 1L),
                    dp[i][j - 1] + waitCost[i][j - 1] + (i + 1L) * (j + 1L)
                );
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
    }

}
