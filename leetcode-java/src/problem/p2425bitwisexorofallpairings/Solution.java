package problem.p2425bitwisexorofallpairings;

/**
 * 2425. Bitwise XOR of All Pairings
 *
 * https://leetcode.cn/problems/bitwise-xor-of-all-pairings/
 *
 * You are given two 0-indexed arrays, nums1 and nums2, consisting of non-negative integers.
 * There exists another array, nums3, which contains the bitwise XOR of all pairings of integers
 * between nums1 and nums2 (every integer in nums1 is paired with every integer in nums2 exactly once).
 *
 * Return the bitwise XOR of all integers in nums3.
 */

public class Solution {

    // [a, b, c] [x, y]
    // (a^x ^ a^y) ^ (b^x ^ b^y) ^ (c^x ^ c^y)
    // (a^a) ^ (b^b) ^ (c^c) ^ (x^x^x) ^ (y^y^y)
    public int xorAllNums(int[] nums1, int[] nums2) {
        int l1 = nums1.length, l2 = nums2.length;
        if (l1 % 2 == 0 && l2 % 2 == 0) return 0;

        int xor1 = 0, xor2 = 0;
        for (var v : nums1) xor1 ^= v;
        for (var v : nums2) xor2 ^= v;

        if (l1 % 2 == 1 && l2 % 2 == 1) return xor1 ^ xor2;
        return l1 % 2 == 1 ? xor2 : xor1;
    }

    public static void main(String[] args) {
        assert new Solution().xorAllNums(new int[]{8,6,29,2,26,16,15,29}, new int[]{24,12,12}) == 9;

        assert new Solution().xorAllNums(new int[]{2,1,3}, new int[]{10,2,5,0}) == 13;
        assert new Solution().xorAllNums(new int[]{1,2}, new int[]{3,4}) == 0;
    }

}
