package problem.p956tallestbillboard;

import ability.Benchmark;

import java.util.Arrays;

/**
 * 956. Tallest Billboard
 *
 * https://leetcode.cn/problems/tallest-billboard/
 *
 * You are installing a billboard and want it to have the largest height.
 * The billboard will have two steel supports, one on each side.
 * Each steel support must be an equal height.
 *
 * You are given a collection of rods that can be welded together.
 * For example, if you have rods of lengths 1, 2, and 3, you can weld
 * them together to make a support of length 6.
 *
 * Return the largest possible height of your billboard installation.
 * If you cannot support the billboard, return 0.
 */

public class Solution {

    public int tallestBillboard(int[] rods) {
        int sum = 0;
        for (var v : rods) sum += v;

        dfs(0, 0, 0, sum, rods);
        return ans;
    }

    private int ans = 0;

    // 一根钢筋要么进a, 要么进b, 要么都不进
    private void dfs(int a, int b, int i, int remain, int[] rods) {
        if (a == b) ans = Math.max(ans, a);
        if (i == rods.length) return;
        if (a + remain < b) return;
        if (b + remain < a) return;
        if (a + remain < ans) return;
        if (b + remain < ans) return;

        dfs(a + rods[i], b, i + 1, remain - rods[i], rods);
        dfs(a, b + rods[i], i + 1, remain - rods[i], rods);
        dfs(a, b, i + 1, remain - rods[i], rods);
    }

    private static class DynamicProgramming {

        private final static int INF = 10001;
        private final static int ZERO = 5000;

        // 尝试将 rods 中一部分数字变为 0, 一部分数字变为负数
        //  求当 rods 的和为 0 时, 最大的正数和为多少
        public int tallestBillboard(int[] rods) {
            // dp[i][j] 表示使用前 i 个数字, 且两组数字之差为 j (+ 5000) 的最大正数和
            int[][] dp = new int[rods.length + 1][INF];
            for (var r : dp) Arrays.fill(r, -1);
            dp[0][ZERO] = 0;

            int ans = 0;
            for (int i = 1; i <= rods.length; i++) {
                int v = rods[i - 1];
                for (int j = 0; j < INF; j++) {
                    // 考虑不选当前 v 的情况下, 我们需要从前一个状态转移过来
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);

                    if (dp[i - 1][j] == -1) continue;
                    // 选择将 v 作为正数
                    if (j + v < INF) dp[i][j + v] = Math.max(dp[i][j + v], dp[i - 1][j] + v);
                    // 选择将 v 作为负数
                    if (j - v >= 0) dp[i][j - v] = Math.max(dp[i][j - v], dp[i - 1][j]);
                }

                ans = Math.max(ans, dp[i][ZERO]);
            }

            return ans;
        }
    }

    /** @noinspection DuplicatedCode*/
    private static class DynamicProgrammingOptimization {
        private final static int INF = 10001;
        private final static int ZERO = 5000;

        public int tallestBillboard(int[] rods) {
            int[][] dp = new int[2][INF];
            for (var r : dp) Arrays.fill(r, -1);
            dp[0][ZERO] = 0;

            int ans = 0, left = ZERO, right = ZERO;
            for (int i = 1; i <= rods.length; i++) {
                int v = rods[i - 1];
                int[] curr = dp[i & 1], prev = dp[(i & 1) ^ 1];
                Arrays.fill(curr, -1);

                for (int j = left; j <= right; j++) {
                    // 考虑不选当前 v 的情况下, 我们需要从前一个状态转移过来
                    curr[j] = Math.max(curr[j], prev[j]);
                    if (prev[j] == -1) continue;

                    // 选择将 v 作为正数
                    if (j + v < INF) {
                        right = Math.max(right, j + v);
                        curr[j + v] = Math.max(curr[j + v], prev[j] + v);
                    }

                    // 选择将 v 作为负数
                    if (j - v >= 0) {
                        left = Math.min(left, j - v);
                        curr[j - v] = Math.max(curr[j - v], prev[j]);
                    }
                }

                ans = Math.max(ans, curr[ZERO]);
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("dp", () -> {
            assert new DynamicProgramming().tallestBillboard(new int[]{1,2,4,8,16,32,64,128,256,512,50,50,50,150,150,150,100,100,100,123}) == 1023;
            assert new DynamicProgramming().tallestBillboard(new int[]{1,2,3,4,5,6}) == 10;
            assert new DynamicProgramming().tallestBillboard(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}) == 10;
        });

        Benchmark.benchmark("", () -> {
            assert new Solution().tallestBillboard(new int[]{1,2,4,8,16,32,64,128,256,512,50,50,50,150,150,150,100,100,100,123}) == 1023;
            assert new Solution().tallestBillboard(new int[]{1,2,3,4,5,6}) == 10;
            assert new Solution().tallestBillboard(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}) == 10;
        });
    }

}
