package problem.p125validpalindrome;

/**
 * 125. Valid Palindrome
 *
 * https://leetcode-cn.com/problems/valid-palindrome/
 *
 * Given a string s, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 */

public class Solution {

    public boolean isPalindrome(String s) {
        int sz = s.length();
        for (int l = 0, r = sz - 1; l < r;) {
            char lc, rc;
            do {
                lc = s.charAt(l++);
            } while (l < sz && !Character.isLetterOrDigit(lc));
            do {
                rc = s.charAt(r--);
            } while (r >= 0 && !Character.isLetterOrDigit(rc));

            if (l < sz && r >=0 && lc != rc && Character.toUpperCase(lc) != Character.toUpperCase(rc)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert !new Solution().isPalindrome("0P");
        assert new Solution().isPalindrome("");
        assert new Solution().isPalindrome(".,");
        assert new Solution().isPalindrome(",,,");
        assert new Solution().isPalindrome("a,,,");
        assert new Solution().isPalindrome(",,,a");
        assert new Solution().isPalindrome("a,,,a");
        assert new Solution().isPalindrome(",,,q,q,,");
        assert !new Solution().isPalindrome("ab");
        assert new Solution().isPalindrome("A man, a plan, a canal: Panama");
        assert !new Solution().isPalindrome("race a car");
    }

}
