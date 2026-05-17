package weekly.w494.A;

/**
 * Q1. Construct Uniform Parity Array I
 *
 * https://leetcode.cn/contest/weekly-contest-494/problems/construct-uniform-parity-array-i/
 *
 * You are given an array nums1 of n distinct integers.
 *
 * You want to construct another array nums2 of length n such that
 * the elements in nums2 are either all odd or all even.
 *
 * For each index i, you must choose exactly one of the following (in any order):
 *
 * nums2[i] = nums1[i]
 * nums2[i] = nums1[i] - nums1[j], for an index j != i
 *
 * Return true if it is possible to construct such an array, otherwise, return false.
 */

public class Solution {

    public boolean uniformArray(int[] nums1) {
        // 要么就是全是奇数或者全是偶数
        //
        // 否则我们需要让里面的所有奇数都变成偶数, 或者让所有的偶数变成奇数
        //  - 让所有奇数都变成偶数: 奇数 - 奇数 = 偶数, 也就是至少存在 2 个奇数
        //  - 让所有的偶数变成奇数: 偶数 - 奇数 = 奇数, 也就是至少存在 1 个奇数
        // 也就是都可以
        return true;
    }

    public static void main(String[] args) {
    }

}
