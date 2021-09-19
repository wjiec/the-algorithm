package daily.d2109192keyskeyboard;

/**
 * 650. 2 Keys Keyboard
 *
 * https://leetcode-cn.com/problems/2-keys-keyboard/
 *
 * There is only one character 'A' on the screen of a notepad. You can perform two operations on this notepad for each step:
 *
 * Copy All: You can copy all the characters present on the screen (a partial copy is not allowed).
 * Paste: You can paste the characters which are copied last time.
 *
 * Given an integer n, return the minimum number of operations to get the character 'A' exactly n times on the screen.
 */

public class Solution {

    public int minSteps(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                if (i % j == 0) {
                    dp[i] = Math.min(dp[i], dp[j] + i / j);
                    dp[i] = Math.min(dp[i], dp[i / j] + j);
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        assert new Solution().minSteps(1) == 0;
        assert new Solution().minSteps(3) == 3;
        assert new Solution().minSteps(5) == 3;
    }

}
