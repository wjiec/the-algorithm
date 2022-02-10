package daily.d210828p1480runningsumof1darray;

import common.Checker;

/**
 * 1480. Running Sum of 1d Array
 *
 * https://leetcode-cn.com/problems/running-sum-of-1d-array/
 *
 * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
 *
 * Return the running sum of nums.
 */

public class Solution {

    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().runningSum(new int[]{1,2,3,4}), new int[]{1,3,6,10});
        assert Checker.check(new Solution().runningSum(new int[]{1,1,1,1,1}), new int[]{1,2,3,4,5});
        assert Checker.check(new Solution().runningSum(new int[]{3,1,2,10,1}), new int[]{3,4,6,16,17});
    }

}
