package problem.p500keyboardrow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 500. Keyboard Row
 *
 * https://leetcode-cn.com/problems/keyboard-row/
 *
 * Given an array of strings words, return the words that can be typed using letters of
 * the alphabet on only one row of American keyboard like the image below.
 *
 * In the American keyboard:
 *
 * the first row consists of the characters "qwertyuiop",
 * the second row consists of the characters "asdfghjkl", and
 * the third row consists of the characters "zxcvbnm".
 */

public class Solution {
    private byte[] chars = new byte[255];

    public String[] findWords(String[] words) {
        String s0 = "qwertyuiopQWERTYUIOP", s1 = "asdfghjklASDFGHJKL", s2 = "zxcvbnmZXCVBNM";
        for (var c : s0.toCharArray()) chars[c] = 1;
        for (var c : s1.toCharArray()) chars[c] = 2;
        for (var c : s2.toCharArray()) chars[c] = 3;

        List<String> rs = new ArrayList<>();
        for (var s : words) {
            byte v = chars[s.charAt(0)];
            for (var c : s.toCharArray()) {
                if (chars[c] != v) {
                    v = -1;
                    break;
                }
            }

            if (v != -1) {
                rs.add(s);
            }
        }
        return rs.toArray(new String[0]);
    }

    public static void main(String[] args) {
        assert Arrays.equals(new Solution().findWords(new String[]{"Hello","Alaska","Dad","Peace"}), new String[]{"Alaska","Dad"});
        assert Arrays.equals(new Solution().findWords(new String[]{"omk"}), new String[]{});
        assert Arrays.equals(new Solution().findWords(new String[]{"adsdf","sfd"}), new String[]{"adsdf","sfd"});
    }

}
