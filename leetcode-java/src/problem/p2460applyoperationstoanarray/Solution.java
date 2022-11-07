package problem.p2460applyoperationstoanarray;

import common.Checker;

/**
 * 2460. Apply Operations to an Array
 *
 * https://leetcode.cn/problems/apply-operations-to-an-array/
 *
 * You are given a 0-indexed array nums of size n consisting of non-negative integers.
 *
 * You need to apply n - 1 operations to this array where, in the ith operation (0-indexed), you will
 * apply the following on the ith element of nums:
 *
 * If nums[i] == nums[i + 1], then multiply nums[i] by 2 and set nums[i + 1] to 0.
 * Otherwise, you skip this operation.
 *
 * After performing all the operations, shift all the 0's to the end of the array.
 *
 * For example, the array [1,0,2,0,0,1] after shifting all its 0's to the end, is [1,2,1,0,0,0].
 * Return the resulting array.
 *
 * Note that the operations are applied sequentially, not all at once.
 */

public class Solution {

    public int[] applyOperations(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                ans[i] = nums[i] * 2; ans[++i] = 0;
            } else ans[i] = nums[i];
        }

        int left = 0;
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] != 0) ans[left++] = ans[i];
        }
        while (left < nums.length) ans[left++] = 0;

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().applyOperations(new int[]{1,2,2,1,1,0}), new int[]{1,4,2,0,0,0});
        assert Checker.check(new Solution().applyOperations(new int[]{0,1}), new int[]{1,0});
    }

}
