package daily.d210920p673numberoflongestincreasingsubsequence;

/**
 * 673. Number of Longest Increasing Subsequence
 *
 * https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/
 *
 * Given an integer array nums, return the number of longest increasing subsequences.
 *
 * Notice that the sequence has to be strictly increasing.
 */

public class Solution {

    public int findNumberOfLIS(int[] nums) {
        int ans = 0, max = 0;
        int[][] dp = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = dp[i][1] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j][0] + 1 > dp[i][0]) {
                        dp[i][0] = dp[j][0] + 1;
                        dp[i][1] = dp[j][1];
                    } else if (dp[j][0] + 1 == dp[i][0]) {
                        dp[i][1] += dp[j][1];
                    }
                }
            }

            if (dp[i][0] > max) {
                max = dp[i][0];
                ans = dp[i][1];
            } else if (dp[i][0] == max) {
                ans += dp[i][1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findNumberOfLIS(new int[]{1,3,5,4,7}) == 2;
        assert new Solution().findNumberOfLIS(new int[]{2,2,2,2,2}) == 5;
    }

}
