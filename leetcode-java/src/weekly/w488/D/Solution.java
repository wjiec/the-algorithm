package weekly.w488.D;

import ability.Benchmark;

import java.util.Arrays;

/**
 * Q4. Maximum Score Using Exactly K Pairs
 *
 * https://leetcode.cn/contest/weekly-contest-488/problems/maximum-score-using-exactly-k-pairs/
 *
 * You are given two integer arrays nums1 and nums2 of lengths n and m respectively, and an integer k.
 *
 * You must choose exactly k pairs of indices (i1, j1), (i2, j2), ..., (ik, jk) such that:
 *
 * 0 <= i1 < i2 < ... < ik < n
 * 0 <= j1 < j2 < ... < jk < m
 * For each chosen pair (i, j), you gain a score of nums1[i] * nums2[j].
 *
 * The total score is the sum of the products of all selected pairs.
 *
 * Return an integer representing the maximum achievable total score.
 */

public class Solution {

    public long maxScore(int[] nums1, int[] nums2, int k) {
        return maxScore(nums1, nums1.length - 1, nums2, nums2.length - 1, k);
    }

    private static final long INF = Long.MIN_VALUE / 2;
    private final long[][][] memo = new long[102][102][101];
    { for (var mat : memo) for (var r : mat) Arrays.fill(r, -1); }

    // 从 nums1[0..r1) 和 nums2[0..r2) 中选出 k 对下标的最大和
    private long maxScore(int[] nums1, int r1, int[] nums2, int r2, int k) {
        // 如果 k == 0 表示不需要选了, 而 i < k, j < k 表示无法选出 k 对下标
        if (k == 0) return 0;
        if (r1 + 1 < k || r2 + 1 < k) return INF;
        if (memo[r1][r2][k] != -1) return memo[r1][r2][k];

        long ans = maxScore(nums1, r1 - 1, nums2, r2, k); // 不选 r1 位置
        ans = Math.max(ans, maxScore(nums1, r1, nums2, r2 - 1, k)); // 不选 r2 位置
        // 选择 r1 * r2 这一对元素
        ans = Math.max(ans, maxScore(nums1, r1 - 1, nums2, r2 - 1, k - 1) + (long) nums1[r1] * nums2[r2]);
        return memo[r1][r2][k] = ans;
    }

    private static class Iteration {
        public long maxScore(int[] nums1, int[] nums2, int k) {
            int m = nums1.length, n = nums2.length;
            long[][][] dp = new long[k + 1][m + 1][n + 1];
            for (var mat : dp) for (var r : mat) Arrays.fill(r, Long.MIN_VALUE >> 1);
            for (var r : dp[0]) Arrays.fill(r, 0);

            // 枚举选择的数对的数量
            for (int dk = 1; dk <= k; dk++) {
                // 每个数组至少要选出 k - 1 对, 也就是当前数组必须从 k 开始
                for (int i = dk; i <= m; i++) {
                    for (int j = dk; j <= n; j++) {
                        long pair = (long) nums1[i - 1] * nums2[j - 1];
                        dp[dk][i][j] = Math.max(dp[dk][i - 1][j], dp[dk][i][j - 1]);
                        dp[dk][i][j] = Math.max(dp[dk][i][j], dp[dk - 1][i - 1][j - 1] + pair);
                    }
                }
            }

            return dp[k][m][n];
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("Iteration", () -> {
            assert new Iteration().maxScore(new int[]{23,26}, new int[]{-8,-10,-11,-13,-13,-16,-18}, 1) == -184;
            assert new Iteration().maxScore(new int[]{1,3,2}, new int[]{4,5,1}, 2) == 22;
            assert new Iteration().maxScore(new int[]{-2,0,5}, new int[]{-3,4,-1,2}, 2) == 26;
            assert new Iteration().maxScore(new int[]{-3,-2}, new int[]{1,2}, 2) == -7;
        });

        Benchmark.benchmark("", () -> {
            assert new Solution().maxScore(new int[]{23,26}, new int[]{-8,-10,-11,-13,-13,-16,-18}, 1) == -184;
            assert new Solution().maxScore(new int[]{1,3,2}, new int[]{4,5,1}, 2) == 22;
            assert new Solution().maxScore(new int[]{-2,0,5}, new int[]{-3,4,-1,2}, 2) == 26;
            assert new Solution().maxScore(new int[]{-3,-2}, new int[]{1,2}, 2) == -7;
        });
    }

}
