package problem.p2002maximumproductofthelengthoftwopalindromicsubsequences;

/**
 * 2002. Maximum Product of the Length of Two Palindromic Subsequences
 *
 * https://leetcode.cn/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/
 *
 * Given a string s, find two disjoint palindromic subsequences of s such that the product of their
 * lengths is maximized. The two subsequences are disjoint if they do not both pick a character at the same index.
 *
 * Return the maximum possible product of the lengths of the two palindromic subsequences.
 *
 * A subsequence is a string that can be derived from another string by deleting some or no characters
 * without changing the order of the remaining characters. A string is palindromic if it reads
 * the same forward and backward.
 */

public class Solution {

    private int ans = 0;

    public int maxProduct(String s) {
        dfs(s.toCharArray(), 0, new char[20], 0, new char[20], 0);
        return ans;
    }

    private void dfs(char[] chars, int x, char[] s1, int i, char[] s2, int j) {
        if (isPalindromic(s1, i) && isPalindromic(s2, j)) {
            ans = Math.max(ans, i * j);
        }
        if (x == chars.length) return;
        dfs(chars, x + 1, s1, i, s2, j);

        s1[i] = chars[x];
        dfs(chars, x + 1, s1, i + 1, s2, j);

        s2[j] = chars[x];
        dfs(chars, x + 1, s1, i, s2, j + 1);
    }

    private boolean isPalindromic(char[] chars, int n) {
        int l = 0, r = n - 1;
        for (; l < r; l++, r--) {
            if (chars[l] != chars[r]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().maxProduct("leetcodecom") == 9;
        assert new Solution().maxProduct("bb") == 1;
        assert new Solution().maxProduct("accbcaxxcxx") == 25;
    }

}
