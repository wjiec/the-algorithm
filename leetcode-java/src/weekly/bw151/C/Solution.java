package weekly.bw151.C;

import ability.Benchmark;

import java.util.HashMap;
import java.util.Map;

/**
 * 3469. Find Minimum Cost to Remove Array Elements
 *
 * https://leetcode.cn/contest/biweekly-contest-151/problems/find-minimum-cost-to-remove-array-elements/
 *
 * You are given an integer array nums. Your task is to remove all elements from the array by
 * performing one of the following operations at each step until nums is empty:
 *
 * Choose any two elements from the first three elements of nums and remove them.
 * The cost of this operation is the maximum of the two elements removed.
 *
 * If fewer than three elements remain in nums, remove all the remaining elements in a single operation.
 * The cost of this operation is the maximum of the remaining elements.
 *
 * Return the minimum cost required to remove all the elements.
 */

public class Solution {

    public int minCost(int[] nums) {
        return minCost(nums, 1, nums[0]);
    }

    private final Map<Integer, Integer> memo = new HashMap<>();

    // 从 [i, nums.length) 开始移除, 且前一个元素是 prev 的最小代价
    private int minCost(int[] nums, int i, int prev) {
        if (nums.length - i + 1 < 3) {
            int ans = prev;
            for (int j = i; j < nums.length; j++) {
                ans = Math.max(ans, nums[j]);
            }
            return ans;
        }

        int key = i << 22 | prev;
        if (memo.containsKey(key)) return memo.get(key);

        // 移除 prev 和第一个
        int ans = Math.max(prev, nums[i]) + minCost(nums, i + 2, nums[i + 1]);
        // 移除 prev 和第二个
        ans = Math.min(ans, Math.max(prev, nums[i + 1]) + minCost(nums, i + 2, nums[i]));
        // 移除第一个和第二个
        ans = Math.min(ans, Math.max(nums[i], nums[i + 1]) + minCost(nums, i + 2, prev));

        memo.put(key, ans);
        return ans;
    }

    private static class Iteration {
        public int minCost(int[] nums) {
            int n = nums.length;
            if (n == 1) return nums[0];
            if (n == 2) return Math.max(nums[0], nums[1]);

            // dp[i][j] 表示以 i 作为保留的元素加上 [j, n) 的最小消除代价是什么
            int[][] dp = new int[n][n + 1];
            // 初始条件, 如果保留 i 且从 n 开始, 则需要直接取 nums[i]
            for (int i = 0; i < n; i++) dp[i][n] = nums[i];
            // 初始条件, 如果保留 i 且从 n - 1 开始, 则需要直接取 max(nums[i], nums[n - 1])
            for (int i = 0; i < n; i++) dp[i][n - 1] = Math.max(nums[i], nums[n - 1]);

            // 从后往前枚举我们的保留位置 i, 上面已经处理了 n == 1 or n == 2 的情况
            for (int i = n - 3; i >= 0; i--) {
                // 每次我们都可以处理 nums[i], nums[j], nums[j + 1] 三个元素
                for (int j = n - 2; j > i; j--) {
                    // 选出头部两个元素
                    dp[i][j] = Math.max(nums[i], nums[j]) + dp[j + 1][j + 2];
                    // 选取头尾两个元素
                    dp[i][j] = Math.min(dp[i][j], Math.max(nums[i], nums[j + 1]) + dp[j][j + 2]);
                    // 选取后面两个元素
                    dp[i][j] = Math.min(dp[i][j], Math.max(nums[j], nums[j + 1]) + dp[i][j + 2]);
                }
            }

            return dp[0][1];
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("Iteration", () -> {
            assert new Iteration().minCost(new int[]{6,2,8,4}) == 12;
            assert new Iteration().minCost(new int[]{2,1,3,3}) == 5;
        });

        Benchmark.benchmark("", () -> {
            assert new Solution().minCost(new int[]{6,2,8,4}) == 12;
            assert new Solution().minCost(new int[]{2,1,3,3}) == 5;
        });
    }

}
