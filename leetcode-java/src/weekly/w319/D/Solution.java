package weekly.w319.D;

/**
 * 6236. Maximum Number of Non-overlapping Palindrome Substrings
 *
 * https://leetcode.cn/contest/weekly-contest-319/problems/maximum-number-of-non-overlapping-palindrome-substrings/
 *
 * You are given a string s and a positive integer k.
 *
 * Select a set of non-overlapping substrings from the string s that satisfy the following conditions:
 *
 * The length of each substring is at least k.
 * Each substring is a palindrome.
 * Return the maximum number of substrings in an optimal selection.
 *
 * A substring is a contiguous sequence of characters within a string.
 */

public class Solution {

    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int[] dp = new int[n];
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (i != 0) dp[i] = Math.max(dp[i], dp[i - 1]);

            palindromes(chars, dp, i, i, k);
            palindromes(chars, dp, i - 1, i, k);
        }
        return dp[n - 1];
    }

    private void palindromes(char[] chars, int[] dp, int l, int r, int k) {
        while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
            if (r - l + 1 >= k) {
                int prev = l - 1 >= 0 ? dp[l - 1] : 0;
                dp[r] = Math.max(dp[r], prev + 1);
            }
            l--; r++;
        }
    }

    public static void main(String[] args) {
        assert new Solution().maxPalindromes("fttfjofpnpfydwdwdnns", 2) == 4;

        assert new Solution().maxPalindromes("abaccdbbd", 3) == 2;
        assert new Solution().maxPalindromes("adbcda", 2) == 0;
        assert new Solution().maxPalindromes("abaccdbbd", 1) == 9;
    }

}
