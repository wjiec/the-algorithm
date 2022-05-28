package problem.p904fruitintobaskets;

import java.util.HashMap;
import java.util.Map;

/**
 * 904. Fruit Into Baskets
 *
 * https://leetcode.cn/problems/fruit-into-baskets/
 *
 * You are visiting a farm that has a single row of fruit trees arranged from left to right.
 * The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
 *
 * You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
 *
 * You only have two baskets, and each basket can only hold a single type of fruit.
 * There is no limit on the amount of fruit each basket can hold.
 * Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree)
 * while moving to the right. The picked fruits must fit in one of your baskets.
 * Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
 * Given the integer array fruits, return the maximum number of fruits you can pick.
 */

public class Solution {

    public int totalFruit(int[] fruits) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int l = 0, r = 0; r < fruits.length; r++) {
            map.merge(fruits[r], 1, Integer::sum);
            while (map.size() > 2) {
                map.compute(fruits[l++], (k, v) -> v == null || v == 1 ? null : v - 1);
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().totalFruit(new int[]{1,2,1}) == 3;
        assert new Solution().totalFruit(new int[]{0,1,2,2}) == 3;
        assert new Solution().totalFruit(new int[]{1,2,3,2,2}) == 4;
    }

}
