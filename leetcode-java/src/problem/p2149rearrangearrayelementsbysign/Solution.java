package problem.p2149rearrangearrayelementsbysign;

import common.Checker;

/**
 * 2149. Rearrange Array Elements by Sign
 *
 * https://leetcode.cn/problems/rearrange-array-elements-by-sign/
 *
 * You are given a 0-indexed integer array nums of even length consisting of an
 * equal number of positive and negative integers.
 *
 * You should rearrange the elements of nums such that the modified array
 * follows the given conditions:
 *
 * Every consecutive pair of integers have opposite signs.
 * For all integers with the same sign, the order in which they were present in nums is preserved.
 * The rearranged array begins with a positive integer.
 * Return the modified array after rearranging the elements to satisfy the aforementioned conditions.
 */

public class Solution {

    public int[] rearrangeArray(int[] nums) {
        int positive = 0, negative = 0, n = nums.length;

        int[] ans = new int[n];
        for (int i = 0; i < n; i += 2) {
            while (nums[positive] < 0) positive++;
            ans[i] = nums[positive++];

            while (nums[negative] > 0) negative++;
            ans[i + 1] = nums[negative++];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().rearrangeArray(new int[]{3,1,-2,-5,2,-4}), new int[]{3,-2,1,-5,2,-4});
        assert Checker.check(new Solution().rearrangeArray(new int[]{-1,1}), new int[]{1,-1});
    }

}
