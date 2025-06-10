package weekly.w450.A;

/**
 * Q1. Smallest Index With Digit Sum Equal to Index
 *
 * https://leetcode.cn/contest/weekly-contest-450/problems/smallest-index-with-digit-sum-equal-to-index
 *
 * You are given an integer array nums.
 *
 * Return the smallest index i such that the sum of the digits of nums[i] is equal to i.
 *
 * If no such index exists, return -1.
 */

public class Solution {

    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int s = 0;
            for (; nums[i] != 0; nums[i] /= 10) s += nums[i] % 10;
            if (s == i) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
    }

}
