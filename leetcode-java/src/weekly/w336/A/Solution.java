package weekly.w336.A;

/**
 * 2586. Count the Number of Vowel Strings in Range
 *
 * https://leetcode.cn/problems/count-the-number-of-vowel-strings-in-range/
 *
 * You are given a 0-indexed array of string words and two integers left and right.
 *
 * A string is called a vowel string if it starts with a vowel character and ends
 * with a vowel character where vowel characters are 'a', 'e', 'i', 'o', and 'u'.
 *
 * Return the number of vowel strings words[i] where i belongs to the inclusive range [left, right].
 */

public class Solution {

    public int vowelStrings(String[] words, int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            char start = words[i].charAt(0);
            char end = words[i].charAt(words[i].length() - 1);
            if (isVowel(start) && isVowel(end)) ans++;
        }
        return ans;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static void main(String[] args) {
    }

}
