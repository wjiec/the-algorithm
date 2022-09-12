package problem.p1829maximumxorforeachquery;

import common.Checker;

/**
 * 1829. Maximum XOR for Each Query
 *
 * https://leetcode.cn/problems/maximum-xor-for-each-query/
 *
 * You are given a sorted array nums of n non-negative integers and an integer maximumBit.
 * You want to perform the following query n times:
 *
 * Find a non-negative integer k < 2maximumBit such that nums[0] XOR nums[1]
 * XOR ... XOR nums[nums.length-1] XOR k is maximized. k is the answer to the ith query.
 *
 * Remove the last element from the current array nums.
 * Return an array answer, where answer[i] is the answer to the ith query.
 */

public class Solution {

    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int[] ans = new int[nums.length];
        int mask = (1 << maximumBit) - 1, n = nums.length, curr = 0;
        for (int i = 0; i < n; i++) {
            ans[n - i - 1] = mask ^ (curr ^= nums[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().getMaximumXor(new int[]{0,1,1,3}, 2), new int[]{0,3,2,3});
        assert Checker.check(new Solution().getMaximumXor(new int[]{2,3,4,7}, 3), new int[]{5,2,6,5});
        assert Checker.check(new Solution().getMaximumXor(new int[]{0,1,2,2,5,7}, 3), new int[]{4,3,6,4,6,7});
    }

}
