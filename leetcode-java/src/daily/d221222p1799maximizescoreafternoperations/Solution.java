package daily.d221222p1799maximizescoreafternoperations;

/**
 * 1799. Maximize Score After N Operations
 *
 * https://leetcode.cn/problems/maximize-score-after-n-operations/
 *
 * You are given nums, an array of positive integers of size 2 * n.
 * You must perform n operations on this array.
 *
 * In the ith operation (1-indexed), you will:
 *
 * Choose two elements, x and y.
 * Receive a score of i * gcd(x, y).
 * Remove x and y from nums.
 * Return the maximum score you can receive after performing n operations.
 *
 * The function gcd(x, y) is the greatest common divisor of x and y.
 */

public class Solution {

    public int maxScore(int[] nums) {
        int n = nums.length;
        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                memo[i][j] = gcd(nums[i], nums[j]);
            }
        }

        int[] dp = new int[1 << n];
        for (int i = 1; i < dp.length; i++) {
            int ones = Integer.bitCount(i);
            if (ones % 2 == 1) continue;

            for (int j = 0; j < n; j++) {
                if ((i >> j) % 2 == 1) {
                    for (int k = j + 1; k < n; k++) {
                        if ((i >> k) % 2 == 1) {
                            dp[i] = Math.max(dp[i], dp[i ^  (1 << j) ^ (1 << k)] + ones / 2 * memo[j][k]);
                        }
                    }
                }
            }
        }

        return dp[dp.length - 1];
    }

    // 最大公约数
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        assert new Solution().maxScore(new int[]{1,2}) == 1;
        assert new Solution().maxScore(new int[]{3,4,6,8}) == 11;
        assert new Solution().maxScore(new int[]{1,2,3,4,5,6}) == 14;
    }

}
