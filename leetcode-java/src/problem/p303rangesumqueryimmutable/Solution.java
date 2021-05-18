package problem.p303rangesumqueryimmutable;

import java.util.Arrays;

/**
 * 303. Range Sum Query - Immutable
 *
 * https://leetcode-cn.com/problems/range-sum-query-immutable/
 *
 * Given an integer array nums, handle multiple queries of the following type:
 *
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 *
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * int sumRange(int left, int right) Returns the sum of the elements of nums between
 * indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 */

public class Solution {

    static class NumArray {
        private int[] sums;

        public NumArray(int[] nums) {
            sums = Arrays.copyOf(nums, nums.length);
            for (int i = 1; i < nums.length; i++) {
                sums[i] += sums[i - 1];
            }
        }

        public int sumRange(int left, int right) {
            if (left == 0) {
                return sums[right];
            }
            return sums[right] - sums[left - 1];
        }
    }

    public static void main(String[] args) {
        assert new NumArray(new int[]{-2, 0, 3, -5, 2, -1}).sumRange(0, 2) == 1;
        assert new NumArray(new int[]{-2, 0, 3, -5, 2, -1}).sumRange(2, 5) == -1;
        assert new NumArray(new int[]{-2, 0, 3, -5, 2, -1}).sumRange(0, 5) == -3;
    }

}
