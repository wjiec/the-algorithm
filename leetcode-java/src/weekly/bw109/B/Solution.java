package weekly.bw109.B;

import ability.AsciiString;

import java.util.Arrays;

/**
 * 2785. Sort Vowels in a String
 *
 * https://leetcode.cn/contest/biweekly-contest-109/problems/sort-vowels-in-a-string/
 *
 * Given a 0-indexed string s, permute s to get a new string t such that:
 *
 * All consonants remain in their original places. More formally, if there is an index i
 * with 0 <= i < s.length such that s[i] is a consonant, then t[i] = s[i].
 *
 * The vowels must be sorted in the nondecreasing order of their ASCII values.
 * More formally, for pairs of indices i, j with 0 <= i < j < s.length such that s[i]
 * and s[j] are vowels, then t[i] must not have a higher ASCII value than t[j].
 *
 * Return the resulting string.
 *
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in lowercase or uppercase.
 * Consonants comprise all letters that are not vowels.
 */

public class Solution {

    public String sortVowels(String s) {
        char[] chars = s.toCharArray();
        StringBuilder vowels = new StringBuilder();
        for (var c : chars) if (AsciiString.isVowel(c)) vowels.append(c);
        char[] sorted = vowels.toString().toCharArray();
        Arrays.sort(sorted);

        for (int i = 0, j = 0; i < chars.length; i++) {
            if (AsciiString.isVowel(chars[i])) {
                chars[i] = sorted[j++];
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
    }

}
