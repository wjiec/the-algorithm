package weekly.bw153.C;

import ability.Array;
import ability.Benchmark;

import java.util.Arrays;

/**
 * 3500. Minimum Cost to Divide Array Into Subarrays
 *
 * https://leetcode.cn/contest/biweekly-contest-153/problems/minimum-cost-to-divide-array-into-subarrays/
 *
 * You are given two integer arrays, nums and cost, of the same size, and an integer k.
 *
 * You can divide nums into subarrays. The cost of the ith subarray consisting of elements nums[l..r] is:
 *
 * (nums[0] + nums[1] + ... + nums[r] + k * i) * (cost[l] + cost[l + 1] + ... + cost[r]).
 * Note that i represents the order of the subarray: 1 for the first subarray, 2 for the second, and so on.
 *
 * Return the minimum total cost possible from any valid division.
 */

public class Solution {

    /** @noinspection DuplicatedCode*/
    // (nums[0] + nums[1] + ... + nums[r] + k * i) * (cost[l] + cost[l + 1] + ... + cost[r])
    public long minimumCost(int[] nums, int[] cost, int k) {
        int n = nums.length;
        long[] acc1 = new long[n + 1], acc2 = new long[n + 1];
        for (int i = 1; i <= n; i++) acc1[i] = acc1[i - 1] + nums[i - 1];
        for (int i = 1; i <= n; i++) acc2[i] = acc2[i - 1] + cost[i - 1];

        memo = new long[n + 1][n + 1];
        for (var row : memo) Arrays.fill(row, -1);
        return dfs(acc1, 0, n, acc2, 1, k);
    }

    private long[][] memo = null;

    // (acc1[r + 1] + k * i) * (acc2[r + 1] - acc2[l])
    private long dfs(long[] acc1, int l, int n, long[] acc2, int i, long k) {
        if (l == n) return 0;
        if (memo[l][i] != -1) return memo[l][i];

        long ans = Long.MAX_VALUE;
        for (int r = l; r < n; r++) {
            long curr = dfs(acc1, r + 1, n, acc2, i + 1, k);
            ans = Math.min(ans, (acc1[r + 1] + k * i) * (acc2[r + 1] - acc2[l]) + curr);
        }

        return memo[l][i] = ans;
    }

    private static class Iteration {
        /** @noinspection DuplicatedCode*/
        public long minimumCost(int[] nums, int[] cost, int k) {
            int n = nums.length;
            long[] acc1 = new long[n + 1], acc2 = new long[n + 1];
            for (int i = 1; i <= n; i++) acc1[i] = acc1[i - 1] + nums[i - 1];
            for (int i = 1; i <= n; i++) acc2[i] = acc2[i - 1] + cost[i - 1];

            // dp[i][j] 表示使用前 i 个数字(也就是 [0, i) 范围), 组成 j 个子数组的最小代价
            long[][] dp = new long[n + 1][n + 1];
            for (int r = 1; r <= n; r++) {
                Arrays.fill(dp[r], Long.MAX_VALUE >> 1);
                // 此时数组的有效的范围是 [0, r), 这其中一共有 r 个数字, 也就是最多有 r 个子数组
                for (int j = 1; j <= r; j++) {
                    // 包含当前数字且要满足 j 个子数组, 则当前子数组的左边界 l 需要满足以下条件
                    //  - [l, r) 共有 r - l 个元素, 则剩下的其他元素数量为 r - (r - l) = l 个
                    //      - 剩下的元素数量必须可以分出 j - 1 个子数组, 即 l >= j - 1
                    //  - 根据定义 l < r
                    // 所以则有 j - 1 <= l < r
                    for (int l = j - 1; l < r; l++) {
                        // 当前使用的数组范围是 [l, r)
                        //  - 使用 [0, l) 且组成 j - 1 个子数组为 dp[l][j - 1]
                        //  - nums[0] + ... + nums[r - 1] 的值为 acc1[r]
                        //  - cost[l] + ... + cost[r - 1] 的值为 acc2[r] - acc2[l]
                        dp[r][j] = Math.min(dp[r][j], dp[l][j - 1] + (acc1[r] + (long) k * j) * (acc2[r] - acc2[l]));
                    }
                }
            }

            long ans = Long.MAX_VALUE;
            for (int j = 0; j <= n; j++) ans = Math.min(ans, dp[n][j]);
            return ans;
        }
    }

