package problem.p238productofarrayexceptself;

import common.Checker;

/**
 * 238. Product of Array Except Self
 *
 * https://leetcode-cn.com/problems/product-of-array-except-self/
 *
 * Given an integer array nums, return an array answer such that answer[i] is equal to the
 * product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 */

public class Solution {

    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length]; left[0] = 1;
        for (int i = 1; i < nums.length; i++) left[i] = left[i - 1] * nums[i - 1];

        int[] right = new int[nums.length]; right[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) right[i] = right[i + 1] * nums[i + 1];

        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) ans[i] = left[i] * right[i];
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().productExceptSelf(new int[]{1,2,3,4}), new int[]{24,12,8,6});
        assert Checker.check(new Solution().productExceptSelf(new int[]{-1,1,0,-3,3}), new int[]{0,0,9,0,0});
    }

}
