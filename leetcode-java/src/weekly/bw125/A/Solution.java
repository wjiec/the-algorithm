package weekly.bw125.A;

/**
 * 3065. Minimum Operations to Exceed Threshold Value I
 *
 * https://leetcode.cn/contest/biweekly-contest-125/problems/minimum-operations-to-exceed-threshold-value-i/
 *
 * You are given a 0-indexed integer array nums, and an integer k.
 *
 * In one operation, you can remove one occurrence of the smallest element of nums.
 *
 * Return the minimum number of operations needed so that all elements of the array are greater than or equal to k.
 */

public class Solution {

    public int minOperations(int[] nums, int k) {
        int ans = 0;
        for (var v : nums) if (v < k) ans++;
        return ans;
    }

    public static void main(String[] args) {
    }

}
