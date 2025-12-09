package weekly.bw167.B;

/**
 * Q2. Longest Fibonacci Subarray
 *
 * https://leetcode.cn/contest/biweekly-contest-167/problems/longest-fibonacci-subarray/
 *
 * You are given an array of positive integers nums.
 *
 * A Fibonacci array is a contiguous sequence whose third and subsequent terms
 * each equal the sum of the two preceding terms.
 *
 * Return the length of the longest Fibonacci subarray in nums.
 *
 * Note: Subarrays of length 1 or 2 are always Fibonacci.
 */

public class Solution {

    public int longestSubarray(int[] nums) {
        if (nums.length <= 2) return nums.length;

        int ans = 2;
        for (int i = 2, curr = 2; i <= nums.length; i++) {
            if (i == nums.length || nums[i] != nums[i - 1] + nums[i - 2]) {
                ans = Math.max(ans, curr); curr = 2;
            } else curr++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
