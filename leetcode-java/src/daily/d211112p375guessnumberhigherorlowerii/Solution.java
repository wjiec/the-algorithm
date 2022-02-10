package daily.d211112p375guessnumberhigherorlowerii;

/**
 * 375. Guess Number Higher or Lower II
 *
 * https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/
 *
 * We are playing the Guessing Game. The game will work as follows:
 *
 * I pick a number between 1 and n.
 * You guess a number.
 * If you guess the right number, you win the game.
 * If you guess the wrong number, then I will tell you whether the number I picked is higher or lower,
 * and you will continue guessing.
 * Every time you guess a wrong number x, you will pay x dollars. If you run out of money, you lose the game.
 *
 * Given a particular n, return the minimum amount of money you
 * need to guarantee a win regardless of what number I pick.
 */

public class Solution {

    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                int cost = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    cost = Math.min(cost, k + Math.max(dp[i][k - 1], dp[k + 1][j]));
                }
                dp[i][j] = cost;
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        assert new Solution().getMoneyAmount(10) == 16;
        assert new Solution().getMoneyAmount(1) == 0;
        assert new Solution().getMoneyAmount(2) == 1;
    }

}
