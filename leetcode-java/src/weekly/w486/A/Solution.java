package weekly.w486.A;

/**
 * Q1. Minimum Prefix Removal to Make Array Strictly Increasing
 *
 * https://leetcode.cn/contest/weekly-contest-486/problems/minimum-prefix-removal-to-make-array-strictly-increasing/
 *
 * You are given an integer array nums.
 *
 * You need to remove exactly one prefix (possibly empty) from nums.
 *
 * Return an integer denoting the minimum length of the removed prefix
 * such that the remaining array is strictly increasing.
 */

public class Solution {

    public int minimumPrefixLength(int[] nums) {
        int s = nums.length - 2;
        while (s >= 0 && nums[s] <= nums[s + 1]) s--;
        return s + 1;
    }

    public static void main(String[] args) {
    }

}
