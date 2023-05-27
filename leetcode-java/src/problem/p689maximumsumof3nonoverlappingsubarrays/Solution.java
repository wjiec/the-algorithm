package problem.p689maximumsumof3nonoverlappingsubarrays;

import common.Checker;

/**
 * 689. Maximum Sum of 3 Non-Overlapping Subarrays
 *
 * https://leetcode.cn/problems/maximum-sum-of-3-non-overlapping-subarrays/
 *
 * Given an integer array nums and an integer k, find three non-overlapping
 * subarrays of length k with maximum sum and return them.
 *
 * Return the result as a list of indices representing the starting position of
 * each interval (0-indexed).
 *
 * If there are multiple answers, return the lexicographically smallest one.
 */

public class Solution {

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] ans = new int[3];
        int sum1 = 0, maxSum1 = 0, maxSum1Idx = 0;
        int sum2 = 0, maxSum12 = 0, maxSum12Idx1 = 0, maxSum12Idx2 = 0;
        int sum3 = 0, maxTotal = 0;
        for (int i = k * 2; i < nums.length; ++i) {
            sum1 += nums[i - k * 2];
            sum2 += nums[i - k];
            sum3 += nums[i];
            if (i >= k * 3 - 1) {
                if (sum1 > maxSum1) {
                    maxSum1 = sum1;
                    maxSum1Idx = i - k * 3 + 1;
                }
                if (maxSum1 + sum2 > maxSum12) {
                    maxSum12 = maxSum1 + sum2;
                    maxSum12Idx1 = maxSum1Idx;
                    maxSum12Idx2 = i - k * 2 + 1;
                }
                if (maxSum12 + sum3 > maxTotal) {
                    maxTotal = maxSum12 + sum3;
                    ans[0] = maxSum12Idx1;
                    ans[1] = maxSum12Idx2;
                    ans[2] = i - k + 1;
                }
                sum1 -= nums[i - k * 3 + 1];
                sum2 -= nums[i - k * 2 + 1];
                sum3 -= nums[i - k + 1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().maxSumOfThreeSubarrays(new int[]{1,2,1,2,6,7,5,1}, 2), new int[]{0,3,5});
        assert Checker.check(new Solution().maxSumOfThreeSubarrays(new int[]{1,2,1,2,1,2,1,2,1}, 2), new int[]{0,2,4});
    }

}
