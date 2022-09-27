package problem.p1930uniquelength3palindromicsubsequences;

import java.util.Arrays;

/**
 * 1930. Unique Length-3 Palindromic Subsequences
 *
 * https://leetcode.cn/problems/unique-length-3-palindromic-subsequences/
 *
 * Given a string s, return the number of unique palindromes of length three that are a subsequence of s.
 *
 * Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.
 *
 * A palindrome is a string that reads the same forwards and backwards.
 *
 * A subsequence of a string is a new string generated from the original string with some
 * characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 */

public class Solution {

    public int countPalindromicSubsequence(String s) {
        int ans = 0, n = s.length();
        char[] chars = s.toCharArray();

        int[][] pos = new int[2][128];
        Arrays.fill(pos[0], -1);
        Arrays.fill(pos[1], -1);

        for (int i = 0; i < n; i++) {
            char c = chars[i];
            if (pos[0][c] == -1) pos[0][c] = i;
            else pos[1][c] = i;
        }

        for (int i = 'a'; i <= 'z'; i++) {
            if (pos[0][i] != -1 && pos[1][i] != -1) {
                int cnt = 0;
                boolean[] uniq = new boolean[128];

                int a = pos[0][i], b = pos[1][i];
                for (int j = a + 1; j < b; j++) {
                    if (!uniq[chars[j]]) {
                        uniq[chars[j]] = true;
                        if (++cnt == 26) break;
                    }
                }

                ans += cnt;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countPalindromicSubsequence("aabca") == 3;
        assert new Solution().countPalindromicSubsequence("adc") == 0;
        assert new Solution().countPalindromicSubsequence("bbcbaba") == 4;
    }

}
