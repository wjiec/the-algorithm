package weekly.w425.B;

import java.util.HashMap;
import java.util.Map;

/**
 * 3365. Rearrange K Substrings to Form Target String
 *
 * https://leetcode.cn/contest/weekly-contest-425/problems/rearrange-k-substrings-to-form-target-string/
 *
 * You are given two strings s and t, both of which are anagrams of each other, and an integer k.
 *
 * Your task is to determine whether it is possible to split the string s into k equal-sized
 * substrings, rearrange the substrings, and concatenate them in any order to create a new
 * string that matches the given string t.
 *
 * Return true if this is possible, otherwise, return false.
 *
 * An anagram is a word or phrase formed by rearranging the letters of a different word or
 * phrase, using all the original letters exactly once.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 */

public class Solution {

    public boolean isPossibleToRearrange(String s, String t, int k) {
        int n = s.length(), seg = n / k;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i += seg) {
            map.merge(s.substring(i, i + seg), 1, Integer::sum);
        }

        for (int i = 0; i < t.length(); i += seg) {
            int v = map.merge(t.substring(i, i + seg), -1, Integer::sum);
            if (v < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
