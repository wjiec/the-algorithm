package problem.p213houserobberii;

/**
 * 213. House Robber II
 *
 * https://leetcode-cn.com/problems/house-robber-ii/
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain
 * amount of money stashed. All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one. Meanwhile,
 * adjacent houses have a security system connected, and it will automatically
 * contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 */

public class Solution {

    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];

        int[] dp0 = new int[nums.length];
        dp0[0] = nums[0]; dp0[1] = Math.max(nums[0], nums[1]);
        for (int i = 2, e = nums.length - 1; i < e; i++) {
            dp0[i] = Math.max(dp0[i - 1], dp0[i - 2] + nums[i]);
        }

        int[] dp1 = new int[nums.length];
        dp1[0] = 0; dp1[1] = nums[1];
        for (int i = 2; i < nums.length; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i]);
        }

        return Math.max(dp0[nums.length - 2], dp1[nums.length - 1]);
    }

    public static void main(String[] args) {
        assert new Solution().rob(new int[]{2,3,2}) == 3;
        assert new Solution().rob(new int[]{1,2,3,1}) == 4;
        assert new Solution().rob(new int[]{1,2,3}) == 3;
    }

}
