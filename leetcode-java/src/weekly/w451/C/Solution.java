package weekly.w451.C;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * Q3. Maximum Profit from Trading Stocks with Discounts
 *
 * https://leetcode.cn/contest/weekly-contest-451/problems/maximum-profit-from-trading-stocks-with-discounts/
 *
 * You are given an integer n, representing the number of employees in a company.
 * Each employee is assigned a unique ID from 1 to n, and employee 1 is the CEO.
 *
 * You are given two 1-based integer arrays, present and future, each of length n, where:
 *
 * present[i] represents the current price at which the ith employee can buy a stock today.
 * future[i] represents the expected price at which the ith employee can sell the stock tomorrow.
 * The company's hierarchy is represented by a 2D integer array hierarchy, where hierarchy[i] = [ui, vi]
 * means that employee ui is the direct boss of employee vi.
 *
 * Additionally, you have an integer budget representing the total funds available for investment.
 *
 * However, the company has a discount policy: if an employee's direct boss purchases their own stock,
 * then the employee can buy their stock at half the original price (floor(present[v] / 2)).
 *
 * Return the maximum profit that can be achieved without exceeding the given budget.
 *
 * Note:
 *
 * You may buy each stock at most once.
 * You cannot use any profit earned from future stock prices to fund additional
 * investments and must buy only from budget.
 */

public class Solution {

    /** @noinspection unchecked*/
    private final Set<Integer>[] g = new Set[161];
    { Arrays.setAll(g, i -> new TreeSet<>()); }

    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        int[] p1 = new int[n + 1], p2 = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            p1[i] = future[i - 1] - present[i - 1]; // 原价买的情况下可以获得的收益
            p2[i] = future[i - 1] - (present[i - 1] / 2); // 折扣价情况下可以获得的收益
        }

        // 如果 1 原价购买, 则所有子节点都可以获得折扣优惠(背包大小为 budget - present[1], 求 01 背包最大值)
        // dp[i][j] 表示从 [0, i] 使用最多使用 j 预算可以获得的最大利润
        int budget1 = budget - present[0];
        int[][] dp = new int[n + 1][budget1 + 1];
        for (int i = 2; i <= n; i++) {
            int cost = present[i - 1] / 2; // 购买当前节点所需要的花费
            System.arraycopy(dp[i - 1], 0, dp[i], 0, budget1 + 1);
            for (int j = cost; j <= budget1; j++) {
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - cost] + p2[i]);
            }
        }
        int ans1 = p1[1] + dp[n][budget1];

        // 剩下的就是在树上购买的情况(1 不购买), 如果父节点购买, 则子节点获得优惠
        for (var edge : hierarchy) g[edge[0]].add(edge[1]);

        return ans1;
    }

    public static void main(String[] args) {
        assert new Solution().maxProfit(2, new int[]{1,2}, new int[]{4,3}, new int[][]{{1,2}}, 3) == 5;
        assert new Solution().maxProfit(2, new int[]{3,4}, new int[]{5,8}, new int[][]{{1,2}}, 4) == 4;
        assert new Solution().maxProfit(3, new int[]{4,6,8}, new int[]{7,9,11}, new int[][]{{1,2},{1,3}}, 10) == 10;
        assert new Solution().maxProfit(3, new int[]{5,2,3}, new int[]{8,5,6}, new int[][]{{1,2},{2,3}}, 7) == 12;
    }

}
