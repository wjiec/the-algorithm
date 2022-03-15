package problem.p309besttimetobuyandsellstockwithcooldown;

/**
 * 309. Best Time to Buy and Sell Stock with Cooldown
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like
 * (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously
 * (i.e., you must sell the stock before you buy again).
 */

public class Solution {

    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][3];
        dp[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]); // 手上持有股票的最大收益
            dp[i][1] = dp[i - 1][0] + prices[i]; // 手上不持有股票，并且处于冷冻期中的累计最大收益
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]); // 手上不持有股票，并且不在冷冻期中的累计最大收益
        }
        return Math.max(dp[prices.length - 1][1], dp[prices.length - 1][2]);
    }

    public static void main(String[] args) {
        assert new Solution().maxProfit(new int[]{1,2,3,0,2}) == 3;
        assert new Solution().maxProfit(new int[]{1}) == 0;
        assert new Solution().maxProfit(new int[]{1}) == 0;
    }

}
