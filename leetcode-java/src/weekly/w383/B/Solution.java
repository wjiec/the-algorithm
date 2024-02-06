package weekly.w383.B;

/**
 * 3029. Minimum Time to Revert Word to Initial State I
 *
 * https://leetcode.cn/contest/weekly-contest-383/problems/minimum-time-to-revert-word-to-initial-state-i/
 *
 * You are given a 0-indexed string word and an integer k.
 *
 * At every second, you must perform the following operations:
 *
 * Remove the first k characters of word.
 * Add any k characters to the end of word.
 * Note that you do not necessarily need to add the same characters that you removed.
 * However, you must perform both operations at every second.
 *
 * Return the minimum time greater than zero required for word to revert to its initial state.
 */

public class Solution {

    public int minimumTimeToInitialState(String word, int k) {
        if (k == word.length()) return 1;

        char[] chars = word.toCharArray();
        for (int i = k; i < chars.length; i += k) {
            boolean matches = true;
            for (int j = i; j < chars.length; j++) {
                if (chars[j] != chars[j - i]) {
                    matches = false;
                    break;
                }
            }
            if (matches) return i / k;
        }
        return (word.length() + k - 1) / k;
    }

    public static void main(String[] args) {
    }

}
