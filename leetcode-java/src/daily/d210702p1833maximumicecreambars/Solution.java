package daily.d210702p1833maximumicecreambars;

import java.util.Arrays;

/**
 * 1833. Maximum Ice Cream Bars
 *
 * https://leetcode-cn.com/problems/maximum-ice-cream-bars/
 *
 * It is a sweltering summer day, and a boy wants to buy some ice cream bars.
 *
 * At the store, there are n ice cream bars. You are given an array costs of length n,
 * where costs[i] is the price of the ith ice cream bar in coins.
 * The boy initially has coins coins to spend, and he wants to buy as many ice cream bars as possible. 
 *
 * Return the maximum number of ice cream bars the boy can buy with coins coins.
 *
 * Note: The boy can buy the ice cream bars in any order.
 */

public class Solution {

    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);

        int ans = 0;
        for (int n : costs) {
            coins -= n;
            if (coins < 0) break;
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxIceCream(new int[]{1,3,2,4,1}, 7) == 4;
        assert new Solution().maxIceCream(new int[]{10,6,8,7,7,8}, 5) == 0;
        assert new Solution().maxIceCream(new int[]{1,6,3,1,2,5}, 20) == 6;
    }

}
