package problem.p214shortestpalindrome;

/**
 * 214. Shortest Palindrome
 *
 * https://leetcode.cn/problems/shortest-palindrome
 *
 * You are given a string s. You can convert s to a palindrome by adding characters in front of it.
 *
 * Return the shortest palindrome you can find by performing this transformation.
 */

public class Solution {

    public String shortestPalindrome(String s) {
        char[] chars = s.toCharArray();
        for (int i = chars.length / 2; i >= 0; i--) {
            if (checkPalindrome(chars, i, i + 1)) {
                if (i >= chars.length / 2) return s;
                return buildPalindrome(chars, i + i + 2);
            }
            if (checkPalindrome(chars, i, i)) {
                if (i >= chars.length / 2) return s;
                return buildPalindrome(chars, i + i + 1);
            }
        }
        return buildPalindrome(chars, 0);
    }

    private boolean checkPalindrome(char[] chars, int l, int r) {
        if (r >= chars.length || chars[l] != chars[r]) return false;
        while (l - 1 >= 0 && r + 1 < chars.length && chars[l - 1] == chars[r + 1]) {
            l--; r++;
        }
        return l == 0;
    }

    private String buildPalindrome(char[] chars, int r) {
        StringBuilder sb = new StringBuilder();
        for (int i = chars.length - 1; i >= r; i--) sb.append(chars[i]);
        return sb.append(chars).toString();
    }

    private static class KMP {
        public String shortestPalindrome(String s) {
            int n = s.length(), m = n * 2 + 1;
            // 找到正序 s 和倒序 s' 最长真前缀后缀
            // a b b a d # d a b b a
            char[] chars = new char[m];
            for (int i = 0; i < n; i++) {
                chars[i] = chars[m - i - 1] = s.charAt(i);
            }
            // 隔开正序和倒序的两个字符串
            chars[n] = '#';

            int[] next = new int[m];
            for (int i = 1, j = 0; i < m; ) {
                if (chars[i] != chars[j]) {
                    if (j != 0) j = next[j - 1];
                    else next[i++] = j;
                } else next[i++] = ++j;
            }

            int len = next[m - 1];
            StringBuilder sb = new StringBuilder();
            for (int i = n - 1; i >= len; i--) {
                sb.append(s.charAt(i));
            }
            return sb + s;
        }
    }

    public static void main(String[] args) {
        assert new Solution().shortestPalindrome("aabbaad").equals("daabbaad");

        assert new Solution().shortestPalindrome("aacecaaa").equals("aaacecaaa");
        assert new Solution().shortestPalindrome("abcd").equals("dcbabcd");

        assert new Solution().shortestPalindrome("").equals("");
        assert new Solution().shortestPalindrome("a").equals("a");
        assert new Solution().shortestPalindrome("aa").equals("aa");
        assert new Solution().shortestPalindrome("aaa").equals("aaa");
        assert new Solution().shortestPalindrome("aaaa").equals("aaaa");
        assert new Solution().shortestPalindrome("xaaaa").equals("aaaaxaaaa");

        assert new KMP().shortestPalindrome("aabbaad").equals("daabbaad");
        assert new KMP().shortestPalindrome("aacecaaa").equals("aaacecaaa");
        assert new KMP().shortestPalindrome("abcd").equals("dcbabcd");
        assert new KMP().shortestPalindrome("").equals("");
        assert new KMP().shortestPalindrome("a").equals("a");
        assert new KMP().shortestPalindrome("aa").equals("aa");
        assert new KMP().shortestPalindrome("aaa").equals("aaa");
        assert new KMP().shortestPalindrome("aaaa").equals("aaaa");
        assert new KMP().shortestPalindrome("xaaaa").equals("aaaaxaaaa");
    }

}
