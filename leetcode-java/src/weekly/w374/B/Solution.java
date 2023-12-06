package weekly.w374.B;

import java.util.Arrays;

/**
 * 2952. Minimum Number of Coins to be Added
 *
 * https://leetcode.cn/contest/weekly-contest-374/problems/minimum-number-of-coins-to-be-added/
 *
 * You are given a 0-indexed integer array coins, representing the
 * values of the coins available, and an integer target.
 *
 * An integer x is obtainable if there exists a subsequence of coins that sums to x.
 *
 * Return the minimum number of coins of any value that need to be added to the
 * array so that every integer in the range [1, target] is obtainable.
 *
 * A subsequence of an array is a new non-empty array that is formed from the
 * original array by deleting some (possibly none) of the elements without
 * disturbing the relative positions of the remaining elements.
 */

public class Solution {

    public int minimumAddedCoins(int[] coins, int target) {
        Arrays.sort(coins);
        int ans = 0;
        for (int i = 0, s = 1; s <= target; ) {
            if (i < coins.length && coins[i] <= s) {
                s += coins[i++];
            } else {
                s *= 2;
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumAddedCoins(new int[]{1,4,10}, 19) == 2;
        assert new Solution().minimumAddedCoins(new int[]{1,4,10,5,7,19}, 19) == 1;
        assert new Solution().minimumAddedCoins(new int[]{1,1,1}, 20) == 3;
    }

}
