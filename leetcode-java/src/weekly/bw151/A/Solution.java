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

    public static void main(String[] args) {
    }

}
