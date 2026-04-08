package weekly.w480.B;

/**
 * Q2. Reverse Words With Same Vowel Count
 *
 * https://leetcode.cn/contest/weekly-contest-480/problems/reverse-words-with-same-vowel-count/
 *
 * You are given a string s consisting of lowercase English words, each separated by a single space.
 *
 * Determine how many vowels appear in the first word. Then, reverse each following word that has the
 * same vowel count. Leave all remaining words unchanged.
 *
 * Return the resulting string.
 *
 * Vowels are 'a', 'e', 'i', 'o', and 'u'.
 */

public class Solution {

    // 标记 ASCII 字符集中的所有元音字母
    private final static boolean[] vowels = new boolean[128];
    static { vowels['a'] = vowels['e'] = vowels['i'] = vowels['o'] = vowels['u'] = true; }
    static { vowels['A'] = vowels['E'] = vowels['I'] = vowels['O'] = vowels['U'] = true; }

    // 判断字符是否是元音字符
    public static boolean isVowel(char c) { return vowels[c]; }

    public String reverseWords(String s) {
        String[] words = s.split(" ");

        int vowels = 0;
        for (var c : words[0].toCharArray()) {
            if (isVowel(c)) vowels++;
        }

        for (int i = 1; i < words.length; i++) {
            int curr = 0;
            for (var c : words[i].toCharArray()) {
                if (isVowel(c)) curr++;
            }
            if (curr == vowels) words[i] = new StringBuilder(words[i]).reverse().toString();
        }

        return String.join(" ", words);
    }

    public static void main(String[] args) {
    }

}
