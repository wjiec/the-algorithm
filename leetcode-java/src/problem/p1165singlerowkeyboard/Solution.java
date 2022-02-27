package problem.p1165singlerowkeyboard;

import java.util.HashMap;
import java.util.Map;

/**
 * 1165. Single-Row Keyboard
 *
 * https://leetcode-cn.com/problems/single-row-keyboard/
 *
 * There is a special keyboard with all keys in a single row.
 *
 * Given a string keyboard of length 26 indicating the layout of the keyboard (indexed from 0 to 25).
 *
 * Initially, your finger is at index 0. To type a character, you have to move your finger to
 * the index of the desired character.
 *
 * The time taken to move your finger from index i to index j is |i - j|.
 *
 * You want to type a string word. Write a function to calculate how much time it takes to type it with one finger.
 */

public class Solution {

    public int calculateTime(String keyboard, String word) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < keyboard.length(); i++) {
            map.put(keyboard.charAt(i), i);
        }

        int curr = 0, ans = 0;
        for (var c : word.toCharArray()) {
            ans += Math.abs(curr - map.get(c));
            curr = map.get(c);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().calculateTime("abcdefghijklmnopqrstuvwxyz", "cba") == 4;
        assert new Solution().calculateTime("pqrstuvwxyzabcdefghijklmno", "leetcode") == 73;
    }

}
