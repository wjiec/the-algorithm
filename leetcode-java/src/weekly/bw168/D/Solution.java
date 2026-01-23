package weekly.bw168.D;

import common.Tag;

import java.util.Arrays;

/**
 * Q4. Count Ways to Choose Coprime Integers from Rows
 *
 * https://leetcode.cn/contest/biweekly-contest-168/problems/count-ways-to-choose-coprime-integers-from-rows/
 *
 * You are given a m x n matrix mat of positive integers.
 *
 * Return an integer denoting the number of ways to choose exactly one integer
 * from each row of mat such that the greatest common divisor of all chosen integers is 1.
 *
 * Since the answer may be very large, return it modulo 1e9 + 7.
 */

public class Solution {

    private static final int MOD = 1_000_000_007;

    public int countCoprime(int[][] mat) { return dfs(mat, 0, 0); }

    private final int[][] memo = new int[151][151];
    { for (var r : memo) Arrays.fill(r, -1); }

    // 当前已选择的数的 gcd 是 g, 当前要从 mat[i] 中选择一个数
    private int dfs(int[][] mat, int i, int g) {
        if (i == mat.length) return g == 1 ? 1 : 0;
        if (memo[i][g] != -1) return memo[i][g];

        int ans = 0;
        for (var v : mat[i]) ans = (ans + dfs(mat, i + 1, gcd(g, v))) % MOD;
        return memo[i][g] = ans;
    }

    private int gcd(int a, int b) { return a == 0 ? b : (a % b == 0 ? b : gcd(b, a % b)); }

    private static class Iteration {
        private static final int MOD = 1_000_000_007;

        public int countCoprime(int[][] mat) {
            int n = mat.length, mx = 0;
            for (var r : mat) for (var v : r) mx = Math.max(mx, v);

            // dp[i][j] 表示在 [0, i] 中选择一个数且 gcd 的值为 j 的方案数有多少
            int[][] dp = new int[n][mx + 1];
            for (var v : mat[0]) dp[0][v] += 1;
            for (int i = 1; i < n; i++) {
                for (var v : mat[i]) {
                    // 枚举前一个的 gcd 值
                    for (int g = 1; g <= mx; g++) {
                        if (dp[i - 1][g] == 0) continue; int cg = gcd(g, v);
                        dp[i][cg] = (dp[i][cg] + dp[i - 1][g]) % MOD;
                    }
                }
            }
            return dp[n - 1][1];
        }

        private int gcd(int a, int b) { return a % b == 0 ? b : gcd(b, a % b); }
    }

    @Tag("倍数容斥")
    private static class InclusionExclusion {
        public int countCoprime(int[][] mat) {
            final int MOD = 1_000_000_007;
            // 我们要求 gcd(...) = 1 的方案数, 我们可以换种方案:
            //  - 求所有 gcd(...) 为 1 的倍数的方案数 - 所有 gcd(...) 恰好 2 的倍数的方案数 - ...
            //  - 同样的: 所有 gcd(...) 恰好为 2 的倍数的方案数 = 所有 gcd(...) 为 2 的倍数的方案数 - 所有 gcd(...) 恰好 4 的倍数的方案数 - ...
            //
            // 也就是如果我们需要求 gcd(...) 恰好等于 k 的方案数 g(k), 就是:
            //  - g(k) = ALL - g(2k) - g(3k) - ... - g(nk)
            //      - ALL 表示在每一行中选择是 k 的倍数的数字的方案数
            int mx = 0;
            for (var r : mat) for (var v : r) mx = Math.max(mx, v);

            // 倒着枚举所有倍数
            int[] gcd = new int[mx + 1];
            for (int i = mx; i > 0; i--) {
                // 在 mat 的每一行选择 i 的倍数, 使得最终的 gcd 值为 i 的倍数的方案数
                long ans = 1;
                for (var r : mat) {
                    int c = 0;
                    for (var v : r) c += v % i == 0 ? 1 : 0;
                    // 每一行的选择方案相互独立, 使用乘法原理. 如果结果为 0 说明找不到方案
                    if ((ans = (ans * c) % MOD) == 0) break;
                }

                // 减去所有倍数, 使得当前值是恰好 gcd == i 的方案数
                for (int j = i + i; j <= mx; j += i) ans = (ans - gcd[j] + MOD) % MOD;
                gcd[i] = (int) ans;
            }

            return gcd[1];
        }
    }

    public static void main(String[] args) {
        assert new Solution().countCoprime(new int[][]{{1,2},{3,4}}) == 3;
        assert new Solution().countCoprime(new int[][]{{1},{2},{2}}) == 1;
        assert new Solution().countCoprime(new int[][]{{150}}) == 0;

        assert new Iteration().countCoprime(new int[][]{{1,2},{3,4}}) == 3;
        assert new Iteration().countCoprime(new int[][]{{1},{2},{2}}) == 1;
        assert new Iteration().countCoprime(new int[][]{{150}}) == 0;

        assert new InclusionExclusion().countCoprime(new int[][]{{1,2},{3,4}}) == 3;
        assert new InclusionExclusion().countCoprime(new int[][]{{1},{2},{2}}) == 1;
        assert new InclusionExclusion().countCoprime(new int[][]{{150}}) == 0;
    }

}
