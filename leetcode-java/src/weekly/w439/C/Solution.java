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

    public static void main(String[] args) {
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
