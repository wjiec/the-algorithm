package problem.p1685sumofabsolutedifferencesinasortedarray;

import common.Checker;

/**
 * 1685. Sum of Absolute Differences in a Sorted Array
 *
 * https://leetcode.cn/problems/sum-of-absolute-differences-in-a-sorted-array/
 *
 * You are given an integer array nums sorted in non-decreasing order.
 *
 * Build and return an integer array result with the same length as nums such that result[i] is equal to
 * the summation of absolute differences between nums[i] and all the other elements in the array.
 *
 * In other words, result[i] is equal to sum(|nums[i]-nums[j]|) where 0 <= j < nums.length and j != i (0-indexed).
 */

public class Solution {

    public int[] getSumAbsoluteDifferences(int[] nums) {
        long sum = 0, curr = 0;
        for (var n : nums) sum += n;

        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            sum -= nums[i];
            ans[i] = (int) (sum - nums[i] * (n - i));
            ans[i] += (int) ((i + 1) * nums[i] - curr);
            curr += nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().getSumAbsoluteDifferences(new int[]{2,3,5}), new int[]{4,3,5});
        assert Checker.check(new Solution().getSumAbsoluteDifferences(new int[]{1,4,6,8,10}), new int[]{24,15,13,15,21});
    }

}
