package problem.p1160findwordsthatcanbeformedbycharacters;

/**
 * 1160. Find Words That Can Be Formed by Characters
 *
 * https://leetcode-cn.com/problems/find-words-that-can-be-formed-by-characters/
 *
 * You are given an array of strings words and a string chars.
 *
 * A string is good if it can be formed by characters from chars (each character can only be used once).
 *
 * Return the sum of lengths of all good strings in words.
 */

public class Solution {

    public int countCharacters(String[] words, String chars) {
        int[] map = buildMap(chars.toCharArray());

        int ans = 0;
        for (var word : words) {
            if (check(buildMap(word.toCharArray()), map)) {
                ans += word.length();
            }
        }
        return ans;
    }

    private int[] buildMap(char[] s) {
        int[] map = new int[26];
        for (var c : s) map[c - 'a']++;
        return map;
    }

    private boolean check(int[] word, int[] target) {
        for (int i = 0; i < 26; i++) {
            if (word[i] > target[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().countCharacters(new String[]{"cat","bt","hat","tree"}, "atach") == 6;
        assert new Solution().countCharacters(new String[]{"hello","world","leetcode"}, "welldonehoneyr") == 10;
    }

}
