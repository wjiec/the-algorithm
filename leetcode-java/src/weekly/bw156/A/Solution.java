package weekly.bw156.A;

/**
 * Q1. Find Most Frequent Vowel and Consonant
 *
 * https://leetcode.cn/contest/biweekly-contest-156/problems/find-most-frequent-vowel-and-consonant
 *
 * You are given a string s consisting of lowercase English letters ('a' to 'z').
 *
 * Your task is to:
 *
 * Find the vowel (one of 'a', 'e', 'i', 'o', or 'u') with the maximum frequency.
 * Find the consonant (all other letters excluding vowels) with the maximum frequency.
 * Return the sum of the two frequencies.
 *
 * Note: If multiple vowels or consonants have the same maximum frequency, you may choose any one of them.
 * If there are no vowels or no consonants in the string, consider their frequency as 0.
 *
 * The frequency of a letter x is the number of times it occurs in the string.
 */

public class Solution {

    public int maxFreqSum(String s) {
        int[] count = new int[128];
        for (var c : s.toCharArray()) count[c]++;

        int a = 0, b = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            if ("aeiou".indexOf(c) != -1) a = Math.max(a, count[c]);
            else b = Math.max(b, count[c]);
        }
        return a + b;
    }

    public static void main(String[] args) {
    }

}
