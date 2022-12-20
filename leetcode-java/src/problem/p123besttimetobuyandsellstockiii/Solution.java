package problem.p123besttimetobuyandsellstockiii;

/**
 * 123. Best Time to Buy and Sell Stock III
 *
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously
 * (i.e., you must sell the stock before you buy again).
 */

public class Solution {

    public int maxProfit(int[] prices) {
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < prices.length; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);

            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }

        return sell2;
    }

    public static void main(String[] args) {
        assert new Solution().maxProfit(new int[]{3,3,5,0,0,3,1,4}) == 6;
        assert new Solution().maxProfit(new int[]{1,2,3,4,5}) == 4;
        assert new Solution().maxProfit(new int[]{7,6,4,3,1}) == 0;
        assert new Solution().maxProfit(new int[]{1}) == 0;
    }

}
