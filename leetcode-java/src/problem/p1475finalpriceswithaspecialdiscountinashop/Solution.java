package problem.p1475finalpriceswithaspecialdiscountinashop;

import common.Checker;

/**
 * 1475. Final Prices With a Special Discount in a Shop
 *
 * https://leetcode-cn.com/problems/final-prices-with-a-special-discount-in-a-shop/
 *
 * Given the array prices where prices[i] is the price of the ith item in a shop.
 *
 * There is a special discount for items in the shop, if you buy the ith item,
 * then you will receive a discount equivalent to prices[j] where
 * j is the minimum index such that j > i and prices[j] <= prices[i],
 * otherwise, you will not receive any discount at all.
 *
 * Return an array where the ith element is the final price you will
 * pay for the ith item of the shop considering the special discount.
 */

public class Solution {

    public int[] finalPrices(int[] prices) {
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] <= prices[i]) {
                    prices[i] -= prices[j];
                    break;
                }
            }
        }
        return prices;
    }

    public static void main(String[] args) {
        Checker.check(new Solution().finalPrices(new int[]{8,4,6,2,3}), new int[]{4,2,4,2,3});
        Checker.check(new Solution().finalPrices(new int[]{1,2,3,4,5}), new int[]{1,2,3,4,5});
        Checker.check(new Solution().finalPrices(new int[]{10,1,1,6}), new int[]{9,0,1,6});
    }

}
