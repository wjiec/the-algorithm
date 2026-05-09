package weekly.w491.A;

/**
 * Q1. Trim Trailing Vowels
 *
 * https://leetcode.cn/contest/weekly-contest-491/problems/trim-trailing-vowels/
 *
 * You are given a string s that consists of lowercase English letters.
 *
 * Return the string obtained by removing all trailing vowels from s.
 *
 * The vowels consist of the characters 'a', 'e', 'i', 'o', and 'u'.
 */

public class Solution {

    public String trimTrailingVowels(String s) {
        char[] chars = s.toCharArray(); int n = s.length() - 1;
        while (n >= 0 && (chars[n] == 'a' || chars[n] == 'e' || chars[n] == 'i' || chars[n] == 'o' || chars[n] == 'u')) n--;
        return new String(chars, 0, n + 1);
    }

    public static void main(String[] args) {
    }

}
