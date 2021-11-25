package problem.p1920buildarrayfrompermutation;

import common.Checker;

/**
 * 1920. Build Array from Permutation
 *
 * https://leetcode-cn.com/problems/build-array-from-permutation/
 *
 * Given a zero-based permutation nums (0-indexed), build an array ans of
 * the same length where ans[i] = nums[nums[i]] for each 0 <= i < nums.length and return it.
 *
 * A zero-based permutation nums is an array of distinct integers from 0 to nums.length - 1 (inclusive).
 */

public class Solution {

    public int[] buildArray(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums[i]];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().buildArray(new int[]{0,2,1,5,3,4}), new int[]{0,1,2,4,5,3});
        assert Checker.check(new Solution().buildArray(new int[]{5,0,1,2,3,4}), new int[]{4,5,0,1,2,3});
    }

}
