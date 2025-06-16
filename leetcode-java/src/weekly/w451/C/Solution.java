package weekly.w451.C;

import common.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private final List<Integer>[] g = new List[160];
    { Arrays.setAll(g, i -> new ArrayList<>()); }

    @Tag("树上01背包")
    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        for (var edge : hierarchy) g[edge[0] - 1].add(edge[1] - 1);
        return dfs(0, present, future, budget)[budget][0];
    }

    // 计算当前位于 curr 节点的结果值
    private int[][] dfs(int curr, int[] present, int[] future, int budget) {
        // 首先需要计算从 curr 的所有子节点中能得到的最大利润之和
        //  - step[i][j] 表示前 x 个子节点在 j 情况下(0 = 不购买 curr, 1 = 购买 curr)的最大值
        int[][] step = new int[budget + 1][2];
        // 枚举并计算所有子节点的叠加之后的最佳分配方式
        for (var next : g[curr]) {
            // 我们把之前所有子节点作为一组保存在 step 中, 还需要计算当前子节点自己作为一组的情况
            // 此时就是两个子节点的情况, 我们枚举给前一个分配 lb = [0, budget], 另一个则是 budget - lb
            var nextStep = dfs(next, present, future, budget);
            // 枚举计算所有可能的预算 j
            for (int j = budget; j >= 0; j--) {
                // 枚举在当前子节点左边的一组子节点的预算为 lb
                //  - 则当前子节点分配到的预算就是 budget - lb
                for (int lb = j; lb >= 0; lb--) {
                    // 枚举使用当前节点以及不使用当前节点 curr
                    for (int k = 0; k < 2; k++) {
                        // 此时当子节点分配 j 时, 最大值为左边子节点组的和加上当前子节点的分配之和
                        //  - 我们当前计算的是 j, 会不断从 [0, j] 进行转移, 所以我们对于 lb 需要从大到小进行计算
                        step[j][k] = Math.max(step[j][k], step[lb][k] + nextStep[j - lb][k]);
                    }
                }
            }
        }

        // 接下来考虑 curr 买还是不买
        int[][] ans = new int[budget + 1][2];
        for (int j = 0; j <= budget; j++) {
            for (int k = 0; k < 2; k++) {
                // 如果 k == 0 不买的话, 代价就等于 present[curr]
                // 如果买的话 k == 1, 代价就等于 present[curr] / 2
                int cost = present[curr] / (k + 1);
                if (j >= cost) {
                    // 如果不买 curr, 则需要从 step[j][0] 进行转移
                    // 如果买 curr, 则需要从 step[j - cost][1] 进行转移
                    ans[j][k] = Math.max(step[j][0], step[j - cost][1] + future[curr] - cost);
                } else {
                    // 我们的额度不够, 无法购买 curr
                    ans[j][k] = step[j][0];
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxProfit(4, new int[]{42,9,1,12}, new int[]{35,41,18,46}, new int[][]{{1,3},{1,4},{1,2}}, 94) == 88;

        assert new Solution().maxProfit(2, new int[]{1,2}, new int[]{4,3}, new int[][]{{1,2}}, 3) == 5;
        assert new Solution().maxProfit(2, new int[]{3,4}, new int[]{5,8}, new int[][]{{1,2}}, 4) == 4;
        assert new Solution().maxProfit(3, new int[]{4,6,8}, new int[]{7,9,11}, new int[][]{{1,2},{1,3}}, 10) == 10;
        assert new Solution().maxProfit(3, new int[]{5,2,3}, new int[]{8,5,6}, new int[][]{{1,2},{2,3}}, 7) == 12;
    }

}
