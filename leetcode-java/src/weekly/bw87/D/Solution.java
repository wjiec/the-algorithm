package weekly.bw87.D;

/**
 * 6187. Minimum Money Required Before Transactions
 *
 * https://leetcode.cn/contest/biweekly-contest-87/problems/minimum-money-required-before-transactions/
 *
 * You are given a 0-indexed 2D integer array transactions, where transactions[i] = [costi, cashbacki].
 *
 * The array describes transactions, where each transaction must be completed exactly once in some order.
 * At any given moment, you have a certain amount of money. In order to complete
 * transaction i, money >= costi must hold true. After performing a transaction, money
 * becomes money - costi + cashbacki.
 *
 * Return the minimum amount of money required before any transaction so that all of the
 * transactions can be completed regardless of the order of the transactions.
 */

public class Solution {

    // [cost, cashback]
    public long minimumMoney(int[][] transactions) {
        long maxCost = 0;
        long[] cost = new long[transactions.length];
        for (int i = 0; i < transactions.length; i++) {
            cost[i] = transactions[i][1] - transactions[i][0];
            if (cost[i] < 0) maxCost += cost[i];
        }

        long ans = 0;
        for (int i = 0; i < transactions.length; i++) {
            ans = Math.max(ans, transactions[i][0] - (maxCost + Math.max(-cost[i], 0)));
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumMoney(new int[][]{
            {13,99},{14,76},{18,61},{50,96},{49,63},{16,98},
            {37,19},{87,32},{100,98},{98,26},{86,99},{76,20},
            {5,61},{14,20},{21,83},{33,53},{75,86},{40,62},
            {71,14},{62,61},{34,29},{66,40},{37,39},{14,88},
            {79,53},{53,84},{63,58},{79,27},{24,58},{93,58},
            {56,12},{8,70},{14,76},{58,63},{62,38},{7,33},
            {74,22},{93,77},{74,60},{64,86},{75,89},{58,29},
            {85,65},{50,78},{51,14},{97,92},{98,97},{37,75},
            {35,1},{54,26},{48,28},{2,0},{10,20},{0,31},{8,55},
            {59,53},{71,21},{8,85},{27,48},{26,100},{86,20},
            {26,66},{77,18},{50,1},{59,98},{6,11},{14,28},{99,95},
            {17,49},{18,10},{54,55},{73,7},{50,82},{73,41},{78,43},
            {67,91},{59,62},{100,49}}) == 1260L;
        assert new Solution().minimumMoney(new int[][]{{2,1}, {5,0}, {4,2}}) == 10L;
        assert new Solution().minimumMoney(new int[][]{{3,0}, {0,3}}) == 3L;
    }

}
