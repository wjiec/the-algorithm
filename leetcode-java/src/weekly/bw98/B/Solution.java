package weekly.bw98.B;

import java.util.Arrays;

/**
 * 6361. Minimum Score by Changing Two Elements
 *
 * https://leetcode.cn/problems/minimum-score-by-changing-two-elements/
 *
 * You are given a 0-indexed integer array nums.
 *
 * The low score of nums is the minimum value of |nums[i] - nums[j]|
 * over all 0 <= i < j < nums.length.
 *
 * The high score of nums is the maximum value of |nums[i] - nums[j]|
 * over all 0 <= i < j < nums.length.
 *
 * The score of nums is the sum of the high and low scores of nums.
 * To minimize the score of nums, we can change the value of at most two elements of nums.
 *
 * Return the minimum possible score after changing the value of at most two elements of nums.
 *
 * Note that |x| denotes the absolute value of x.
 */

public class Solution {

    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);
        return Math.min(
            nums[nums.length - 2] - nums[1],
            Math.min(
                nums[nums.length - 3] - nums[0],
                nums[nums.length - 1] - nums[2]
            )
        );
    }

    public static void main(String[] args) {
    }

}
