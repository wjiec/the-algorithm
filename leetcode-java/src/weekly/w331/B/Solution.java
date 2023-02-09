package weekly.w331.B;

/**
 * 2559. Count Vowel Strings in Ranges
 *
 * https://leetcode.cn/problems/count-vowel-strings-in-ranges/
 *
 * You are given a 0-indexed array of strings words and a 2D array of integers queries.
 *
 * Each query queries[i] = [li, ri] asks us to find the number of strings present
 * in the range li to ri (both inclusive) of words that start and end with a vowel.
 *
 * Return an array ans of size queries.length, where ans[i] is the answer to the ith query.
 *
 * Note that the vowel letters are 'a', 'e', 'i', 'o', and 'u'.
 */

public class Solution {

    // 标记 ASCII 字符集中的所有元音字母
    private final static boolean[] vowels = new boolean[128];
    static { vowels['a'] = vowels['e'] = vowels['i'] = vowels['o'] = vowels['u'] = true; }
    static { vowels['A'] = vowels['E'] = vowels['I'] = vowels['O'] = vowels['U'] = true; }

    // 判断字符是否是元音字符
    public static boolean isVowel(char c) { return vowels[c]; }

    public int[] vowelStrings(String[] words, int[][] queries) {
        int[] counts = new int[words.length + 1];
        for (int i = 1; i <= words.length; i++) {
            int n = words[i - 1].length();
            char a = words[i - 1].charAt(0);
            char z = words[i - 1].charAt(n - 1);

            counts[i] += counts[i - 1];
            if (isVowel(a) && isVowel(z)) counts[i] += 1;
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = counts[queries[i][1] + 1] - counts[queries[i][0]];
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
