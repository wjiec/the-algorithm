package weekly.w300.A;

import java.util.Arrays;

/**
 * 6108. Decode the Message
 *
 * https://leetcode.cn/contest/weekly-contest-300/problems/decode-the-message/
 *
 * You are given the strings key and message, which represent a cipher key and a secret message, respectively.
 *
 * The steps to decode message are as follows:
 *
 * Use the first appearance of all 26 lowercase English letters in key as the order of the substitution table.
 * Align the substitution table with the regular English alphabet.
 * Each letter in message is then substituted using the table.
 * Spaces ' ' are transformed to themselves.
 * For example, given key = "happy boy" (actual key would have at least one instance of each
 * letter in the alphabet), we have the partial substitution table of ('h' -> 'a', 'a' -> 'b', 'p' -> 'c',
 * 'y' -> 'd', 'b' -> 'e', 'o' -> 'f').
 *
 * Return the decoded message.
 */

public class Solution {

    public String decodeMessage(String key, String message) {
        int[] map = new int[128]; int idx = 0;
        Arrays.fill(map, -1);
        for (var c : key.toCharArray()) {
            if (c != ' ' && map[c] == -1) {
                map[c] = idx++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (var c : message.toCharArray()) {
            if (c != ' ') {
                sb.append((char) ('a' + map[c]));
            } else sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().decodeMessage("the quick brown fox jumps over the lazy dog", "vkbs bs t suepuv").equals("this is a secret");
    }

}
