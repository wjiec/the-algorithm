package weekly.w469.C;

import common.PrettyPrinter;

/**
 * Q3. Number of ZigZag Arrays I
 *
 * https://leetcode.cn/contest/weekly-contest-469/problems/number-of-zigzag-arrays-i/
 *
 * You are given three integers n, l, and r.
 *
 * A ZigZag array of length n is defined as follows:
 *
 * Each element lies in the range [l, r].
 * No two adjacent elements are equal.
 * No three consecutive elements form a strictly increasing or strictly decreasing sequence.
 * Return the total number of valid ZigZag arrays.
 *
 * Since the answer may be large, return it modulo 109 + 7.
 *
 * A sequence is said to be strictly increasing if each element is strictly greater than its previous one (if exists).
 * A sequence is said to be strictly decreasing if each element is strictly smaller than its previous one (if exists).
 */

public class Solution {

    public int zigZagArrays(int n, int l, int r) {
        final int MOD = 1_000_000_007, INC = 0, DEC = 1;
        // 把范围 [l, r] 改到 [0, r - l]
        r = r - l + 1;
        // 构造一个长度为 n, 元素值在 [l, r] 范围, 并且满足
        //  - 不存在连续相同的整数
        //  - 不能出现连续三个递增或递减
        //
        // dp[i][j][k][l] 表示当前构造第 i 位, 最后一位是 j, 且连续递增或递减状态是 k, 且数量是 l
        int[][][][] dp = new int[n][r][2][3];
        for (int j = 0; j < r; j++) dp[0][j][INC][1] = dp[0][j][DEC][1] = 1;
        for (int i = 1; i < n; i++) {
            // 枚举当前位填的数字
            for (int j = 0; j < r; j++) {
                // 在递增情况下
                //  - 如果前一位是 [0, j) 那么当前填 j 会导致递增数量 + 1
                //  - 如果前一位是 [j, r) 那么当前填 j 会导致递增数量变为 1
                //
                // 也就是:
                //  - dp[i][j][INC][1] = sum(dp[i - 1][j...r-1][INC | DEC][1 | 2])
                //  - dp[i][j][INC][2] = sum(dp[i - 1][0..j-1][INC | DEC][1])
                for (int k = j; k < r; k++) {
                    dp[i][j][INC][1] = (dp[i][j][INC][1] + dp[i - 1][k][INC][1] + dp[i - 1][k][INC][2] + dp[i - 1][k][DEC][1] + dp[i - 1][k][DEC][2]) % MOD;
                }
                for (int k = 0; k < j; k++) {
                    dp[i][j][INC][2] = (dp[i][j][INC][2] + dp[i - 1][k][INC][1] + dp[i - 1][k][DEC][1]) % MOD;
                }
                // 在递减情况下
                //  - 如果前一位是 [0, j] 那么当前填 j 会导致递减数量变为 1
                //  - 如果前一位是 (j, r) 那么当前填 j 会导致递减数量 + 1
                //
                // 也就是:
                //  - dp[i][j][DEC][1] = sum(dp[i - 1][0...j][INC | DEC][1 | 2])
                //  - dp[i][j][DEC][2] = sum(dp[i - 1][j+1...r-1][INC | DEC][1])
                for (int k = 0; k <= j; k++) {
                    dp[i][j][DEC][1] = (dp[i][j][DEC][1] + dp[i - 1][k][INC][1] + dp[i - 1][k][INC][2] + dp[i - 1][k][DEC][1] + dp[i - 1][k][DEC][2]) % MOD;
                }
                for (int k = j + 1; k < r; k++) {
                    dp[i][j][DEC][2] = (dp[i][j][DEC][2] + dp[i - 1][k][INC][1] + dp[i - 1][k][DEC][1]) % MOD;
                }
            }
        }

        PrettyPrinter.println(dp);

        long ans = 0;
        for (int j = 0; j < r; j++) ans = (ans + dp[n - 1][j][INC][1] + dp[n - 1][j][DEC][1] + dp[n - 1][j][INC][2] + dp[n - 1][j][DEC][2]) % MOD;
        System.out.println(ans);
        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().zigZagArrays(3, 1, 3) == 10;
        assert new Solution().zigZagArrays(3, 4, 5) == 2;
    }

}
