package weekly.w417.B;

/**
 * 100425. Count of Substrings Containing Every Vowel and K Consonants I
 *
 * https://leetcode.cn/contest/weekly-contest-417/problems/count-of-substrings-containing-every-vowel-and-k-consonants-i/
 *
 * You are given a string word and a non-negative integer k.
 *
 * Return the total number of substrings of word that contain every vowel ('a', 'e', 'i', 'o', and 'u')
 * at least once and exactly k consonants.
 */

/** @noinspection DuplicatedCode*/
public class Solution {

    public int countOfSubstrings(String word, int k) {
        int ans = 0;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + k; j < chars.length; j++) {
                if (isOk(chars, i, j, k)) ans++;
            }
        }

        return ans;
    }

    private boolean isOk(char[] chars, int l, int r, int k) {
        int a = 0, e = 0, i = 0, o = 0, u = 0, other = 0;
        for (int x = l; x <= r; x++) {
            switch (chars[x]) {
                case 'a' -> a++;
                case 'e' -> e++;
                case 'i' -> i++;
                case 'o' -> o++;
                case 'u' -> u++;
                default -> other++;
            }
        }
        return a > 0 && e > 0 && i > 0 && o > 0 && u > 0 && other == k;
    }

    public static void main(String[] args) {
        assert new Solution().countOfSubstrings("iqeaouqi", 2) == 3;

        assert new Solution().countOfSubstrings("aeioqq", 1) == 0;
        assert new Solution().countOfSubstrings("aeiou", 0) == 1;
        assert new Solution().countOfSubstrings("ieaouqqieaouqq", 1) == 3;
    }

}
