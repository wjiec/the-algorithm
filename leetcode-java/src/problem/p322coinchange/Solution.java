package problem.p322coinchange;

import common.PrettyPrinter;

import java.util.Arrays;

/**
 * 322. Coin Change
 *
 * https://leetcode-cn.com/problems/coin-change/
 *
 * You are given an integer array coins representing coins of different denominations
 * and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If that amount of
 * money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 */

public class Solution {

    public int coinChange(int[] coins, int amount) {
        final int MAX_VALUE = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, MAX_VALUE); dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (var coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }

        }
        return dp[amount] == MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        assert new Solution().coinChange(new int[]{1,2,5}, 11) == 3;
        assert new Solution().coinChange(new int[]{2}, 3) == -1;
        assert new Solution().coinChange(new int[]{1}, 0) == 0;
        assert new Solution().coinChange(new int[]{1}, 1) == 1;
    }

}
