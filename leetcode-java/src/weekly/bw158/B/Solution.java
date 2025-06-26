package weekly.bw158.B;

/**
 * Q2. Best Time to Buy and Sell Stock V
 *
 * https://leetcode.cn/contest/biweekly-contest-158/problems/best-time-to-buy-and-sell-stock-v/
 *
 * You are given an integer array prices where prices[i] is the price of a stock
 * in dollars on the ith day, and an integer k.
 *
 * You are allowed to make at most k transactions, where each transaction can be either of the following:
 *
 * Normal transaction: Buy on day i, then sell on a later day j
 * where i < j. You profit prices[j] - prices[i].
 *
 * Short selling transaction: Sell on day i, then buy back on a later day j
 * where i < j. You profit prices[i] - prices[j].
 *
 * Note that you must complete each transaction before starting another.
 * Additionally, you can't buy or sell on the same day you are selling or
 * buying back as part of a previous transaction.
 *
 * Return the maximum total profit you can earn by making at most k transactions.
 */

public class Solution {

    private final static long INF = Long.MIN_VALUE / 2;
    private final static int NORM_BUY = 0, NORM_SELL = 1;
    private final static int SHORT_BUY = 2, SHORT_SELL = 3;

    public long maximumProfit(int[] prices, int k) {
        int n = prices.length; long ans = INF;
        // dp[x][i][j] 表示在第 i 天执行第 j 个交易的 x 操作时获得的收益
        long[][][] dp = new long[4][n + 1][k + 1];
        // 枚举当前正在执行的第 j 个交易
        for (int j = 1; j <= k; j++) {
            long normSellMax = 0, normBuyMax = INF, shortBuyMax = INF;
            for (int i = 1; i <= n; i++) {
                // 普通交易
                dp[NORM_BUY][i][j] = normSellMax - prices[i - 1];
                dp[NORM_SELL][i][j] = normBuyMax + prices[i - 1];

                // 做空买入操作
                dp[SHORT_BUY][i][j] = normSellMax + prices[i - 1];
                dp[SHORT_SELL][i][j] = shortBuyMax - prices[i - 1];

                normSellMax = Math.max(normSellMax, Math.max(dp[NORM_SELL][i][j - 1], dp[SHORT_SELL][i][j - 1]));
                normBuyMax = Math.max(normBuyMax, dp[NORM_BUY][i][j]);
                shortBuyMax = Math.max(shortBuyMax, dp[SHORT_BUY][i][j]);

                ans = Math.max(ans, Math.max(dp[NORM_SELL][i][j], dp[SHORT_SELL][i][j]));
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumProfit(new int[]{1,7,9,8,2}, 2) == 14;
        assert new Solution().maximumProfit(new int[]{12,16,19,19,8,1,19,13,9}, 3) == 36;
    }

}
