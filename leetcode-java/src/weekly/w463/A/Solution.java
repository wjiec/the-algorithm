package weekly.w463.A;

/**
 * Q1. Best Time to Buy and Sell Stock using Strategy
 *
 * https://leetcode.cn/contest/weekly-contest-463/problems/best-time-to-buy-and-sell-stock-using-strategy/
 *
 * You are given two integer arrays prices and strategy, where:
 *
 * prices[i] is the price of a given stock on the ith day.
 * strategy[i] represents a trading action on the ith day, where:
 * -1 indicates buying one unit of the stock.
 * 0 indicates holding the stock.
 * 1 indicates selling one unit of the stock.
 *
 * You are also given an even integer k, and may perform at most one modification
 * to strategy. A modification consists of:
 * Selecting exactly k consecutive elements in strategy.
 * Set the first k / 2 elements to 0 (hold).
 * Set the last k / 2 elements to 1 (sell).
 * The profit is defined as the sum of strategy[i] * prices[i] across all days.
 *
 * Return the maximum possible profit you can achieve.
 *
 * Note: There are no constraints on budget or stock ownership, so all buy and sell
 * operations are feasible regardless of past actions.
 */

public class Solution {

    public long maxProfit(int[] prices, int[] strategy, int k) {
        // 选择一个长为 k 的区间, 将前一半改为 0, 后一半改为 1, 求最大的总和
        //  - 定长滑动窗口
        long curr = 0; int n = prices.length;
        for (int i = 0; i < n; i++) {
            curr += (long) prices[i] * strategy[i];
        }

        long ans = curr;
        // 枚举以 r 为右端点的定长滑动窗口
        for (int r = 0; r < n; r++) {
            // 首先将 r 改成 1
            switch (strategy[r]) {
                case 0 -> curr += prices[r];
                case -1 -> curr += 2L * prices[r];
            }

            // 中间位置需要改成 0
            int mid = r - k / 2;
            if (mid >= 0) {
                // 我们已经将其从原始策略改成 1 了, 我们先回滚变为 1 的操作
                switch (strategy[mid]) {
                    case 0 -> curr -= prices[mid];
                    case -1 -> curr -= 2L * prices[mid];
                }
                // 然后再将其从原始策略改成 0
                switch (strategy[mid]) {
                    case 1 -> curr -= prices[mid];
                    case -1 -> curr += prices[mid];
                }
            }

            // 左侧位置需要改回原本的值
            int l = r - k;
            if (l >= 0) {
                switch (strategy[l]) {
                    case 1 -> curr += prices[l];
                    case -1 -> curr -= prices[l];
                }
            }

            // 统计答案
            if (r - k + 1 >= 0) ans = Math.max(ans, curr);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxProfit(new int[]{4,2,8}, new int[]{-1,0,1}, 2) == 10;
        assert new Solution().maxProfit(new int[]{5,4,3}, new int[]{1,1,0}, 2) == 9;
    }

}
