package weekly.w452.A;

import java.util.Arrays;

/**
 * Q1. Partition Array into Two Equal Product Subsets
 *
 * https://leetcode.cn/contest/weekly-contest-452/problems/partition-array-into-two-equal-product-subsets
 *
 * You are given an integer array nums containing distinct positive integers and an integer target.
 *
 * Determine if you can partition nums into two non-empty disjoint subsets, with each element
 * belonging to exactly one subset, such that the product of the elements in each subset is equal to target.
 *
 * Return true if such a partition exists and false otherwise.
 *
 * A subset of an array is a selection of elements of the array.
 */

public class Solution {

    public boolean checkEqualPartitions(int[] nums, long target) {
        int n = nums.length, mask = 1 << n;
        long[] mul = new long[mask]; Arrays.fill(mul, -1);
        for (int i = 1; i < mask; i++) {
            mul[i] = 1;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    mul[i] *= nums[j];
                }
            }

            if (mul[i] == target && (i != mask - 1) && mul[i] == mul[(mask - 1) ^ i]) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        assert new Solution().checkEqualPartitions(new int[]{3,1,6,8,4}, 24);
        assert !new Solution().checkEqualPartitions(new int[]{1, 5, 24}, 159);
    }

}
