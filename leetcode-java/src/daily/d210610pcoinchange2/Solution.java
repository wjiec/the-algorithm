package daily.d210610pcoinchange2;

import java.util.Arrays;

/**
 * 518. Coin Change 2
 *
 * https://leetcode-cn.com/problems/coin-change-2/
 *
 * You are given an integer array coins representing coins of different denominations
 * and an integer amount representing a total amount of money.
 *
 * Return the number of combinations that make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return 0.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * The answer is guaranteed to fit into a signed 32-bit integer.
 */

public class Solution {

    public int change(int amount, int[] coins) {
        // dp[i] 凑到金额 i 的方案数
        int[] dp = new int[amount + 1]; dp[0] = 1;
        for (var coin : coins) {
            System.out.println(Arrays.toString(dp));
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        assert new Solution().change(5, new int[]{1,2,5}) == 4;
        assert new Solution().change(3, new int[]{2}) == 0;
        assert new Solution().change(10, new int[]{10}) == 1;
    }

}
