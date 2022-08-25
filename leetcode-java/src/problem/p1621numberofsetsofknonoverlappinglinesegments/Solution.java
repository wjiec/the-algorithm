package problem.p1621numberofsetsofknonoverlappinglinesegments;

import common.TODO;

/**
 * 1621. Number of Sets of K Non-Overlapping Line Segments
 *
 * https://leetcode.cn/problems/number-of-sets-of-k-non-overlapping-line-segments/
 *
 * Given n points on a 1-D plane, where the ith point (from 0 to n-1) is at x = i, find
 * the number of ways we can draw exactly k non-overlapping line segments such that
 * each segment covers two or more points. The endpoints of each segment must have
 * integral coordinates. The k line segments do not have to cover all n points, and
 * they are allowed to share endpoints.
 *
 * Return the number of ways we can draw k non-overlapping line segments.
 * Since this number can be huge, return it modulo 109 + 7.
 */

public class Solution {

    @TODO(tips = "https://www.bilibili.com/video/BV1CK411G7Xk")
    public int numberOfSets(int n, int k) {
        final long MOD = 1_000_000_007;
        // dp[i][j] = 从 0 ~ i 中选取 j 个线段的方案数
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = (2 * dp[i - 1][j] + dp[i - 1][j - 1] - dp[i - 2][j] + MOD) % MOD;
            }
        }

        return (int) (dp[n][k] % MOD);
    }

    public static void main(String[] args) {
        assert new Solution().numberOfSets(48, 12) == 337883431;

        assert new Solution().numberOfSets(4, 2) == 5;
        assert new Solution().numberOfSets(3, 1) == 3;
        assert new Solution().numberOfSets(30, 7) == 796297179;
        assert new Solution().numberOfSets(5, 3) == 7;
        assert new Solution().numberOfSets(3, 2) == 1;
    }

}
