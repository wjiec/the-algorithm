package problem.p638shoppingoffers;

import common.TODO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 638. Shopping Offers
 *
 * https://leetcode-cn.com/problems/shopping-offers/
 *
 * In LeetCode Store, there are n items to sell. Each item has a price. However, there are some special offers,
 * and a special offer consists of one or more different kinds of items with a sale price.
 *
 * You are given an integer array price where price[i] is the price of the ith item, and an integer array
 * needs where needs[i] is the number of pieces of the ith item you want to buy.
 *
 * You are also given an array special where special[i] is of size n + 1 where special[i][j] is the
 * number of pieces of the jth item in the ith offer and special[i][n] (i.e.,
 * the last integer in the array) is the price of the ith offer.
 *
 * Return the lowest price you have to pay for exactly certain items as given,
 * where you could make optimal use of the special offers.
 *
 * You are not allowed to buy more items than you want, even if that would lower the overall price.
 *
 * You could use any of the special offers as many times as you want.
 */

public class Solution {

    private final Map<List<Integer>, Integer> memo = new HashMap<>();

    @TODO
    public int shoppingOffers(List<Integer> price, List<List<Integer>> specials, List<Integer> needs) {
        int n = needs.size();
        List<List<Integer>> filtered = new ArrayList<>();
        for (var special : specials) {
            int count = 0, total = 0;
            for (int i = 0; i < n; i++) {
                count += special.get(i);
                total += price.get(i) * special.get(i);
            }
            if (count > 0 && total > special.get(n)) filtered.add(special);
        }
        return dfs(price, filtered, needs, n);
    }

    private int dfs(List<Integer> price, List<List<Integer>> specials, List<Integer> curr, int n) {
        if (!memo.containsKey(curr)) {
            int minPrice = 0;
            for (int i = 0; i < n; i++) minPrice += curr.get(i) * price.get(i);

            for (var special : specials) {
                int specialPrice = special.get(n);
                List<Integer> next = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    if (special.get(i) > curr.get(i)) break;
                    next.add(curr.get(i) - special.get(i));
                }

                if (next.size() == n) {
                    minPrice = Math.min(minPrice, dfs(price, specials, next, n) + specialPrice);
                }
            }
            memo.put(curr, minPrice);
        }
        return memo.get(curr);
    }

    public static void main(String[] args) {
        assert new Solution().shoppingOffers(List.of(2,5),
            List.of(List.of(3,0,5), List.of(1,2,10)), List.of(3,2)) == 14;

        assert new Solution().shoppingOffers(List.of(2,3,4),
            List.of(List.of(1,1,0,4), List.of(2,2,1,9)), List.of(1,2,1)) == 11;
    }

}
