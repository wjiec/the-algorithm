package problem.p1140stonegameii;

/**
 * 1140. Stone Game II
 *
 * https://leetcode.cn/problems/stone-game-ii/
 *
 * Alice and Bob continue their games with piles of stones.  There are a number of piles arranged in a row, and
 * each pile has a positive integer number of stones piles[i].
 *
 * The objective of the game is to end with the most stones.
 *
 * Alice and Bob take turns, with Alice starting first.  Initially, M = 1.
 *
 * On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.
 * Then, we set M = max(M, X).
 *
 * The game continues until all the stones have been taken.
 *
 * Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.
 */

public class Solution {

    public int stoneGameII(int[] piles) {
        int n = piles.length, sum = 0;
        int[][] dp = new int[n][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            sum += piles[i];
            for (int m = 1; m <= n; m++) {
                if (i + 2 * m < n) {
                    // 剩下的堆不能全部取走
                    // 自身可以从 [i + 1, i + 2 * m] 范围内取 (x)
                    // 对方可以从 [i + x] 处开始取
                    // 需要保证自己取的是最多的, 所以取最大值
                    for (int x = 1; x <= 2 * m; x++) {
                        dp[i][m] = Math.max(dp[i][m], sum - dp[i + x][Math.max(m, x)]);
                    }
                } else dp[i][m] = sum;
            }
        }
        return dp[0][1];
    }

    public static void main(String[] args) {
        assert new Solution().stoneGameII(new int[]{2,7,9,4,4}) == 10;
        assert new Solution().stoneGameII(new int[]{1,2,3,4,5,100}) == 104;
    }

}
