package problem.p1027longestarithmeticsubsequence;

/**
 * 1027. Longest Arithmetic Subsequence
 *
 * https://leetcode.cn/problems/longest-arithmetic-subsequence/
 *
 * Given an array nums of integers, return the length of the longest arithmetic subsequence in nums.
 *
 * Recall that a subsequence of an array nums is a list nums[i1], nums[i2], ..., nums[ik]
 * with 0 <= i1 < i2 < ... < ik <= nums.length - 1, and that a sequence seq is arithmetic
 * if seq[i+1] - seq[i] are all the same value (for 0 <= i < seq.length - 1).
 */

public class Solution {

    public int longestArithSeqLength(int[] nums) {
        int ans = 0;
        int[][] dp = new int[nums.length][1001];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                int d = nums[i] - nums[j] + 500;
                dp[i][d] = dp[j][d] + 1;
                ans = Math.max(ans, dp[i][d]);
            }
        }
        return ans + 1;
    }

    public static void main(String[] args) {
        assert new Solution().longestArithSeqLength(new int[]{3,6,9,12}) == 4;
        assert new Solution().longestArithSeqLength(new int[]{9,4,7,2,10}) == 3;
        assert new Solution().longestArithSeqLength(new int[]{20,1,15,3,10,5,8}) == 4;
    }

}
