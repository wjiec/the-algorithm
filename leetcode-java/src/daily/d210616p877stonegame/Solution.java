package daily.d210616p877stonegame;

/**
 * 877. Stone Game
 *
 * https://leetcode-cn.com/problems/stone-game/
 *
 * Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row,
 * and each pile has a positive integer number of stones piles[i].
 *
 * The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.
 *
 * Alex and Lee take turns, with Alex starting first.
 * Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.
 * This continues until there are no more piles left, at which point the person with the most stones wins.
 *
 * Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
 */

public class Solution {

    public boolean stoneGame(int[] piles) {
        int[][] dp = new int[piles.length][piles.length];
        for (int i = 0; i < piles.length; i++) {
            dp[i][i] = piles[i];
        }

        for (int i = piles.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < piles.length; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][piles.length - 1] > 0;
    }

    public static void main(String[] args) {
        assert new Solution().stoneGame(new int[]{3,7,2,3});
        assert new Solution().stoneGame(new int[]{5,3,4,5});
    }

}
