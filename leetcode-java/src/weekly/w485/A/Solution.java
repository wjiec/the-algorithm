package weekly.w485.A;

import static ability.AsciiString.isVowel;

/**
 * Q1. Vowel-Consonant Score
 *
 * https://leetcode.cn/contest/weekly-contest-485/problems/vowel-consonant-score/
 *
 * You are given a string s consisting of lowercase English letters, spaces, and digits.
 *
 * Let v be the number of vowels in s and c be the number of consonants in s.
 *
 * A vowel is one of the letters 'a', 'e', 'i', 'o', or 'u', while any other letter
 * in the English alphabet is considered a consonant.
 *
 * The score of the string s is defined as follows:
 *
 * If c > 0, the score = floor(v / c) where floor denotes rounding down to the nearest integer.
 * Otherwise, the score = 0.
 *
 * Return an integer denoting the score of the string.
 */

public class Solution {

    public int vowelConsonantScore(String s) {
        int n = s.length(), v = 0, cc = 0;
        for (var c : s.toCharArray()) {
            if (isVowel(c)) v++;
            else if ('a' <= c && c <= 'z') cc++;
        }
        return cc == 0 ? 0 : (v / cc);
    }

    public static void main(String[] args) {
    }

}
