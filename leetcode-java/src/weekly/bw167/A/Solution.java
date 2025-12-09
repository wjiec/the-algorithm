package weekly.bw167.A;

/**
 * Q1. Equal Score Substrings
 *
 * https://leetcode.cn/contest/biweekly-contest-167/problems/equal-score-substrings/
 *
 * You are given a string s consisting of lowercase English letters.
 *
 * The score of a string is the sum of the positions of its characters
 * in the alphabet, where 'a' = 1, 'b' = 2, ..., 'z' = 26.
 *
 * Determine whether there exists an index i such that the string can be split
 * into two non-empty substrings s[0..i] and s[(i + 1)..(n - 1)] that have equal scores.
 *
 * Return true if such a split exists, otherwise return false.
 */

public class Solution {

    public boolean scoreBalance(String s) {
        int total = 0, curr = 0;
        for (var c : s.toCharArray()) total += c - 'a' + 1;
        for (var c : s.toCharArray()) {
            curr += c - 'a' + 1;
            if (curr * 2 == total) return true;
        }
        return false;
    }

    public static void main(String[] args) {
    }

}
