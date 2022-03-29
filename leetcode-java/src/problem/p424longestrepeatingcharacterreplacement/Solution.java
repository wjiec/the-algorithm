package problem.p424longestrepeatingcharacterreplacement;

/**
 * 424. Longest Repeating Character Replacement
 *
 * https://leetcode-cn.com/problems/longest-repeating-character-replacement/
 *
 * You are given a string s and an integer k. You can choose any character of the string and
 * change it to any other uppercase English character.
 *
 * You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter
 * you can get after performing the above operations.
 */

public class Solution {

    public int characterReplacement(String s, int k) {
        int[] map = new int[128];
        int max = 0, n = s.length(), l = 0, r = 0;
        for (; r < n; r++) {
            max = Math.max(max, ++map[s.charAt(r)]);
            // 窗口只增不减
            if (r - l + 1 - max > k) {
                map[s.charAt(l++)]--;
            }
        }
        return r - l;
    }

    public static void main(String[] args) {
        assert new Solution().characterReplacement("ABAB", 2) == 4;
        assert new Solution().characterReplacement("AABABBA", 1) == 4;
    }

}
