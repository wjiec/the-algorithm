package problem.p2218maximumvalueofkcoinsfrompiles;

import java.util.ArrayList;
import java.util.List;

/**
 * 2218. Maximum Value of K Coins From Piles
 *
 * https://leetcode.cn/problems/maximum-value-of-k-coins-from-piles/
 *
 * There are n piles of coins on a table. Each pile consists of a
 * positive number of coins of assorted denominations.
 *
 * In one move, you can choose any coin on top of any pile, remove it, and add it to your wallet.
 *
 * Given a list piles, where piles[i] is a list of integers denoting the
 * composition of the ith pile from top to bottom, and a positive integer k,
 * return the maximum total value of coins you can have in your wallet
 * if you choose exactly k coins optimally.
 */

public class Solution {

    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int total = 0;
        int[] dp = new int[k + 1];
        for (var pile : piles) {
            int n = pile.size();
            for (int i = 1; i < n; i++) {
                pile.set(i, pile.get(i) + pile.get(i - 1));
            }

            total = Math.min(total + n, k);
            for (int i = total; i > 0; i--) {
                for (int j = 0; j < Math.min(pile.size(), i); j++) {
                    dp[i] = Math.max(dp[i], dp[i - j - 1] + pile.get(j));
                }
            }
        }

        return dp[k];
    }

    public static void main(String[] args) {
        assert new Solution().maxValueOfCoins(List.of(
            new ArrayList<>(List.of(1,100,3)),
            new ArrayList<>(List.of(7,8,9))
        ), 2) == 101;
    }

}
