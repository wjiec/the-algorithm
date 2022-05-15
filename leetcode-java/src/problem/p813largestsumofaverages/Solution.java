package problem.p813largestsumofaverages;

import common.Checker;
import common.TODO;

/**
 * 813. Largest Sum of Averages
 *
 * https://leetcode.cn/problems/largest-sum-of-averages/
 *
 * You are given an integer array nums and an integer k. You can partition the array
 * into at most k non-empty adjacent subarrays.
 *
 * The score of a partition is the sum of the averages of each subarray.
 *
 * Note that the partition must use every integer in nums,
 * and that the score is not necessarily an integer.
 *
 * Return the maximum score you can achieve of all the possible partitions.
 *
 * Answers within 10-6 of the actual answer will be accepted.
 */

public class Solution {

    @TODO(tips = "DP")
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[] sum = new double[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }

        double[] dp = new double[n];
        for (int i = 0; i < n; i++) {
            dp[i] = (sum[n] - sum[i]) / (n - i);
        }

        for (int i = 0; i < k - 1; i++) {
            for (int j = 0; j < n; j++) {
                for (int x = j + 1; x < n; x++) {
                    dp[j] = Math.max(dp[j], (sum[x] - sum[j]) / (x - j) + dp[x]);
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().largestSumOfAverages(new int[]{9,1,2,3,9}, 3), 20.0);
        assert Checker.check(new Solution().largestSumOfAverages(new int[]{1,2,3,4,5,6,7}, 4), 20.5);
    }

}
