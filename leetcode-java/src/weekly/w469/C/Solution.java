package weekly.w469.C;

import ability.Benchmark;

import java.util.Arrays;

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

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int zigZagArrays(int n, int l, int r) {
        final int MOD = 1_000_000_007, INC = 0, DEC = 1;
        // 把范围 [l, r] 改到 [0, r - l]
        r = r - l + 1;
        // 构造一个长度为 n, 元素值在 [l, r] 范围, 并且满足
        //  - 不存在连续相同的整数
        //  - 不能出现连续三个递增或递减
        //
        // 实际就是构造一个递增之后立马递减的序列

        // dp[i][j][k] 表示在 [0, i] 中最后一个位置为 j 且当前构成递增 = 0 或递减 = 1 的方案个数
        long[][][] dp = new long[n][r][2];
        // 初始化: 长度为 1 的序列都是满足要求的
        for (int j = 0; j < r; j++) dp[0][j][INC] = dp[0][j][DEC] = 1;

        // 枚举当前构造的第几位
        for (int i = 1; i < n; i++) {
            // 枚举当前位的数字
            for (int j = 0; j < r; j++) {
                // 如果当前位要递增, 那么前一位就是要 < j 且是要递减的情况
                for (int pj = 0; pj < j; pj++) dp[i][j][INC] = (dp[i][j][INC] + dp[i - 1][pj][DEC]) % MOD;
                // 如果当前位要递减, 那么前一位就是要 > j 且是要递增的情况
                for (int pj = j + 1; pj < r; pj++) dp[i][j][DEC] = (dp[i][j][DEC] + dp[i - 1][pj][INC]) % MOD;
            }
        }

        // 最后一位可以是任何数且可以是递减或递增
        long ans = 0;
        for (int j = 0; j < r; j++) ans = (ans + dp[n - 1][j][INC] + dp[n - 1][j][DEC]) % MOD;
        return (int) ans;
    }

    private static class Optimization1 {
        public int zigZagArrays(int n, int l, int r) {
            final int MOD = 1_000_000_007, INC = 0, DEC = 1;
            // 把范围 [l, r] 改到 [0, r - l]
            r = r - l + 1;
            // 构造一个长度为 n, 元素值在 [l, r] 范围, 并且满足
            //  - 不存在连续相同的整数
            //  - 不能出现连续三个递增或递减
            //
            // 实际就是构造一个递增之后立马递减的序列

            // dp[i][j][k] 表示在 [0, i] 中最后一个位置为 j 且当前构成递增 = 0 或递减 = 1 的方案个数
            int[][][] dp = new int[n][r][2];
            // 初始化: 长度为 1 的序列都是满足要求的
            for (int j = 0; j < r; j++) dp[0][j][INC] = dp[0][j][DEC] = 1;

            // 枚举当前构造的第几位
            for (int i = 1; i < n; i++) {
                int[] incSum = new int[r + 1], decSum = new int[r + 1];
                for (int j = 0; j < r; j++) {
                    incSum[j + 1] = (incSum[j] + dp[i - 1][j][INC]) % MOD;
                    decSum[j + 1] = (decSum[j] + dp[i - 1][j][DEC]) % MOD;
                }

                // 枚举当前位的数字
                for (int j = 0; j < r; j++) {
                    dp[i][j][INC] = decSum[j] % MOD; // \sum{0, 1, ..., j-1}
                    dp[i][j][DEC] = (incSum[r] - incSum[j + 1] + MOD) % MOD; // \sum{j+1, j+2, ..., r-1}
                }
            }

            // 最后一位可以是任何数且可以是递减或递增
            long ans = 0;
            for (int j = 0; j < r; j++) ans = (ans + dp[n - 1][j][INC] + dp[n - 1][j][DEC]) % MOD;
            return (int) ans;
        }
    }

    private static class Optimization2 {
        public int zigZagArrays(int n, int l, int r) {
            final int MOD = 1_000_000_007; r = r - l + 1;

            // dp[i][j][k] 表示在 [0, i] 中最后一个位置为 j 且当前构成递增 = 0 或递减 = 1 的方案个数
            int[][] incDp = new int[2][r], decDp = new int[2][r];
            int[] incSum = new int[r + 1], decSum = new int[r + 1];
            Arrays.fill(incDp[0], 1); Arrays.fill(decDp[0], 1);

            for (int i = 1; i < n; i++) {
                int curr = i & 1, prev = curr ^ 1;
                for (int j = 0; j < r; j++) {
                    incSum[j + 1] = (incSum[j] + incDp[prev][j]) % MOD;
                    decSum[j + 1] = (decSum[j] + decDp[prev][j]) % MOD;
                }

                // 枚举当前位的数字
                for (int j = 0; j < r; j++) {
                    incDp[curr][j] = decSum[j]; // 前缀和
                    decDp[curr][j] = (incSum[r] - incSum[j + 1] + MOD) % MOD; // 后缀和
                }
            }

            // 最后一位可以是任何数且可以是递减或递增
            long ans = 0; int b = (n & 1) ^ 1;
            for (int j = 0; j < r; j++) ans = (ans + incDp[b][j] + decDp[b][j]) % MOD;
            return (int) ans;
        }
    }

    private static class Optimization3 {
        public int zigZagArrays(int n, int l, int r) {
            final int MOD = 1_000_000_007; r = r - l + 1;

            // dp[i][j][k] 表示在 [0, i] 中最后一个位置为 j 且当前构成递增 = 0 或递减 = 1 的方案个数
            int[] inc = new int[r], dec = new int[r];
            Arrays.fill(inc, 1); Arrays.fill(dec, 1);

            for (int i = 1; i < n; i++) {
                int[] incS = new int[r], decS = new int[r];
                for (int j = 1; j < r; j++) decS[j] = (decS[j - 1] + dec[j - 1]) % MOD;
                for (int j = r - 2; j >= 0; j--) incS[j] = (incS[j + 1] + inc[j + 1]) % MOD;

                inc = decS; dec = incS;
            }

            // 最后一位可以是任何数且可以是递减或递增
            long ans = 0;
            for (int j = 0; j < r; j++) ans = (ans + inc[j] + dec[j]) % MOD;
            return (int) ans;
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("Optimization3", () -> {
            assert new Optimization3().zigZagArrays(2000, 1, 2000) == 594850306;
        });

        Benchmark.benchmark("Optimization2", () -> {
            assert new Optimization2().zigZagArrays(2000, 1, 2000) == 594850306;
        });

        Benchmark.benchmark("Optimization1", () -> {
            assert new Optimization1().zigZagArrays(3, 1, 3) == 10;
            assert new Optimization1().zigZagArrays(3, 4, 5) == 2;
        });

        assert new Solution().zigZagArrays(3, 1, 3) == 10;
        assert new Solution().zigZagArrays(3, 4, 5) == 2;
    }

}
