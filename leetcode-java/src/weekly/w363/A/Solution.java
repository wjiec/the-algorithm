package weekly.w363.A;

import java.util.List;

/**
 * 2859. Sum of Values at Indices With K Set Bits
 *
 * https://leetcode.cn/contest/weekly-contest-363/problems/sum-of-values-at-indices-with-k-set-bits/
 *
 * You are given a 0-indexed integer array nums and an integer k.
 *
 * Return an integer that denotes the sum of elements in nums whose corresponding
 * indices have exactly k set bits in their binary representation.
 *
 * The set bits in an integer are the 1's present when it is written in binary.
 *
 * For example, the binary representation of 21 is 10101, which has 3 set bits.
 */

public class Solution {

    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (Integer.bitCount(i) == k) {
                ans += nums.get(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
