package problem.p280wigglesort;

import common.PrettyPrinter;

import java.util.Arrays;

/**
 * 280. Wiggle Sort
 *
 * https://leetcode.cn/problems/wiggle-sort/
 *
 * Given an integer array nums, reorder it such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *
 * You may assume the input array always has a valid answer.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        if (nums.length <= 2) return;

        int n = nums.length, half = (n / 2) + (n % 2);
        int[] left = new int[half], right = new int[half];
        // 得到 sorted[n/2], sorted[n/2-1], ..., sorted[0] 的序列
        for (int i = 0, j = half - 1; j >= 0; i++, j--) left[i] = nums[j];
        // 得到 sorted[n], sorted[n-1], ..., sorted[n/2] 的序列
        for (int i = 0, j = n - 1; j >= half; i++, j--) right[i] = nums[j];
        // 逐个插入数据
        for (int i = 0, j = 0; i < half && j < n; i++, j += 2) nums[j] = left[i];
        for (int i = 0, j = 1; i < half && j < n; i++, j += 2) nums[j] = right[i];
    }

    public static void main(String[] args) {
        int[] s1 = new int[]{3,5,2,1,6,4};
        new Solution().wiggleSort(s1);
        PrettyPrinter.println(s1);

        int[] s2 = new int[]{6,6,5,6,3,8};
        new Solution().wiggleSort(s2);
        PrettyPrinter.println(s2);
    }

}
