package problem.p2161partitionarrayaccordingtogivenpivot;

import common.Checker;

/**
 * 2161. Partition Array According to Given Pivot
 *
 * https://leetcode.cn/problems/partition-array-according-to-given-pivot/
 *
 * You are given a 0-indexed integer array nums and an integer pivot. Rearrange nums
 * such that the following conditions are satisfied:
 *
 * Every element less than pivot appears before every element greater than pivot.
 * Every element equal to pivot appears in between the elements less than and greater than pivot.
 * The relative order of the elements less than pivot and the elements greater than pivot is maintained.
 * More formally, consider every pi, pj where pi is the new position of the ith element and pj is the
 * new position of the jth element. For elements less than pivot, if i < j and nums[i] < pivot and
 * nums[j] < pivot, then pi < pj. Similarly for elements greater than pivot, if i < j and nums[i] > pivot
 * and nums[j] > pivot, then pi < pj.
 *
 * Return nums after the rearrangement.
 */

public class Solution {

    public int[] pivotArray(int[] nums, int pivot) {
        int idx = 0;
        int[] ans = new int[nums.length];
        for (int num : nums) if (num < pivot) ans[idx++] = num;
        for (int num : nums) if (num == pivot) ans[idx++] = num;
        for (int num : nums) if (num > pivot) ans[idx++] = num;
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().pivotArray(new int[]{9,12,5,10,14,3,10}, 10), new int[]{9,5,3,10,10,12,14});
    }

}
