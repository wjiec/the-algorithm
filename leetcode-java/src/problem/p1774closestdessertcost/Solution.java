package problem.p1774closestdessertcost;

import ability.Array;
import common.Tag;

/**
 * 1774. Closest Dessert Cost
 *
 * https://leetcode.cn/problems/closest-dessert-cost/
 *
 * You would like to make dessert and are preparing to buy the ingredients. You have n ice cream
 * base flavors and m types of toppings to choose from.
 *
 * You must follow these rules when making your dessert:
 *
 * There must be exactly one ice cream base.
 * You can add one or more types of topping or have no toppings at all.
 * There are at most two of each type of topping.
 * You are given three inputs:
 *
 * baseCosts, an integer array of length n, where each baseCosts[i] represents the
 * price of the ith ice cream base flavor.
 *
 * toppingCosts, an integer array of length m, where each toppingCosts[i] is the price of one of the ith topping.
 * target, an integer representing your target price for dessert.
 * You want to make a dessert with a total cost as close to target as possible.
 *
 * Return the closest possible cost of the dessert to target. If there are multiple, return the lower one.
 */

public class Solution {

    @Tag({"最接近", "两个数组搭配"})
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        int maxBase = Array.max(baseCosts), maxTopping = Array.max(toppingCosts);
        final int N = 3 * Math.max(Math.max(maxBase, maxTopping), target);

        boolean[] reached = new boolean[N];
        for (var baseCost : baseCosts) reached[baseCost] = true;

        for (var toppingCost : toppingCosts) {
            for (int i = N - 1; i > toppingCost; i--) {
                reached[i] = reached[i] || reached[i - toppingCost];
            }
        }
        if (reached[target]) return target;

        for (var toppingCost : toppingCosts) {
            for (int i = N - 1; i > toppingCost; i--) {
                reached[i] = reached[i] || reached[i - toppingCost];
            }
        }

        if (reached[target]) return target;
        for (int l = target - 1, r = target + 1; l >= 0 || r < N; l--, r++) {
            if (l >= 0 && reached[l]) return l;
            if (r < N && reached[r]) return r;
        }

        return -1; // unreached
    }

    public static void main(String[] args) {
        assert new Solution().closestCost(new int[]{1,7}, new int[]{3,4}, 10) == 10;
        assert new Solution().closestCost(new int[]{2,3}, new int[]{4,5,100}, 18) == 17;
        assert new Solution().closestCost(new int[]{3,10}, new int[]{2,5}, 9) == 8;
        assert new Solution().closestCost(new int[]{10}, new int[]{1}, 1) == 10;
    }

}
