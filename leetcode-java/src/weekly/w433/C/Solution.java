package weekly.w433.C;

/**
 * 3429. Paint House IV
 *
 * https://leetcode.cn/contest/weekly-contest-433/problems/paint-house-iv/
 *
 * You are given an even integer n representing the number of houses arranged in a straight
 * line, and a 2D array cost of size n x 3, where cost[i][j] represents the cost of painting
 * house i with color j + 1.
 *
 * The houses will look beautiful if they satisfy the following conditions:
 *
 * No two adjacent houses are painted the same color.
 * Houses equidistant from the ends of the row are not painted the same color.
 * For example, if n = 6, houses at positions (0, 5), (1, 4), and (2, 3) are considered equidistant.
 *
 * Return the minimum cost to paint the houses such that they look beautiful.
 */

/** @noinspection DuplicatedCode*/
public class Solution {

    private static final int RG = 0, RB = 1, GR = 2, GB = 3, BR = 4, BG = 5;

    public long minCost(int n, int[][] cost) {
        // (i, n - i - 1) 的颜色不能相同, 所以先将所有可能的配对全枚举出来
        // 相当于一共有 n / 2 个房子, 每个房子有 6 种涂色方案
        int[][] houses = new int[n / 2][6];
        for (int i = 0; i < n / 2; i++) houses[i] = colors(cost[i], cost[n - i - 1]);

        // dp[i][j] 表示第 i 组房子使用 j 种涂色方案的最低成本是多少
        long[][] dp = new long[n / 2 + 1][6];
        for (int i = 1; i <= n / 2; i++) {
            dp[i][RG] = houses[i - 1][RG] + min(dp[i - 1][GR], dp[i - 1][GB], dp[i - 1][BR]);
            dp[i][RB] = houses[i - 1][RB] + min(dp[i - 1][GR], dp[i - 1][BR], dp[i - 1][BG]);
            dp[i][GR] = houses[i - 1][GR] + min(dp[i - 1][RG], dp[i - 1][RB], dp[i - 1][BG]);
            dp[i][GB] = houses[i - 1][GB] + min(dp[i - 1][RG], dp[i - 1][BR], dp[i - 1][BG]);
            dp[i][BR] = houses[i - 1][BR] + min(dp[i - 1][RG], dp[i - 1][RB], dp[i - 1][GB]);
            dp[i][BG] = houses[i - 1][BG] + min(dp[i - 1][RB], dp[i - 1][GR], dp[i - 1][GB]);
        }

        return min(dp[n / 2][RG], dp[n / 2][RB], dp[n / 2][GR], dp[n / 2][GB], dp[n / 2][BR], dp[n / 2][BG]);
    }

    private long min(long... values) {
        long ans = values[0];
        for (int i = 1; i < values.length; i++) {
            ans = Math.min(ans, values[i]);
        }
        return ans;
    }

    private int[] colors(int[] l, int[] r) {
        return new int[]{
            l[0] + r[1], l[0] + r[2],
            l[1] + r[0], l[1] + r[2],
            l[2] + r[0], l[2] + r[1]
        };
    }

    public static void main(String[] args) {
    }

}
