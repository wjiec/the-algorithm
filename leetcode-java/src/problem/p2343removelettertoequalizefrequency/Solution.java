package problem.p2343removelettertoequalizefrequency;

/**
 * 2423. Remove Letter To Equalize Frequency
 *
 * https://leetcode.cn/problems/remove-letter-to-equalize-frequency/
 *
 * You are given a 0-indexed string word, consisting of lowercase English letters.
 * You need to select one index and remove the letter at that index from word so that
 * the frequency of every letter present in word is equal.
 *
 * Return true if it is possible to remove one letter so that the frequency of all letters
 * in word are equal, and false otherwise.
 *
 * Note:
 *
 * The frequency of a letter x is the number of times it occurs in the string.
 * You must remove exactly one letter and cannot chose to do nothing.
 */

public class Solution {

    public boolean equalFrequency(String word) {
        int[] freq = new int[128];
        for (var c : word.toCharArray()) freq[c]++;

        for (int i = 'a'; i <= 'z'; i++) {
            if (freq[i] != 0) {
                freq[i] -= 1;
                boolean ok = true; int target = -1;

                for (int j = 'a'; j <= 'z'; j++) {
                    if (freq[j] == 0) continue;
                    if (target == -1) target = freq[j];
                    ok = ok && target == freq[j];
                }

                freq[i] += 1;
                if (ok) return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        assert new Solution().equalFrequency("abcc");
        assert !new Solution().equalFrequency("aazz");
        assert new Solution().equalFrequency("abc");
        assert new Solution().equalFrequency("ab");
        assert new Solution().equalFrequency("aaaa");
        assert new Solution().equalFrequency("abbcc");
    }

}
