package weekly.bw129.C;

import common.Timer;

/**
 * 100292. Find All Possible Stable Binary Arrays I
 *
 * https://leetcode.cn/contest/biweekly-contest-129/problems/find-all-possible-stable-binary-arrays-i/
 *
 * You are given 3 positive integers zero, one, and limit.
 *
 * A binary array arr is called stable if:
 *
 * The number of occurrences of 0 in arr is exactly zero.
 * The number of occurrences of 1 in arr is exactly one.
 * Each subarray of arr with a size greater than limit must contain both 0 and 1.
 * Return the total number of stable binary arrays.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 */

public class Solution {

    private final static int MOD = 1_000_000_007;

    // 同时最多出现 limit 个 0|1
    public int numberOfStableArrays(int zero, int one, int limit) {
        // dp[i][j][k] 表示使用i个0和j个1，且最后一位为k的方案数
        long[][][] dp = new long[zero + 1][one + 1][2];
        for (int i = 1; i <= Math.min(limit, zero); i++) dp[i][0][0] = 1;
        for (int j = 1; j <= Math.min(limit, one); j++) dp[0][j][1] = 1;

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1] + (i > limit ? MOD - dp[i - limit - 1][j][1] : 0)) % MOD;
                dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1] + (j > limit ? MOD - dp[i][j - limit - 1][0] : 0)) % MOD;
            }
        }

        return (int) (dp[zero][one][0] + dp[zero][one][1]) % MOD;
    }

    public static void main(String[] args) {
        Timer.stopwatch(() -> {
            assert new Solution().numberOfStableArrays(72, 74, 53) == 396816249;
            assert new Solution().numberOfStableArrays(200, 200, 100) == 70669177;
            assert new Solution().numberOfStableArrays(77, 80, 94) == 694148718;
            assert new Solution().numberOfStableArrays(76, 59, 99) == 44017274;
            assert new Solution().numberOfStableArrays(35, 35, 22) == 782677541;
            assert new Solution().numberOfStableArrays(20, 15, 75) == 247943139;

            assert new Solution().numberOfStableArrays(1, 1, 2) == 2;
            assert new Solution().numberOfStableArrays(1, 2, 2) == 3;
            assert new Solution().numberOfStableArrays(1, 2, 1) == 1;
            assert new Solution().numberOfStableArrays(3, 3, 2) == 14;

            return null;
        });
    }

}
