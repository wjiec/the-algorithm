package weekly.bw80.C;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 6097. Match Substring After Replacement
 *
 * https://leetcode.cn/contest/biweekly-contest-80/problems/match-substring-after-replacement/
 *
 * You are given two strings s and sub. You are also given a 2D character array mappings
 * where mappings[i] = [oldi, newi] indicates that you may replace any number of oldi
 * characters of sub with newi.
 * Each character in sub cannot be replaced more than once.
 *
 * Return true if it is possible to make sub a substring of s by replacing zero or more characters
 * according to mappings. Otherwise, return false.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 */

public class Solution {

    private Map<Character, Set<Character>> map = new HashMap<>();

    public boolean matchReplacement(String s, String sub, char[][] mappings) {
        for (var mapping : mappings) {
            map.computeIfAbsent(mapping[0], v -> new HashSet<>())
                .add(mapping[1]);
        }

        int m = s.length(), n = sub.length();
        for (int l = 0, r = n - 1; r < m; l++, r++) {
            boolean matched = true;
            for (int i = l, j = 0; i <= r; i++, j++) {
                char a = s.charAt(i), b = sub.charAt(j);
                if (a != b && (!map.containsKey(b) || !map.get(b).contains(a))) {
                    matched = false;
                    break;
                }
            }
            if (matched) return true;
        }
        return false;
    }

    public static void main(String[] args) {
    }

}
