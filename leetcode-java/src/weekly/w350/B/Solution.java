package weekly.w350.B;

import java.util.Arrays;

/**
 * 6890. Find the Value of the Partition
 *
 * https://leetcode.cn/contest/weekly-contest-350/problems/find-the-value-of-the-partition/
 *
 * You are given a positive integer array nums.
 *
 * Partition nums into two arrays, nums1 and nums2, such that:
 *
 * Each element of the array nums belongs to either the array nums1 or the array nums2.
 * Both arrays are non-empty.
 * The value of the partition is minimized.
 * The value of the partition is |max(nums1) - min(nums2)|.
 *
 * Here, max(nums1) denotes the maximum element of the array nums1, and min(nums2) denotes the
 * minimum element of the array nums2.
 *
 * Return the integer denoting the value of such partition.
 */

public class Solution {

    public int findValueOfPartition(int[] nums) {
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            ans = Math.min(ans, nums[i] - nums[i - 1]);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
