package weekly.w439.B;

import ability.Benchmark;

import java.util.Arrays;

/**
 * 3472. Longest Palindromic Subsequence After at Most K Operations
 *
 * https://leetcode.cn/contest/weekly-contest-439/problems/longest-palindromic-subsequence-after-at-most-k-operations/
 *
 * You are given a string s and an integer k.
 *
 * In one operation, you can replace the character at any position with the next
 * or previous letter in the alphabet (wrapping around so that 'a' is after 'z').
 *
 * For example, replacing 'a' with the next letter results in 'b', and replacing 'a'
 * with the previous letter results in 'z'. Similarly, replacing 'z' with the next
 * letter results in 'a', and replacing 'z' with the previous letter results in 'y'.
 *
 * Return the length of the longest palindromic subsequence of s that can be obtained
 * after performing at most k operations.
 */

public class Solution {

    public int longestPalindromicSubsequence(String s, int k) {
        int n = s.length();
        memo = new int[n + 1][n + 1][k + 1];
        for (var mat : memo) for (var row : mat) Arrays.fill(row, -1);

        return longest(s.toCharArray(), 0, s.length() - 1, k);
    }

    private int[][][] memo = null;

    // 从 [l, r] 中修改 k 次后的最长回文子序列
    private int longest(char[] chars, int l, int r, int k) {
        if (r - l + 1 <= 1) return r - l + 1;
        if (memo[l][r][k] != -1) return memo[l][r][k];

        int ans = Math.max(longest(chars, l + 1, r, k), longest(chars, l, r - 1, k));
        // 一次操作只能将字母变成他相邻的其他字母
        // 将 l 和 r 所在位置字母变为相同字母的最小操作次数
        int distance = Math.min(Math.abs(chars[l] - chars[r]), 26 - Math.abs(chars[l] - chars[r]));
        if (distance <= k) ans = Math.max(ans, 2 + longest(chars, l + 1, r - 1, k - distance));

        return memo[l][r][k] = ans;
    }

    public static void main(String[] args) {
        Benchmark.benchmark("", () -> {
            assert new Solution().longestPalindromicSubsequence("eoaomoywvhgftyeadslggdpdsowqyuinzginteozwrpoeuxbaqlx", 87) == 48;
            assert new Solution().longestPalindromicSubsequence("a", 1) == 1;
            assert new Solution().longestPalindromicSubsequence("abced", 2) == 3;
            assert new Solution().longestPalindromicSubsequence("aaazzz", 4) == 6;
        });
    }

}
