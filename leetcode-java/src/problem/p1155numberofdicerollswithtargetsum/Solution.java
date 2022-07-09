package problem.p1155numberofdicerollswithtargetsum;

/**
 * 1155. Number of Dice Rolls With Target Sum
 *
 * https://leetcode.cn/problems/number-of-dice-rolls-with-target-sum/
 *
 * You have n dice and each die has k faces numbered from 1 to k.
 *
 * Given three integers n, k, and target, return the number of possible ways (out of the kn total ways)
 * to roll the dice so the sum of the face-up numbers equals target.
 *
 * Since the answer may be too large, return it modulo 109 + 7.
 */

public class Solution {

    public int numRollsToTarget(int n, int k, int target) {
        int[][] dp = new int[n][target + 1];
        for (int i = 1; i <= Math.min(k, target); i++) dp[0][i] = 1;

        final int MOD = 1_000_000_007;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target; j++) {
                int curr = dp[i - 1][j];
                for (int x = 1; x <= k; x++) {
                    if (j + x <= target) {
                        dp[i][j + x] = (dp[i][j + x] + curr) % MOD;
                    }
                }
            }
        }
        return dp[n - 1][target];
    }

    public static void main(String[] args) {
        assert new Solution().numRollsToTarget(1, 6, 3) == 1;
        assert new Solution().numRollsToTarget(2, 6, 7) == 6;
        assert new Solution().numRollsToTarget(30, 30, 500) == 222616187;
    }

}
