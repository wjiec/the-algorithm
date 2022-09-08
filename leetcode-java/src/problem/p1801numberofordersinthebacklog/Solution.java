package problem.p1801numberofordersinthebacklog;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1801. Number of Orders in the Backlog
 *
 * https://leetcode.cn/problems/number-of-orders-in-the-backlog/
 *
 * You are given a 2D integer array orders, where each orders[i] = [pricei, amounti, orderTypei] denotes
 * that amounti orders have been placed of type orderTypei at the price pricei. The orderTypei is:
 *
 * 0 if it is a batch of buy orders, or
 * 1 if it is a batch of sell orders.
 * Note that orders[i] represents a batch of amounti independent orders with the same price and order type.
 * All orders represented by orders[i] will be placed before all orders represented by orders[i+1] for all valid i.
 *
 * There is a backlog that consists of orders that have not been executed. The backlog is initially empty.
 * When an order is placed, the following happens:
 *
 * If the order is a buy order, you look at the sell order with the smallest price in the backlog.
 * If that sell order's price is smaller than or equal to the current buy order's price, they will
 * match and be executed, and that sell order will be removed from the backlog. Else, the buy
 * order is added to the backlog.
 * Vice versa, if the order is a sell order, you look at the buy order with the largest price in
 * the backlog. If that buy order's price is larger than or equal to the current sell order's
 * price, they will match and be executed, and that buy order will be removed from the backlog.
 * Else, the sell order is added to the backlog.
 *
 * Return the total amount of orders in the backlog after placing all the orders from the input.
 * Since this number can be large, return it modulo 109 + 7.
 */

public class Solution {

    public int getNumberOfBacklogOrders(int[][] orders) {
        // [price, amount]
        PriorityQueue<int[]> maxBuy = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        PriorityQueue<int[]> minSell = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        for (var order : orders) {
            // [price, amount, order_type]
            int price = order[0], amount = order[1];
            if (order[2] == 0) { // buy
                while (amount > 0 && !minSell.isEmpty() && minSell.peek()[0] <= price) {
                    if (minSell.peek()[1] <= amount) amount -= minSell.remove()[1];
                    else { minSell.peek()[1] -= amount; amount = 0; }
                }
                if (amount > 0) maxBuy.add(new int[]{price, amount});
            } else { // sell
                while (amount > 0 && !maxBuy.isEmpty() && maxBuy.peek()[0] >= price) {
                    if (maxBuy.peek()[1] <= amount) amount -= maxBuy.remove()[1];
                    else { maxBuy.peek()[1] -= amount; amount = 0; }
                }
                if (amount > 0) minSell.add(new int[]{price, amount});
            }
        }

        int ans = 0, MOD = 1_000_000_007;
        while (!maxBuy.isEmpty()) ans = (ans + maxBuy.remove()[1]) % MOD;
        while (!minSell.isEmpty()) ans = (ans + minSell.remove()[1]) % MOD;

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().getNumberOfBacklogOrders(new int[][]{{10,5,0},{15,2,1},{25,1,1},{30,4,0}}) == 6;
        assert new Solution().getNumberOfBacklogOrders(new int[][]{{7,1000000000,1},{15,3,0},{5,999999995,0},{5,1,1}}) == 999999984;
    }

}
