package problem.p2108findfirstpalindromicstringinthearray;

/**
 * 2108. Find First Palindromic String in the Array
 *
 * https://leetcode-cn.com/problems/find-first-palindromic-string-in-the-array/
 *
 * Given an array of strings words, return the first palindromic string in the array.
 *
 * If there is no such string, return an empty string "".
 *
 * A string is palindromic if it reads the same forward and backward.
 */

public class Solution {

    public String firstPalindrome(String[] words) {
        for (var word : words) {
            boolean palindrome = true;
            int l = 0, r = word.length() - 1;
            for (; l < r; l++, r--) {
                if (word.charAt(l) != word.charAt(r)) {
                    palindrome = false;
                    break;
                }
            }

            if (palindrome) return word;
        }
        return "";
    }

    public static void main(String[] args) {
        assert new Solution().firstPalindrome(new String[]{"abc","car","ada","racecar","cool"}).equals("ada");
        assert new Solution().firstPalindrome(new String[]{"notapalindrome","racecar"}).equals("racecar");
        assert new Solution().firstPalindrome(new String[]{"def","ghi"}).equals("");
    }

}
