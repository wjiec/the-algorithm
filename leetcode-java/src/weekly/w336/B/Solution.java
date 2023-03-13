package weekly.w336.B;

import java.util.Arrays;

/**
 * 2587. Rearrange Array to Maximize Prefix Score
 *
 * https://leetcode.cn/problems/rearrange-array-to-maximize-prefix-score/
 *
 * You are given a 0-indexed integer array nums. You can rearrange the
 * elements of nums to any order (including the given order).
 *
 * Let prefix be the array containing the prefix sums of nums after rearranging it.
 * In other words, prefix[i] is the sum of the elements from 0 to i in nums after rearranging it.
 * The score of nums is the number of positive integers in the array prefix.
 *
 * Return the maximum score you can achieve.
 */

public class Solution {

    public int maxScore(int[] nums) {
        Arrays.sort(nums);
        long ans = 0, curr = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            curr += nums[i];
            if (curr > 0) ans++;
            else break;
        }
        return (int) ans;
    }

    public static void main(String[] args) {
    }

}
