package weekly.w468.A;

/**
 * Q1. Bitwise OR of Even Numbers in an Array
 *
 * https://leetcode.cn/contest/weekly-contest-468/problems/bitwise-or-of-even-numbers-in-an-array/
 *
 * You are given an integer array nums.
 *
 * Return the bitwise OR of all even numbers in the array.
 *
 * If there are no even numbers in nums, return 0.
 */

public class Solution {

    public int evenNumberBitwiseORs(int[] nums) {
        int ans = 0;
        for (var v : nums) if ((v & 1) == 0) ans |= v;
        return ans;
    }

    public static void main(String[] args) {
    }

}
