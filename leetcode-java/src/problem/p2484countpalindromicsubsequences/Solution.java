package problem.p2484countpalindromicsubsequences;

/**
 * 2484. Count Palindromic Subsequences
 *
 * https://leetcode.cn/problems/count-palindromic-subsequences
 *
 * Given a string of digits s, return the number of palindromic subsequences of s having length 5.
 * Since the answer may be very large, return it modulo 109 + 7.
 *
 * Note:
 *
 * A string is palindromic if it reads the same forward and backward.
 * A subsequence is a string that can be derived from another string by deleting some or no
 * characters without changing the order of the remaining characters.
 */

public class Solution {

    public int countPalindromes(String s) {
        long[] suffix1 = new long[10];
        long[][] suffix2 = new long[10][10];
        for (int i = s.length() - 1; i >= 0; i--) {
            var digit = s.charAt(i) - '0';
            for (int j = 0; j < 10; j++) suffix2[digit][j] += suffix1[j];
            suffix1[digit]++;
        }

        long ans = 0;
        long[] prefix1 = new long[10];
        long[][] prefix2 = new long[10][10];
        for (int i = 0; i < s.length(); i++) {
            var digit = s.charAt(i) - '0';

            suffix1[digit]--;
            for (int j = 0; j < 10; j++) suffix2[digit][j] -= suffix1[j];

            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    ans += prefix2[j][k] * suffix2[j][k];
                }
            }

            for (int j = 0; j < 10; j++) prefix2[digit][j] += prefix1[j];
            prefix1[digit]++;
        }

        return (int) (ans % 1_000_000_007);
    }

    public static void main(String[] args) {
        assert new Solution().countPalindromes("103301") == 2;
        assert new Solution().countPalindromes("0000000") == 21;
        assert new Solution().countPalindromes("9999900000") == 2;
    }

}
