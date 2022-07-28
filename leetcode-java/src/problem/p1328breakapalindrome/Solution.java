package problem.p1328breakapalindrome;

/**
 * 1328. Break a Palindrome
 *
 * https://leetcode.cn/problems/break-a-palindrome/
 *
 * Given a palindromic string of lowercase English letters palindrome, replace exactly one character
 * with any lowercase English letter so that the resulting string is not a palindrome and that
 * it is the lexicographically smallest one possible.
 *
 * Return the resulting string. If there is no way to replace a character to make it
 * not a palindrome, return an empty string.
 *
 * A string a is lexicographically smaller than a string b (of the same length)
 * if in the first position where a and b differ, a has a character strictly smaller
 * than the corresponding character in b. For example, "abcc" is lexicographically
 * smaller than "abcd" because the first position they differ is at the fourth
 * character, and 'c' is smaller than 'd'.
 */

public class Solution {

    public String breakPalindrome(String palindrome) {
        if (palindrome.length() == 1) return "";

        int idx = 0, n = palindrome.length();
        char[] chars = palindrome.toCharArray();
        while (idx < n && chars[idx] == 'a') idx++;
        if (idx == n) chars[n - 1] = 'b';
        else if (n % 2 == 1 && idx == n / 2) {
            ++idx;
            while (idx < n && chars[idx] == 'a') idx++;
            if (idx == n) chars[n - 1] = 'b';
            else chars[idx] = 'a';
        } else chars[idx] = 'a';


        return new String(chars);
    }

    public static void main(String[] args) {
        assert new Solution().breakPalindrome("aabaa").equals("aabab");
        assert new Solution().breakPalindrome("aacaa").equals("aacab");
        assert new Solution().breakPalindrome("aa").equals("ab");

        assert new Solution().breakPalindrome("abccba").equals("aaccba");
        assert new Solution().breakPalindrome("a").equals("");
        assert new Solution().breakPalindrome("a").equals("");
    }

}
