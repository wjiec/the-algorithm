package problem.p122besttimetobuyandsellstockii;

/**
 * 122. Best Time to Buy and Sell Stock II
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve.
 * You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 */

public class Solution {

    // @TODO dp
    public int maxProfit(int[] prices) {
        int min = prices[0], max = 0, rs = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < max) {
                rs += max - min;
                min = prices[i];
                max = 0;
            } else if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] > max) {
                max = prices[i];
            }
        }

        return rs + Math.max(0, max - min);
    }

    public static void main(String[] args) {
        assert new Solution().maxProfit(new int[]{7,1,5,3,6,4}) == 7;
        assert new Solution().maxProfit(new int[]{1,2,3,4,5}) == 4;
        assert new Solution().maxProfit(new int[]{7,6,4,3,1}) == 0;
        assert new Solution().maxProfit(new int[]{6,1,3,2,4,7}) == 7;
    }

}
