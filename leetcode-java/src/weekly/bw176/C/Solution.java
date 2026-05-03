package weekly.bw176.C;

/**
 * Q3. House Robber V
 *
 * https://leetcode.cn/contest/biweekly-contest-176/problems/house-robber-v/
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain
 * amount of money stashed and is protected by a security system with a color code.
 *
 * You are given two integer arrays nums and colors, both of length n, where nums[i] is the
 * amount of money in the ith house and colors[i] is the color code of that house.
 *
 * You cannot rob two adjacent houses if they share the same color code.
 *
 * Return the maximum amount of money you can rob.
 */

public class Solution {

    public long rob(int[] nums, int[] colors) {
        // 相邻且颜色相同则不能偷取, 否则就可以偷
        long[] dp = new long[nums.length + 1]; dp[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i + 1] = Math.max(dp[i], (colors[i] == colors[i - 1] ? dp[i - 1] : dp[i]) + nums[i]);
        }
        return dp[nums.length];
    }

    public static void main(String[] args) {
        assert new Solution().rob(new int[]{53,26,23}, new int[]{53,1,1}) == 79;
    }

}
