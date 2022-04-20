package problem.p249groupshiftedstrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 249. Group Shifted Strings
 *
 * https://leetcode-cn.com/problems/group-shifted-strings/
 *
 * We can shift a string by shifting each of its letters to its successive letter.
 *
 * For example, "abc" can be shifted to be "bcd".
 * We can keep shifting the string to form a sequence.
 *
 * For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
 * Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.
 */

public class Solution {

    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (var s : strings) {
            String h = hash(s);
            if (!map.containsKey(h)) {
                map.put(h, new ArrayList<>());
            }
            map.get(h).add(s);
        }

        return new ArrayList<>(map.values());
    }

    private String hash(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1, n = s.length(); i < n; i++) {
            sb.append(diff(s.charAt(i), s.charAt(i - 1))).append('|');
        }
        return sb.toString();
    }

    private int diff(char a, char b) {
        return (26 + a - b) % 26;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().groupStrings(new String[]{"abc","bcd","acef","xyz","az","ba","a","z"}));
        System.out.println(new Solution().groupStrings(new String[]{"a"}));
    }

}
