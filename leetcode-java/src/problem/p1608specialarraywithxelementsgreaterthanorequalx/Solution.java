package problem.p1608specialarraywithxelementsgreaterthanorequalx;

import java.util.Arrays;

/**
 * 1608. Special Array With X Elements Greater Than or Equal X
 *
 * https://leetcode-cn.com/problems/special-array-with-x-elements-greater-than-or-equal-x/
 *
 * You are given an array nums of non-negative integers. nums is considered special
 * if there exists a number x such that there are exactly x numbers in nums that are greater than or equal to x.
 *
 * Notice that x does not have to be an element in nums.
 *
 * Return x if the array is special, otherwise, return -1. It can be proven that
 * if nums is special, the value for x is unique.
 */

public class Solution {

    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[nums.length - 1 - i] < i && nums[nums.length - i] >= i) {
                return i;
            }
        }
        return nums[0] >= nums.length ? nums.length : -1;
    }

    public static void main(String[] args) {
        assert new Solution().specialArray(new int[]{3,5}) == 2;
        assert new Solution().specialArray(new int[]{0,0}) == -1;
        assert new Solution().specialArray(new int[]{0,4,3,0,4}) == 3;
        assert new Solution().specialArray(new int[]{3,6,7,7,0}) == -1;
    }

}
