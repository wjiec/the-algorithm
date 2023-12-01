package weekly.w373.B;

/**
 * 2947. Count Beautiful Substrings I
 *
 * https://leetcode.cn/contest/weekly-contest-373/problems/count-beautiful-substrings-i/
 *
 * You are given a string s and a positive integer k.
 *
 * Let vowels and consonants be the number of vowels and consonants in a string.
 *
 * A string is beautiful if:
 *
 * vowels == consonants.
 * (vowels * consonants) % k == 0, in other terms the multiplication of vowels and consonants is divisible by k.
 * Return the number of non-empty beautiful substrings in the given string s.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 *
 * Consonant letters in English are every letter except vowels.
 */

public class Solution {

    public int beautifulSubstrings(String s, int k) {
        int n = s.length(), ans = 0;
        char[] chars = s.toCharArray();
        boolean[] isVowel = new boolean[n];
        for (int i = 0; i < n; i++) {
            isVowel[i] = "aeiou".indexOf(chars[i]) != -1;
        }

        for (int i = 1; i < n; i++) {
            if ((i * i) % k != 0) continue;

            int vowels = 0, consonants = 0;
            for (int l = 0, r = 0; r < n; r++) {
                if (isVowel[r]) vowels++;
                else consonants++;

                while (vowels > i || consonants > i) {
                    if (isVowel[l++]) vowels--;
                    else consonants--;
                }

                if (vowels == i && consonants == i) ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
