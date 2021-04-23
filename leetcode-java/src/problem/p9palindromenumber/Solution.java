package problem.p9palindromenumber;

/**
 * 9. Palindrome Number
 *
 * https://leetcode-cn.com/problems/palindrome-number/
 *
 * Given an integer x, return true if x is palindrome integer.
 *
 * An integer is a palindrome when it reads the same backward as forward.
 * For example, 121 is palindrome while 123 is not.
 */

public class Solution {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        int r = 0;
        for (int t = x; t != 0; t /= 10) {
            r = r * 10 + t % 10;
        }

        return x == r;
    }

    public static void main(String[] args) {
        assert new Solution().isPalindrome(1);
        assert new Solution().isPalindrome(0);
        assert !new Solution().isPalindrome(10);
        assert new Solution().isPalindrome(121);
        assert new Solution().isPalindrome(121);
        assert !new Solution().isPalindrome(123);
        assert !new Solution().isPalindrome(-101);
        assert new Solution().isPalindrome(1001);
        assert !new Solution().isPalindrome(1000021);
    }

}
