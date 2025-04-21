package weekly.w444.C;

import ability.Benchmark;

import java.util.Arrays;

/**
 * 3509. Maximum Product of Subsequences With an Alternating Sum Equal to K
 *
 * https://leetcode.cn/contest/weekly-contest-444/problems/maximum-product-of-subsequences-with-an-alternating-sum-equal-to-k/
 *
 * You are given an integer array nums and two integers, k and limit.
 *
 * Your task is to find a non-empty subsequence of nums that:
 *
 * Has an alternating sum equal to k.
 * Maximizes the product of all its numbers without the product exceeding limit.
 * Return the product of the numbers in such a subsequence. If no subsequence satisfies the requirements, return -1.
 *
 * The alternating sum of a 0-indexed array is defined as the sum of the elements
 * at even indices minus the sum of the elements at odd indices.
 */

public class Solution {

    public int maxProduct(int[] nums, int k, int limit) {
        // 子序列的乘积不超过 limit, 需要最大化乘积
        // 交错和 = sum(偶数下标的和) - sum(奇数下标的和) == k
        Arrays.sort(nums);

        dfs(nums, 0, 0, 0, 1, k, limit);
        return ans;
    }

    private int ans = -1;

    // 当前在分配 nums[i], 当前是在分配第 j 个位置, 交错和为 sum, 乘积为 mul
    private void dfs(int[] nums, int i, int j, int sum, int mul, int k, int limit) {
        if (j != 0 && sum == k && mul <= limit) ans = Math.max(ans, mul);
        if (i == nums.length || mul > limit) return;

        dfs(nums, i + 1, j, sum, mul, k, limit); // 跳过当前数
        dfs(nums, i + 1, j + 1, sum + (j % 2 == 0 ? nums[i] : -nums[i]), j == 0 ? nums[i] : mul * nums[i], k, limit);
    }

    public static void main(String[] args) {
        Benchmark.benchmark("", () -> {
            assert new Solution().maxProduct(new int[]{7,8,12,2,9,0,5,12,10,1,11,9,5,9,7,12,12,12,6,7,5,7,9,2,7,7,11,8,9,1,6,12,11,6,1,4,2,6,5,4}, 15, 100) == 100;
            assert new Solution().maxProduct(new int[]{0}, 0, 10) == 0;

            assert new Solution().maxProduct(new int[]{1,2,3}, 2, 10) == 6;
            assert new Solution().maxProduct(new int[]{0,2,3}, -5, 12) == -1;
            assert new Solution().maxProduct(new int[]{2,2,3,3}, 0, 9) == 9;
        });
    }

}
