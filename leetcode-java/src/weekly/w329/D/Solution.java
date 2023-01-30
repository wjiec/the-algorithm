package weekly.w329.D;

import java.util.Arrays;

/**
 * 2547. Minimum Cost to Split an Array
 *
 * https://leetcode.cn/problems/minimum-cost-to-split-an-array/
 *
 * You are given an integer array nums and an integer k.
 *
 * Split the array into some number of non-empty subarrays. The cost of a
 * split is the sum of the importance value of each subarray in the split.
 *
 * Let trimmed(subarray) be the version of the subarray where all numbers
 * which appear only once are removed.
 *
 * For example, trimmed([3,1,2,4,3,4]) = [3,4,3,4].
 * The importance value of a subarray is k + trimmed(subarray).length.
 *
 * For example, if a subarray is [1,2,3,3,3,4,4], then trimmed([1,2,3,3,3,4,4]) = [3,3,3,4,4].The importance value of this subarray will be k + 5.
 * Return the minimum possible cost of a split of nums.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public int minCost(int[] nums, int k) {
        int[] state = new int[nums.length];
        int[] global = new int[nums.length];
        for (var v : nums) global[v]++;

        // [cost, #group]
        int[][] dp = new int[nums.length + 1][2];
        for (int i = 1; i <= nums.length; i++) {
            if (global[nums[i - 1]] == 1) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1];
                continue;
            }

            dp[i][0] = dp[i - 1][0] + k;
            dp[i][1] = dp[i - 1][1] + 1;

            int trimmed = 0;
            Arrays.fill(state, 0);
            for (int j = i; j > 0; j--) {
                switch (++state[nums[j - 1]]) {
                    case 1 -> {}
                    case 2 -> trimmed += 2;
                    default -> trimmed++;
                }

                int cost = dp[j - 1][0] + k + trimmed;
                if (cost < dp[i][0]) {
                    dp[i][0] = cost;
                    dp[i][1] = dp[i - 1][1] + 1;
                }
            }
        }

        return dp[nums.length][0];
    }

    public static void main(String[] args) {
        assert new Solution().minCost(new int[]{1,2,1,2,1,3,3}, 2) == 8;
        assert new Solution().minCost(new int[]{1,2,1,2,1}, 2) == 6;
        assert new Solution().minCost(new int[]{1,2,1,2,1}, 5) == 10;
    }

}
