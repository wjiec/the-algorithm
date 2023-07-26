package weekly.bw109.C;

import java.util.Arrays;

/**
 * 2786. Visit Array Positions to Maximize Score
 *
 * https://leetcode.cn/contest/biweekly-contest-109/problems/visit-array-positions-to-maximize-score/
 *
 * You are given a 0-indexed integer array nums and a positive integer x.
 *
 * You are initially at position 0 in the array and you can visit other positions
 * according to the following rules:
 *
 * If you are currently in position i, then you can move to any position j such that i < j.
 * For each position i that you visit, you get a score of nums[i].
 *
 * If you move from a position i to a position j and the parities of nums[i] and nums[j]
 * differ, then you lose a score of x.
 *
 * Return the maximum total score you can get.
 *
 * Note that initially you have nums[0] points.
 */

public class Solution {

    public long maxScore(int[] nums, int x) {
        int n = nums.length;
        long[] dp = new long[n];
        Arrays.fill(dp, Long.MIN_VALUE / 2);

        dp[0] = nums[0];
        long ans = nums[0];
        long mx0 = Long.MIN_VALUE / 2, mx1 = Long.MIN_VALUE / 2;
        if (nums[0] % 2 == 0) mx0 = dp[0]; else mx1 = dp[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] % 2 == 0) {
                dp[i] = Math.max(dp[i], mx0 + nums[i]);
                if (nums[i - 1] % 2 == 1) dp[i] = Math.max(dp[i], mx1 - x + nums[i]);
            } else {
                dp[i] = Math.max(dp[i], mx1 + nums[i]);
                if (nums[i - 1] % 2 == 0) dp[i] = Math.max(dp[i], mx0 - x + nums[i]);
            }

            if (nums[i] % 2 == 0) mx0 = Math.max(mx0, dp[i]);
            else mx1 = Math.max(mx1, dp[i]);

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxScore(new int[]{2,3,6,1,9,2}, 5) == 13;
        assert new Solution().maxScore(new int[]{2,4,6,8}, 3) == 20;
    }

}
