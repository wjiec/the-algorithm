package weekly.w468.B;

/**
 * Q2. Maximum Total Subarray Value I
 *
 * https://leetcode.cn/contest/weekly-contest-468/problems/maximum-total-subarray-value-i/
 *
 *  You are given an integer array nums of length n and an integer k.
 *
 * You need to choose exactly k non-empty subarrays nums[l..r] of nums.
 * Subarrays may overlap, and the exact same subarray (same l and r) can be chosen more than once.
 *
 * The value of a subarray nums[l..r] is defined as: max(nums[l..r]) - min(nums[l..r]).
 *
 * The total value is the sum of the values of all chosen subarrays.
 *
 * Return the maximum possible total value you can achieve.
 */

public class Solution {

    public long maxTotalValue(int[] nums, int k) {
        long max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (var v : nums) {
            max = Math.max(max, v);
            min = Math.min(min, v);
        }

        return (max - min) * k;
    }

    public static void main(String[] args) {
    }

}
