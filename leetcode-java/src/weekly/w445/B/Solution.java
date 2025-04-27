package weekly.w445.B;

/**
 * 3517. Smallest Palindromic Rearrangement I
 *
 * https://leetcode.cn/contest/weekly-contest-445/problems/smallest-palindromic-rearrangement-i/
 *
 * You are given a palindromic string s.
 *
 * Return the lexicographically smallest palindromic permutation of s.
 */

public class Solution {

    public String smallestPalindrome(String s) {
        int[] count = new int[128];
        for (var c : s.toCharArray()) count[c]++;

        StringBuilder sb = new StringBuilder();
        for (char c = 'a'; c <= 'z'; c++) {
            if (count[c] != 0 && count[c] > 1) {
                sb.append(String.valueOf(c).repeat(count[c] / 2));
            }
        }

        String suffix = sb.reverse().toString();
        sb.reverse();
        for (char c = 'a'; c <= 'z'; c++) {
            if (count[c] % 2 == 1) sb.append(c);
        }
        return sb + suffix;
    }

    public static void main(String[] args) {
        assert new Solution().smallestPalindrome("babab").equals("abbba");
    }

}
