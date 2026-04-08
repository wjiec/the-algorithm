package weekly.w480.A;

import java.util.Arrays;

/**
 * Q1. Absolute Difference Between Maximum and Minimum K Elements
 *
 * https://leetcode.cn/contest/weekly-contest-480/problems/absolute-difference-between-maximum-and-minimum-k-elements/
 *
 * You are given an integer array nums and an integer k.
 *
 * Find the absolute difference between:
 *
 * the sum of the k largest elements in the array; and
 * the sum of the k smallest elements in the array.
 *
 * Return an integer denoting this difference.
 */

public class Solution {

    public int absDifference(int[] nums, int k) {
        Arrays.sort(nums);

        int ans = 0;
        for (int i = 0; i < k; i++) ans += nums[i];
        for (int i = 0; i < k; i++) ans -= nums[nums.length - i - 1];
        return Math.abs(ans);
    }

    public static void main(String[] args) {
    }

}
