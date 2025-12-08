package weekly.w470.B;

/**
 * Q2. Longest Subsequence With Non-Zero Bitwise XOR
 *
 * https://leetcode.cn/contest/weekly-contest-470/problems/longest-subsequence-with-non-zero-bitwise-xor/
 *
 * You are given an integer array nums.
 *
 * Return the length of the longest subsequence in nums whose bitwise XOR is non-zero.
 * If no such subsequence exists, return 0.
 */

public class Solution {

    public int longestSubsequence(int[] nums) {
        int xor = 0, zero = 0;
        for (var v : nums) {
            xor ^= v;
            if (v == 0) zero++;
        }
        if (zero == nums.length) return 0;
        return nums.length - (xor == 0 ? 1 : 0);
    }

    public static void main(String[] args) {
    }

}
