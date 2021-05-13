package daily.d210513p1269numberofwaystostayinthesameplaceaftersomesteps;

/**
 * 1269. Number of Ways to Stay in the Same Place After Some Steps
 *
 * https://leetcode-cn.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/
 *
 * You have a pointer at index 0 in an array of size arrLen.
 * At each step, you can move 1 position to the left, 1 position to the right in the array or stay in the same place
 * (The pointer should not be placed outside the array at any time).
 *
 * Given two integers steps and arrLen,
 * return the number of ways such that your pointer still at index 0 after exactly steps steps.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 */

public class Solution {

    public int numWays(int steps, int arrLen) {
        int maxDistance = Math.min(steps / 2, arrLen - 1), mod = 1000000007;
        // dp and base-case, 经过i步之后，指针停留在j的方案数
        int[][] dp = new int[steps + 1][maxDistance + 1]; dp[0][0] = 1;
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j <= maxDistance; j++) {
                // 上一步停留在位置j的方案数, 不走的方案数
                dp[i][j] = dp[i - 1][j];
                // 可以往右走
                if (j >= 1) {
                    // 上一步停留在j-1位置的可以向右走来到j位置
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % mod;
                }
                // 可以向左走
                if (j + 1 <= maxDistance) {
                    // 上一步停留在j+1位置的可以向左走来到j位置
                    dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % mod;
                }
            }
        }
        return dp[steps][0];
    }

    public static void main(String[] args) {
        assert new Solution().numWays(2, 4) == 2;
        assert new Solution().numWays(4, 2) == 8;
        assert new Solution().numWays(3, 2) == 4;
    }

}
