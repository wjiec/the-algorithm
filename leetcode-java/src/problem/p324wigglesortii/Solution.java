package problem.p324wigglesortii;

import common.PrettyPrinter;

import java.util.Arrays;

/**
 * 324. Wiggle Sort II
 *
 * https://leetcode-cn.com/problems/wiggle-sort-ii/
 *
 * Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 *
 * You may assume the input array always has a valid answer.
 */

public class Solution {

    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        if (nums.length <= 2) return;

        int n = nums.length, half = (n / 2) + (n % 2);
        int[] left = new int[half], right = new int[half];
        for (int i = 0, j = half - 1; j >= 0; i++, j--) left[i] = nums[j];
        for (int i = 0, j = n - 1; j >= half; i++, j--) right[i] = nums[j];
        for (int i = 0; i < n; i++) {
            nums[i] = left[i / 2];
            if (++i < n) nums[i] = right[i / 2];
        }
    }

    public static void main(String[] args) {
        int[] nums0 = new int[]{1,5,1,1,6,4};
        new Solution().wiggleSort(nums0);
        PrettyPrinter.println(nums0);

        int[] nums1 = new int[]{1,3,2,2,3,1};
        new Solution().wiggleSort(nums1);
        PrettyPrinter.println(nums1);

        int[] nums2 = new int[]{1,1,2,1,2,2,1};
        new Solution().wiggleSort(nums2);
        PrettyPrinter.println(nums2);
    }

}