    private static class Iteration1 {
        /** @noinspection DuplicatedCode*/
        public long minimumCost(int[] nums, int[] cost, int k) {
            int n = nums.length;
            long[] acc1 = new long[n + 1], acc2 = new long[n + 1];
            for (int i = 1; i <= n; i++) acc1[i] = acc1[i - 1] + nums[i - 1];
            for (int i = 1; i <= n; i++) acc2[i] = acc2[i - 1] + cost[i - 1];

            long[][] dp = new long[2][n + 1];
            Arrays.fill(dp[0], Long.MAX_VALUE >> 1); dp[0][0] = 0;

            long ans = Long.MAX_VALUE;
            for (int j = 1; j <= n; j++) {
                long[] curr = dp[j & 1], prev = dp[(j & 1) ^ 1];
                Arrays.fill(curr, Long.MAX_VALUE >> 1);

//                for (int r = 1; r <= n; r++) {
//                    for (int l = j - 1; l < r; l++) {
//                        curr[r] = Math.min(curr[r], prev[l] + (acc1[r] + (long) k * j) * (acc2[r] - acc2[l]));
//                    }
//                }

                // 需要优化上面的循环, 使得以下的值最小
                //  = prev[l] + (acc1[r + 1] + k * i) * (acc2[r + 1] - acc2[l])
                //
                // 当 r 确定时 acc1[r + 1], k, i, acc2[r + 1] 都是常数, 令 d = acc1[r + 1] + k * i
                //  = prev[l] + d * (acc2[r + 1] - acc2[l])
                //  = acc2[r + 1] * d + prev[l] - acc2[l] * d
                //
                // 令 e = acc2[r + 1] * d
                //  = e + prev[l] - acc2[l] * d
                //
                // 也就是求得 prev[l] - acc2[l] * d 的最大值

                long max = 0;
                for (int r = 1; r <= n; r++) {
                    long d = acc1[r] + (long) k * j, e = acc2[r] * d;

                    for (int l = j - 1; l < r; l++) {
                        curr[r] = Math.min(curr[r], prev[l] + (acc1[r] + (long) k * j) * (acc2[r] - acc2[l]));
                    }
                }

                ans = Math.min(ans, curr[n]);
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        int[] list = Array.make(1000, 1000);

        Benchmark.benchmark("Iteration1", () -> {
            assert new Iteration1().minimumCost(list, list, 1) == 501000500000L;
            assert new Iteration1().minimumCost(new int[]{3,1,4}, new int[]{4,6,6}, 1) == 110;
            assert new Iteration1().minimumCost(new int[]{4,8,5,1,14,2,2,12,1}, new int[]{7,2,8,4,2,2,1,1,2}, 7) == 985;
        });

        Benchmark.benchmark("Iteration", () -> {
            assert new Iteration().minimumCost(list, list, 1) == 501000500000L;
            assert new Iteration().minimumCost(new int[]{3,1,4}, new int[]{4,6,6}, 1) == 110;
            assert new Iteration().minimumCost(new int[]{4,8,5,1,14,2,2,12,1}, new int[]{7,2,8,4,2,2,1,1,2}, 7) == 985;
        });

        Benchmark.benchmark("", () -> {
            assert new Solution().minimumCost(new int[]{3,1,4}, new int[]{4,6,6}, 1) == 110;
            assert new Solution().minimumCost(new int[]{4,8,5,1,14,2,2,12,1}, new int[]{7,2,8,4,2,2,1,1,2}, 7) == 985;
        });
    }

}
