package weekly.bw118.C;

import java.util.Arrays;

/**
 * 2944. Minimum Number of Coins for Fruits
 *
 * https://leetcode.cn/contest/biweekly-contest-118/problems/minimum-number-of-coins-for-fruits/
 *
 * You are at a fruit market with different types of exotic fruits on display.
 *
 * You are given a 1-indexed array prices, where prices[i] denotes
 * the number of coins needed to purchase the ith fruit.
 *
 * The fruit market has the following offer:
 *
 * If you purchase the ith fruit at prices[i] coins, you can get the next i fruits for free.
 *
 * Note that even if you can take fruit j for free, you can still
 * purchase it for prices[j] coins to receive a new offer.
 *
 * Return the minimum number of coins needed to acquire all the fruits.
 */

public class Solution {

    public int minimumCoins(int[] prices) {
        // 最小代价可重叠覆盖整个数组
        // 如果花费 prices[i], 则可以获得 [i, i + i]
        int[] dp = new int[prices.length + 3];
        for (int i = prices.length; i > 0; i--) {
            int end = i + i; // 可以免费送的水果
            int price = prices[i - 1]; // 当前水果的价格
            // 获得 [i, ] 的所有水果所需要花费的代价
            dp[i] = Integer.MAX_VALUE;
            for (int j = i + 1; j < dp.length && j <= end + 1; j++) {
                dp[i] = Math.min(dp[i], dp[j] + price);
            }
        }
        return dp[1];
    }

    public static void main(String[] args) {
    }

}
