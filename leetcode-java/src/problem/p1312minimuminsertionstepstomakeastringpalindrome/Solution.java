package problem.p1312minimuminsertionstepstomakeastringpalindrome;

/**
 * 1312. Minimum Insertion Steps to Make a String Palindrome
 *
 * https://leetcode.cn/problems/minimum-insertion-steps-to-make-a-string-palindrome/
 *
 * Given a string s. In one step you can insert any character at any index of the string.
 *
 * Return the minimum number of steps to make s palindrome.
 *
 * A Palindrome String is one that reads the same backward as well as forward.
 */

public class Solution {

    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        char[] chars = s.toCharArray();
        for (int span = 2; span <= n; span++) {
            for (int i = 0; i <= n - span; i++) {
                int j = i + span - 1;
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                if (chars[i] == chars[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        assert new Solution().minInsertions("zzazz") == 0;
        assert new Solution().minInsertions("mbadm") == 2;
        assert new Solution().minInsertions("leetcode") == 5;
    }

}
