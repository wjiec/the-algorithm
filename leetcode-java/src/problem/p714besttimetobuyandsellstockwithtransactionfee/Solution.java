package problem.p714besttimetobuyandsellstockwithtransactionfee;

/**
 * 714. Best Time to Buy and Sell Stock with Transaction Fee
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day,
 * and an integer fee representing a transaction fee.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like,
 * but you need to pay the transaction fee for each transaction.
 *
 * Note: You may not engage in multiple transactions simultaneously
 * (i.e., you must sell the stock before you buy again).
 */

public class Solution {

    public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0; dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }

    public static void main(String[] args) {
        assert new Solution().maxProfit(new int[]{1,3,2,8,4,9}, 2) == 8;
        assert new Solution().maxProfit(new int[]{1,3,7,5,10,3}, 3) == 6;
        assert new Solution().maxProfit(new int[]{9,8,7,6,5,4,3}, 3) == 0;
    }

}
