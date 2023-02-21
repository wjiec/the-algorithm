package weekly.w333.C;

/**
 * 2572. Count the Number of Square-Free Subsets
 *
 * https://leetcode.cn/problems/count-the-number-of-square-free-subsets/
 *
 * You are given a positive integer 0-indexed array nums.
 *
 * A subset of the array nums is square-free if the product of its elements is a square-free integer.
 *
 * A square-free integer is an integer that is divisible by no square number other than 1.
 *
 * Return the number of square-free non-empty subsets of the array nums.
 * Since the answer may be too large, return it modulo 109 + 7.
 *
 * A non-empty subset of nums is an array that can be obtained by
 * deleting some (possibly none but not all) elements from nums.
 *
 * Two subsets are different if and only if the chosen indices to delete are different.
 */

/**
 * 给你一个正整数数组 nums 。
 *
 * 如果数组 nums 的子集中的元素乘积是一个 无平方因子数 ，则认为该子集是一个 无平方 子集。
 *
 * 无平方因子数 是无法被除 1 之外任何平方数整除的数字。
 *
 * 返回数组 nums 中 无平方 且 非空 的子集数目。因为答案可能很大，返回对 109 + 7 取余的结果。
 */

public class Solution {

    // 1 <= v <= 30
    public int squareFreeSubsets(int[] nums) {
        return 1;
    }

    public static void main(String[] args) {
        assert new Solution().squareFreeSubsets(new int[]{3,4,4,5}) == 3;
        assert new Solution().squareFreeSubsets(new int[]{1}) == 1;
    }

}
