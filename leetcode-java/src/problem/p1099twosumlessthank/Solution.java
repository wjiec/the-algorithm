package problem.p1099twosumlessthank;

import java.util.Arrays;

/**
 * 1099. Two Sum Less Than K
 *
 * https://leetcode-cn.com/problems/two-sum-less-than-k/
 *
 * Given an array nums of integers and integer k, return the maximum sum such that
 * there exists i < j with nums[i] + nums[j] = sum and sum < k.
 *
 * If no i, j exist satisfying this equation, return -1.
 */

public class Solution {

    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0, r = nums.length - 1, sum = -1;
        while (l < r) {
            if (nums[l] + nums[r] >= k) {
                r--;
            } else {
                sum = Math.max(sum, nums[l++] + nums[r]);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        assert new Solution().twoSumLessThanK(new int[]{1,2,4,5}, 6) == 5;

        assert new Solution().twoSumLessThanK(new int[]{34,23,1,24,75,33,54,8}, 60) == 58;
        assert new Solution().twoSumLessThanK(new int[]{10,20,30}, 15) == -1;
    }

}
