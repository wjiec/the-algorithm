package weekly.w353.B;

/**
 * 2770. Maximum Number of Jumps to Reach the Last Index
 *
 * https://leetcode.cn/contest/weekly-contest-353/problems/maximum-number-of-jumps-to-reach-the-last-index/
 *
 * You are given a 0-indexed array nums of n integers and an integer target.
 *
 * You are initially positioned at index 0. In one step, you can jump from index i to any index j such that:
 *
 * 0 <= i < j < n
 * -target <= nums[j] - nums[i] <= target
 * Return the maximum number of jumps you can make to reach index n - 1.
 *
 * If there is no way to reach index n - 1, return -1.
 */

public class Solution {

    // -target <= nums[j] - nums[i] <= target
    // -target + nums[i] <= nums[j] <= target + nums[i]
    public int maximumJumps(int[] nums, int target) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && dp[i] == 0) continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (-target <= nums[j] - nums[i] && nums[j] - nums[i] <= target) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
        }
        return dp[nums.length - 1] == 0 ? -1 : dp[nums.length - 1];
    }

    public static void main(String[] args) {
        assert new Solution().maximumJumps(new int[]{758043978,79060681,785252849,287889790,-983845055,224430896,-477101480}, 1769097904) == 6;
    }

}
