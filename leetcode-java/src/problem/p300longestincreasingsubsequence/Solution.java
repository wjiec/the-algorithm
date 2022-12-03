package problem.p300longestincreasingsubsequence;

/**
 * 300. Longest Increasing Subsequence
 *
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 *
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements
 * without changing the order of the remaining elements.
 *
 * For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 */

public class Solution {

    public int lengthOfLIS(int[] nums) {
        int ans = 1;
        int[] dp = new int[nums.length]; dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int v = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    v = Math.max(v, dp[j]);
                }
            }
            dp[i] = v + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}) == 4;
        assert new Solution().lengthOfLIS(new int[]{0,1,0,3,2,3}) == 4;
        assert new Solution().lengthOfLIS(new int[]{7,7,7,7,7,7,7}) == 1;
    }

}
