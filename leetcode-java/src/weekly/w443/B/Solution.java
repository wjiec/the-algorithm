package weekly.w443.B;

/**
 * 3503. Longest Palindrome After Substring Concatenation I
 *
 * https://leetcode.cn/contest/weekly-contest-443/problems/longest-palindrome-after-substring-concatenation-i/
 *
 * You are given two strings, s and t.
 *
 * You can create a new string by selecting a substring from s (possibly empty) and a substring from t (possibly empty), then concatenating them in order.
 *
 * Return the length of the longest palindrome that can be formed this way.
 */

public class Solution {

    public int longestPalindrome(String s, String t) {
        // 从 s 和 t 中各选择一个子字符串, 找到最长的回文串
        int ans = 0;
        for (int l1 = 0; l1 < s.length(); l1++) {
            for (int r1 = l1; r1 <= s.length(); r1++) {
                String s1 = r1 > l1 ? s.substring(l1, r1) : "";

                for (int l2 = 0; l2 < t.length(); l2++) {
                    for (int r2 = l2; r2 <= t.length(); r2++) {
                        String s2 = r2 > l2 ? t.substring(l2, r2) : "";
                        if (isPalindrome(s1 + s2)) {
                            ans = Math.max(ans, s1.length() + s2.length());
                        }
                    }
                }
            }
        }
        return ans;
    }

    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l <= r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        assert new Solution().longestPalindrome("a", "a") == 2;
        assert new Solution().longestPalindrome("abc", "def") == 1;
        assert new Solution().longestPalindrome("b", "aaaa") == 4;
        assert new Solution().longestPalindrome("abcde", "ecdba") == 5;
    }

}
