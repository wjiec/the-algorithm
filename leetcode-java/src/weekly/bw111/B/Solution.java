package weekly.bw111.B;

/**
 * 8014. Make String a Subsequence Using Cyclic Increments
 *
 * https://leetcode.cn/contest/biweekly-contest-111/problems/make-string-a-subsequence-using-cyclic-increments/
 *
 * You are given two 0-indexed strings str1 and str2.
 *
 * In an operation, you select a set of indices in str1, and for each index i in the set, increment str1[i] to
 * the next character cyclically. That is 'a' becomes 'b', 'b' becomes 'c', and so on, and 'z' becomes 'a'.
 *
 * Return true if it is possible to make str2 a subsequence of str1 by performing the operation
 * at most once, and false otherwise.
 *
 * Note: A subsequence of a string is a new string that is formed from the original string by
 * deleting some (possibly none) of the characters without disturbing the relative positions of
 * the remaining characters.
 */

public class Solution {

    public boolean canMakeSubsequence(String str1, String str2) {
        char[] s1 = str1.toCharArray(), s2 = str2.toCharArray();
        if (s2.length > s1.length) return false;

        for (int i = 0, j = 0; i < s1.length && j < s2.length; i++) {
            if (s1[i] == s2[j]) j++;
            else if (s1[i] != 'z' && (s1[i] + 1 == s2[j])) j++;
            else if (s1[i] == 'z' && s2[j] == 'a') j++;
            if (j == s2.length) return true;
        }

        return false;
    }

    public static void main(String[] args) {
    }

}
