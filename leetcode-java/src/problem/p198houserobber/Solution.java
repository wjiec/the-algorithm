package problem.p198houserobber;

/**
 * 198. House Robber
 *
 * https://leetcode-cn.com/problems/house-robber/
 *
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint
 * stopping you from robbing each of them is that adjacent houses have
 * security systems connected and it will automatically contact
 * the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 */

public class Solution {

    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];

        nums[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            nums[i] = Math.max(nums[i - 1], nums[i - 2] + nums[i]);
        }
        return nums[nums.length - 1];
    }

    public static void main(String[] args) {
        assert new Solution().rob(new int[]{2,1,1,2}) == 4;

        assert new Solution().rob(new int[]{1,2,3,1}) == 4;
        assert new Solution().rob(new int[]{2,7,9,3,1}) == 12;
    }

}
