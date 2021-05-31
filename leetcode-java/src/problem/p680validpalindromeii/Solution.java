package problem.p680validpalindromeii;

/**
 * 680. Valid Palindrome II
 *
 * https://leetcode-cn.com/problems/valid-palindrome-ii/
 *
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 */

public class Solution {

    public boolean validPalindrome(String s) {
        int n = s.length(), l = 0, r = n - 1, c = 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return isPalindrome(s, l + 1, r) || isPalindrome(s, l, r - 1);
            }

            l++; r--;
        }
        return true;
    }

    private boolean isPalindrome(String s, int l, int r) {
        for (; l < r; l++, r--) {
            if (s.charAt(l) != s.charAt(r)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().validPalindrome("xdddbababeccebababddd");
        assert new Solution().validPalindrome("deeeee");
        assert !new Solution().validPalindrome("abc");
        assert new Solution().validPalindrome("aba");
        assert new Solution().validPalindrome("abca");
    }

}
