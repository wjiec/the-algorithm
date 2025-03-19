package weekly.bw151.A;

import java.util.Arrays;

/**
 * 3467. Transform Array by Parity
 *
 * https://leetcode.cn/contest/biweekly-contest-151/problems/transform-array-by-parity/
 *
 * You are given an integer array nums. Transform nums by performing the
 * following operations in the exact order specified:
 *
 * Replace each even number with 0.
 * Replace each odd numbers with 1.
 * Sort the modified array in non-decreasing order.
 *
 * Return the resulting array after performing these operations.
 */

public class Solution {

    public int[] transformArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] & 1;
        }
        Arrays.sort(nums);
        return nums;
    }

    private static class Optimization {
        public int[] transformArray(int[] nums) {
            int n = nums.length, odd = 0;
            for (var v : nums) odd += v & 1;
            for (int i = 0; i < n - odd; i++) nums[i] = 0;
            for (int i = 0; i < odd; i++) nums[n - i - 1] = 1;
            return nums;
        }
    }

    public static void main(String[] args) {
    }

}
