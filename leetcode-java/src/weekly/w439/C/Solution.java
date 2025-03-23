package weekly.w439.C;

import ability.Benchmark;

import java.util.Arrays;

/**
 * 3473. Sum of K Subarrays With Length at Least M
 *
 * https://leetcode.cn/contest/weekly-contest-439/problems/sum-of-k-subarrays-with-length-at-least-m/
 *
 * You are given an integer array nums and two integers, k and m.
 *
 * Return the maximum sum of k non-overlapping subarrays of nums, where each subarray has a length of at least m.
 */

public class Solution {

    // k 个不重叠且长度至少为 m 的子数组的最大和
    public int maxSum(int[] nums, int k, int m) {
        memo = new int[nums.length][k + 1];
        for (var row : memo) Arrays.fill(row, -1);

        return maxSum(nums, 0, k, m);
    }

    private int[][] memo = null;

    // 从 i 开始取 k 个不重叠且长度至少为 m 的子数组的最大和
    private int maxSum(int[] nums, int i, int k, int m) {
        if (k == 0) return 0;
        if (nums.length - i < k * m) return Integer.MIN_VALUE / 2;
        if (memo[i][k] != -1) return memo[i][k];

        int ans = maxSum(nums, i + 1, k, m);
        for (int j = i, s = 0; j < nums.length; j++) {
            s += nums[j];
            if (j - i + 1 >= m) ans = Math.max(ans, s + maxSum(nums, j + 1, k - 1, m));
        }

        return memo[i][k] = ans;
    }

    private static class Iteration {
        public int maxSum(int[] nums, int k, int m) {
            final int n = nums.length, INF = (int) 1e9;

            // dp[i][j] 表示在 [i, n) 取 j 个不重叠且长度至少为 m 的子数组最大和
            int[][] dp = new int[n + 1][k + 1];
            // 从后往前枚举起始位置 i
            for (int i = n - 1; i >= 0; i--) {
                // 首先计算剩余数字的数量
                int remain = n - i;

                // 枚举需要取的子数组的数量
                for (int j = 1; j <= k; j++) {
                    // 如果需要的数字数量大于剩余的数字数量, 那么无法实现
                    dp[i][j] = i + 1 == n ? -INF : dp[i + 1][j]; // 可以跳过当前元素取后续的最大值

                    // 否则我们枚举从 i 开始枚举所有可能的和
                    if (j * m <= remain) {
                        // 我们可以从 i 开始枚举直到剩余 (k - 1) * m 个数字
                        int r = n - (j - 1) * m;
                        for (int s = 0, l = i; l < r; l++) {
                            s += nums[l];
                            if (l - i + 1 >= m) dp[i][j] = Math.max(dp[i][j], s + dp[l + 1][j - 1]);
                        }
                    }
                }
            }

            return dp[0][k];
        }
    }

    private static class OptimizedIteration {
        public int maxSum(int[] nums, int k, int m) {
            final int n = nums.length, INF = (int) 1e9;

            // 计算前缀和用于优化之后的迭代
            int[] acc = new int[n + 1];
            for (int i = 1; i <= n; i++) acc[i] = acc[i - 1] + nums[i - 1];

            // dp[i][j] 表示使用 [0, j) 组成 i 个不重叠的子数组的最大和
            int[][] dp = new int[k + 1][n + 1];

            // 枚举所组成的子数组的数量
            for (int i = 1; i <= k; i++) {
                Arrays.fill(dp[i], -INF);

                // 我们需要求解 dp[i][j], 考虑是否选择最后一位, 我们有以下转移方程:
                //  - 不使用最后一位: dp[i][j] <- dp[i][j - 1]
                //  - 使用最后一位: dp[i][j] <- dp[i - 1][x] 其中 x 需要以下条件:
                //      - 满足最小长度为 m
                //      - 需要保证剩下的至少有 (i - 1) * m 个
                //      - 所以 x 需要考虑的范围是: [(i - 1) * m, j - m)
                //
                // 在使用最后一位的情况下, 我们有以下公式:
                //  - max(dp[i - 1][x] + (acc[j] - acc[x]))     其中 x \in [(i - 1) * m, j - m)
                //
                // 由于 acc[j] 是个常量, 提取得到:
                //  - acc[j] + max(dp[i - 1][x] - acc[x])
                //
                // 令 d = dp[i - 1][x] - acc[x], 则有 acc[j] + max_d
                //
                // 在枚举时, 我们左侧需要保留 (i - 1) * m 个位置, 同时我们当前数组需要至少 m 个元素
                // 所以我们从 i * m 开始枚举
                for (int j = i * m, maxD = -INF; j <= n; j++) {
                    // 我们需要计算使用最后一位的情况
                    maxD = Math.max(maxD, dp[i - 1][j - m] - acc[j - m]);
                    // 计算转移
                    dp[i][j] = Math.max(dp[i][j - 1], acc[j] + maxD);
                }
            }

            return dp[k][n];
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("OptimizedIteration", () -> {
            assert new OptimizedIteration().maxSum(new int[]{-10,3,-1,-2}, 4, 1) == -10;
            assert new OptimizedIteration().maxSum(new int[]{1,2,-1,3,3,4}, 2, 2) == 13;
        });

        Benchmark.benchmark("Iteration", () -> {
            assert new Iteration().maxSum(new int[]{-10,3,-1,-2}, 4, 1) == -10;
            assert new Iteration().maxSum(new int[]{1,2,-1,3,3,4}, 2, 2) == 13;
        });

        Benchmark.benchmark("", () -> {
            assert new Solution().maxSum(new int[]{-10,3,-1,-2}, 4, 1) == -10;
            assert new Solution().maxSum(new int[]{1,2,-1,3,3,4}, 2, 2) == 13;
        });
    }

}
