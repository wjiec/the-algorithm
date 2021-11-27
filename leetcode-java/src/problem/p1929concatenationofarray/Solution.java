package problem.p1929concatenationofarray;

import common.Checker;

/**
 * 1929. Concatenation of Array
 *
 * https://leetcode-cn.com/problems/concatenation-of-array/
 *
 * Given an integer array nums of length n, you want to create an array ans of length 2n
 * where ans[i] == nums[i] and ans[i + n] == nums[i] for 0 <= i < n (0-indexed).
 *
 * Specifically, ans is the concatenation of two nums arrays.
 *
 * Return the array ans.
 */

public class Solution {

    public int[] getConcatenation(int[] nums) {
        int[] ans = new int[nums.length * 2];
        for (int i = 0, l = nums.length; i < l; i++) {
            ans[i] = ans[i + l] = nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().getConcatenation(new int[]{1,2,1}), new int[]{1,2,1,1,2,1});
        assert Checker.check(new Solution().getConcatenation(new int[]{1,3,2,1}), new int[]{1,3,2,1,1,3,2,1});
    }

}
