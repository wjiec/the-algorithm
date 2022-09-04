package weekly.w309.A;

import java.util.Arrays;

/**
 * 6167. Check Distances Between Same Letters
 *
 * https://leetcode.cn/contest/weekly-contest-309/problems/check-distances-between-same-letters/
 *
 * You are given a 0-indexed string s consisting of only lowercase English letters, where
 * each letter in s appears exactly twice. You are also given a 0-indexed integer array distance of length 26.
 *
 * Each letter in the alphabet is numbered from 0 to 25 (i.e. 'a' -> 0, 'b' -> 1, 'c' -> 2, ... , 'z' -> 25).
 *
 * In a well-spaced string, the number of letters between the two occurrences of the ith letter is distance[i].
 * If the ith letter does not appear in s, then distance[i] can be ignored.
 *
 * Return true if s is a well-spaced string, otherwise return false.
 */

public class Solution {

    public boolean checkDistances(String s, int[] distance) {
        int[] map = new int[128];
        Arrays.fill(map, -1);
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map[chars[i]] == -1) {
                map[chars[i]] = i;
            } else {
                int dist = distance[chars[i] - 'a'] + 1;
                if (i - map[chars[i]] != dist) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
