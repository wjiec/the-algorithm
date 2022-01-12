package problem.p5longestpalindromicsubstring;

/**
 * 5. Longest Palindromic Substring
 *
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * Given a string s, return the longest palindromic substring in s.
 */

public class Solution {

    // 每次都尝试从中心往两边扩散并检查长度
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int l = 0, r = 0;
        for (int i = 0; i < s.length(); i++) {
            int len = Math.max(expand(s, i, i), expand(s, i, i + 1));
            if (len > r - l) {
                l = i - (len - 1) / 2;
                r = i + len / 2;
            }
        }
        return s.substring(l, r + 1);
    }

    public int expand(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--; r++;
        }
        return r - l - 1;
    }

    public static void main(String[] args) {
        assert new Solution().longestPalindrome("babad").equals("aba");
        assert new Solution().longestPalindrome("cbbd").equals("bb");
    }

}
