package problem.p1332removepalindromicsubsequences;

/**
 * 1332. Remove Palindromic Subsequences
 *
 * https://leetcode-cn.com/problems/remove-palindromic-subsequences/
 *
 * You are given a string s consisting only of letters 'a' and 'b'.
 * In a single step you can remove one palindromic subsequence from s.
 *
 * Return the minimum number of steps to make the given string empty.
 *
 * A string is a subsequence of a given string if it is generated
 * by deleting some characters of a given string without changing its order.
 *
 * Note that a subsequence does not necessarily need to be contiguous.
 *
 * A string is called palindrome if is one that reads the same backward as well as forward.
 */

public class Solution {

    public int removePalindromeSub(String s) {
        if (s.length() == 0) return 0;
        for (int l = 0, r = s.length() - 1; l < r; l++, r--) {
            if (s.charAt(l) != s.charAt(r)) return 2;
        }
        return 1;
    }

    public static void main(String[] args) {
        assert new Solution().removePalindromeSub("ababa") == 1;
        assert new Solution().removePalindromeSub("abb") == 2;
        assert new Solution().removePalindromeSub("baabb") == 2;
        assert new Solution().removePalindromeSub("baabb") == 2;
        assert new Solution().removePalindromeSub("") == 0;
    }

}
