package weekly.bw105.A;

import java.util.Arrays;

/**
 * 2706. Buy Two Chocolates
 *
 * https://leetcode.cn/contest/biweekly-contest-105/problems/buy-two-chocolates/
 *
 * You are given an integer array prices representing the prices of various chocolates in a store.
 * You are also given a single integer money, which represents your initial amount of money.
 *
 * You must buy exactly two chocolates in such a way that you still have some non-negative leftover money.
 * You would like to minimize the sum of the prices of the two chocolates you buy.
 *
 * Return the amount of money you will have leftover after buying the two chocolates.
 * If there is no way for you to buy two chocolates without ending up in debt, return money.
 * Note that the leftover must be non-negative.
 */

public class Solution {

    public int buyChoco(int[] prices, int money) {
        Arrays.sort(prices);
        if (prices[0] + prices[1] <= money) {
            return money - prices[0] - prices[1];
        }
        return money;
    }

    public static void main(String[] args) {
    }

}
