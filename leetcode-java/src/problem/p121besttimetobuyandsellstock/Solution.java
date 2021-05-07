package problem.p121besttimetobuyandsellstock;

/**
 * 121. Best Time to Buy and Sell Stock
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock
 * and choosing a different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction.
 * If you cannot achieve any profit, return 0.
 */

public class Solution {

    /**
     * 在这个题意下，股票只能在已过去的这段时间内的最低点买入，并在之后的高点卖出。
     *                     ^^^^^^^^^^^^^^^^^^^^^^
     */
    public int maxProfit(int[] prices) {
        int v = prices[0], rs = 0;
        for (int price : prices) {
            if (price < v) {
                v = price;
            } else if (price - v > rs) {
                rs = price - v;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().maxProfit(new int[]{2,4,1}) == 2;
        assert new Solution().maxProfit(new int[]{7,6,4,3,1}) == 0;
        assert new Solution().maxProfit(new int[]{7,1,5,3,6,4}) == 5;
    }

}
