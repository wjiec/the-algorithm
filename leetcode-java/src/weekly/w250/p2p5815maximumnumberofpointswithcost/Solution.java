package weekly.w250.p2p5815maximumnumberofpointswithcost;

import java.util.Arrays;

/**
 * 5815. Maximum Number of Points with Cost
 *
 * https://leetcode-cn.com/contest/weekly-contest-250/problems/maximum-number-of-points-with-cost/
 *
 * You are given an m x n integer matrix points (0-indexed). Starting with 0 points,
 * you want to maximize the number of points you can get from the matrix.
 *
 * To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c)
 * will add points[r][c] to your score.
 *
 * However, you will lose points if you pick a cell too far from the cell that you picked in the previous row.
 * For every two adjacent rows r and r + 1 (where 0 <= r < m - 1),
 * picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.
 *
 * Return the maximum number of points you can achieve.
 */

public class Solution {

    // out of time
    public long maxPoints(int[][] points) {
        int m = points.length, n = points[0].length;

        long[][] dp = new long[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                long val = points[i - 1][j - 1], max = dp[i - 1][j];
                for (int k = 0; k < val; k++) {
                    if (j - k >= 0) max = Math.max(max, val + dp[i - 1][j - k] - k);
                    if (j + k <= n) max = Math.max(max, val + dp[i - 1][j + k] - k);
                }
                dp[i][j] = max;
            }
        }

        System.out.println(Arrays.deepToString(dp));

        long ans = 0;
        for (int i = 1; i <= n; i++) ans = Math.max(ans, dp[m][i]);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxPoints(new int[][]{
            {4,1}, {0,0}, {5,5}, {0,1},{0,0}
        }) == 9L;

        assert new Solution().maxPoints(new int[][]{
            {3}, {4}, {2}, {0}
        }) == 9L;

        assert new Solution().maxPoints(new int[][]{
            {1,2,3}, {1,5,1}, {3,1,1}
        }) == 9L;

        assert new Solution().maxPoints(new int[][]{
            {1,5}, {2,3}, {4,2}
        }) == 11L;
    }

}
