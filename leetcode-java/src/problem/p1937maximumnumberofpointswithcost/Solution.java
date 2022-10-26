package problem.p1937maximumnumberofpointswithcost;

/**
 * 1937. Maximum Number of Points with Cost
 *
 * https://leetcode.cn/problems/maximum-number-of-points-with-cost/
 *
 * You are given an m x n integer matrix points (0-indexed). Starting with 0 points, you
 * want to maximize the number of points you can get from the matrix.
 *
 * To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c)
 * will add points[r][c] to your score.
 *
 * However, you will lose points if you pick a cell too far from the cell that you picked in the previous row.
 * For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1)
 * and (r + 1, c2) will subtract abs(c1 - c2) from your score.
 *
 * Return the maximum number of points you can achieve.
 *
 * abs(x) is defined as:
 *
 * x for x >= 0.
 * -x for x < 0.
 */

public class Solution {

    public long maxPoints(int[][] points) {
        int m = points.length, n = points[0].length;

        long[][] dp = new long[m][n];
        for (int j = 0; j < n; j++) dp[0][j] = points[0][j];

        //
        // dp[i][j] = dp[i - 1][k] + points[i][j] - abs(j - k)
        //
        // 对以上式子进行拆分，可以得到
        //
        // j > k: dp[i][j] = dp[i - 1][k] + point[i][j] - j + k
        // j < k: dp[i][j] = dp[i - 1][k] + point[i][j] - k + j
        //
        // 整理以上式子，可得以下内容
        //
        // j > k: dp[i][j] = point[i][j] - j + dp[i - 1][k] + k
        // j < k: dp[i][j] = point[i][j] + j + dp[i - 1][k] - k

        for (int i = 1; i < m; i++) {
            long best1 = Long.MIN_VALUE;
            for (int j = 0; j < n; j++) { // j > k
                best1 = Math.max(best1, dp[i - 1][j] + j);
                dp[i][j] = points[i][j] - j + best1;
            }

            long best2 = Long.MIN_VALUE;
            for (int j = n - 1; j >= 0; j--) { // j < k
                best2 = Math.max(best2,dp[i - 1][j] - j);
                dp[i][j] = Math.max(dp[i][j], points[i][j] + best2 + j);
            }
        }

        long ans = Long.MIN_VALUE;
        for (int j = 0; j < n; j++) ans = Math.max(ans, dp[m - 1][j]);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxPoints(new int[][]{{1,2,3},{1,5,1},{3,1,1}}) == 9;
        assert new Solution().maxPoints(new int[][]{{1,5},{2,3},{4,2}}) == 11;
    }

}
