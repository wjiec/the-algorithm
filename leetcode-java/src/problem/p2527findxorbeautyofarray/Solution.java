package problem.p2527findxorbeautyofarray;

/**
 * 2527. Find Xor-Beauty of Array
 *
 * https://leetcode.cn/problems/find-xor-beauty-of-array/
 *
 * You are given a 0-indexed integer array nums.
 *
 * The effective value of three indices i, j, and k is defined
 * as ((nums[i] | nums[j]) & nums[k]).
 *
 * The xor-beauty of the array is the XORing of the effective values of all
 * the possible triplets of indices (i, j, k) where 0 <= i, j, k < n.
 *
 * Return the xor-beauty of nums.
 *
 * Note that:
 *
 * val1 | val2 is bitwise OR of val1 and val2.
 * val1 & val2 is bitwise AND of val1 and val2.
 */

public class Solution {

    public int xorBeauty(int[] nums) {
        // (v | v) & v = v
        // ((a | b) & b) ^ ((a | b) & a) = (a & b) ^ (b & a) = 0
        // ((a | b) & c) ^ ((a | c) & b) ^ ((b | c) & a) ^ ... = 0

        int ans = 0;
        for (var v : nums) ans ^= v;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().xorBeauty(new int[]{1, 4}) == 5;
        assert new Solution().xorBeauty(new int[]{15,45,20,2,34,35,5,44,32,30}) == 34;
    }

}
