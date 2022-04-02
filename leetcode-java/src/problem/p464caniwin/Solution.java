package problem.p464caniwin;

/**
 * 464. Can I Win
 *
 * https://leetcode-cn.com/problems/can-i-win/
 *
 * In the "100 game" two players take turns adding, to a running total, any integer from 1 to 10.
 * The player who first causes the running total to reach or exceed 100 wins.
 *
 * What if we change the game so that players cannot re-use integers?
 *
 * For example, two players might take turns drawing from a common pool of numbers from 1 to 15
 * without replacement until they reach a total >= 100.
 *
 * Given two integers maxChoosableInteger and desiredTotal, return true if the first player to move
 * can force a win, otherwise, return false. Assume both players play optimally.
 */

public class Solution {

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= maxChoosableInteger) return true;
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;

        return dfs(0, desiredTotal, new Boolean[1 << maxChoosableInteger], maxChoosableInteger);
    }

    private boolean dfs(int state, int desired, Boolean[] dp, int maxChoose) {
        if (dp[state] != null) return dp[state];

        for (int i = 1; i <= maxChoose; i++) {
            int bits = 1 << (i - 1);
            if ((state & bits) != 0) continue;

            if (i >= desired || !dfs(state | bits, desired - i, dp, maxChoose)) {
                return dp[state] = true;
            }
        }
        return dp[state] = false;
    }

    public static void main(String[] args) {
        assert !new Solution().canIWin(10, 40);

        assert !new Solution().canIWin(10, 11);
        assert new Solution().canIWin(10, 0);
        assert new Solution().canIWin(10, 1);

        assert !new Solution().canIWin(19, 199);
    }

}
