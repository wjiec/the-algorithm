package weekly.w462.B;

/**
 * Q2. Maximum K to Sort a Permutation
 *
 * https://leetcode.cn/contest/weekly-contest-462/problems/maximum-k-to-sort-a-permutation/
 *
 * You are given an integer array nums of length n, where
 * nums is a permutation of the numbers in the range [0..n - 1].
 *
 * You may swap elements at indices i and j only if nums[i] AND nums[j] == k,
 * where AND denotes the bitwise AND operation and k is a non-negative integer.
 *
 * Return the maximum value of k such that the array can be sorted in
 * non-decreasing order using any number of such swaps.
 *
 * If nums is already sorted, return 0.
 */

public class Solution {

    // 在满足 nums[i] AND nums[j] == k 的情况下可以交换 i, j 的位置, 求最终使得数组递增的最大 k 值
    public int sortPermutation(int[] nums) {
        // 两个数 a, b 想要 AND 之后等于 k, 也就是 a & b == k
        //  - 有 (a ^ k) & (b ^ k) == 0
        //  - 也就是 (a ^ k) = ~(b ^ k)
        int ans = (1 << 30) - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) ans &= nums[i];
        }
        return ans > nums.length ? 0 : ans;
    }

    public static void main(String[] args) {
    }

}
