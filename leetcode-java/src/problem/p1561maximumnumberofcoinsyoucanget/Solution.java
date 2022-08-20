package problem.p1561maximumnumberofcoinsyoucanget;

import java.util.Arrays;

/**
 * 1561. Maximum Number of Coins You Can Get
 *
 * https://leetcode.cn/problems/maximum-number-of-coins-you-can-get/
 *
 * There are 3n piles of coins of varying size, you and your friends will take piles of coins as follows:
 *
 * In each step, you will choose any 3 piles of coins (not necessarily consecutive).
 * Of your choice, Alice will pick the pile with the maximum number of coins.
 * You will pick the next pile with the maximum number of coins.
 * Your friend Bob will pick the last pile.
 * Repeat until there are no more piles of coins.
 * Given an array of integers piles where piles[i] is the number of coins in the ith pile.
 *
 * Return the maximum number of coins that you can have.
 */

public class Solution {

    public int maxCoins(int[] piles) {
        Arrays.sort(piles);

        int ans = 0, n = piles.length;
        for (int l = 0, r = n - 1; l < r; l++, r--) {
            ans += piles[--r];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxCoins(new int[]{2,4,1,2,7,8}) == 9;
        assert new Solution().maxCoins(new int[]{2,4,5}) == 4;
        assert new Solution().maxCoins(new int[]{9,8,7,6,5,1,2,3,4}) == 18;
    }

}
