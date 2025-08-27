package weekly.w460.A;

import java.util.Arrays;

/**
 * Q1. Maximum Median Sum of Subsequences of Size 3
 *
 * https://leetcode.cn/contest/weekly-contest-460/problems/maximum-median-sum-of-subsequences-of-size-3/
 *
 * You are given an integer array nums with a length divisible by 3.
 *
 * You want to make the array empty in steps. In each step, you can select any
 * three elements from the array, compute their median, and remove the selected
 * elements from the array.
 *
 * The median of an odd-length sequence is defined as the middle element of the
 * sequence when it is sorted in non-decreasing order.
 *
 * Return the maximum possible sum of the medians computed from the selected elements
 */

public class Solution {

    public long maximumMedianSum(int[] nums) {
        Arrays.sort(nums);

        long ans = 0;
        for (int l = 0, r = nums.length - 1; l < r; l++, r -= 2) {
            ans += nums[r - 1];
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
