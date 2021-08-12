package daily.d210812p516longestpalindromicsubsequence;

/**
 * 516. Longest Palindromic Subsequence
 *
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 *
 * Given a string s, find the longest palindromic subsequence's length in s.
 *
 * A subsequence is a sequence that can be derived from another sequence by
 * deleting some or no elements without changing the order of the remaining elements.
 */

public class Solution {

    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    public static void main(String[] args) {
        assert new Solution().longestPalindromeSubseq("bbbab") == 4;
        assert new Solution().longestPalindromeSubseq("cbbd") == 2;
    }

}
