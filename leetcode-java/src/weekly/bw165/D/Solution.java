package weekly.bw165.D;

import common.Tag;

/**
 * Q4. Maximum XOR of Subsequences
 *
 * https://leetcode.cn/contest/biweekly-contest-165/problems/maximum-xor-of-subsequences/
 *
 * You are given an integer array nums of length n where each element is a non-negative integer.
 *
 * Select two subsequences of nums (they may be empty and are allowed to overlap), each
 * preserving the original order of elements, and let:
 *
 * X be the bitwise XOR of all elements in the first subsequence.
 * Y be the bitwise XOR of all elements in the second subsequence.
 * Return the maximum possible value of X XOR Y.
 *
 * Note: The XOR of an empty subsequence is 0.
 */

public class Solution {

    @Tag("异或线性基")
    public int maxXorSubsequences(int[] nums) {
        int mx = 0;
        for (var v : nums) mx = Math.max(mx, v);
        int b = 32 - Integer.numberOfLeadingZeros(mx);

        // 实际上就是找到子序列最大的异或值, 线性基
        int[] basis = new int[b + 1];
        for (var v : nums) {
            for (int i = b; i >= 0; i--) {
                if ((v & (1 << i)) == 0) continue;
                if (basis[i] == 0) {
                    basis[i] = v;
                    break;
                } else v ^= basis[i]; // 去掉这一位
            }
        }

        int ans = 0;
        for (int i = b; i >= 0; i--) {
            if ((ans ^ basis[i]) > ans) {
                ans ^= basis[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
