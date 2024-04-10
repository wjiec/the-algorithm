package weekly.w392.C;

import java.util.Arrays;

/**
 * 3107. Minimum Operations to Make Median of Array Equal to K
 *
 * https://leetcode.cn/contest/weekly-contest-392/problems/minimum-operations-to-make-median-of-array-equal-to-k/
 *
 * You are given an integer array nums and a non-negative integer k.
 * In one operation, you can increase or decrease any element by 1.
 *
 * Return the minimum number of operations needed to make the median of nums equal to k.
 *
 * The median of an array is defined as the middle element of the array when it is sorted
 * in non-decreasing order.
 *
 * If there are two choices for a median, the larger of the two values is taken.
 */

public class Solution {

    public long minOperationsToMakeMedianK(int[] nums, int k) {
        int n = nums.length, mid = n / 2;
        Arrays.sort(nums);

        long ans = Math.abs(nums[nums.length / 2] - k); nums[n / 2] = k;

        for (int i = mid - 1; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                ans += nums[i] - nums[i + 1];
                nums[i] = nums[i + 1];
            }
        }
        for (int i = mid + 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                ans += nums[i - 1] - nums[i];
                nums[i] = nums[i - 1];
            }
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
