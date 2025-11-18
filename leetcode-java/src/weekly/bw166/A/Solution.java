package weekly.bw166.A;

import java.util.HashMap;
import java.util.Map;

/**
 * Q1. Majority Frequency Characters
 *
 * https://leetcode.cn/contest/biweekly-contest-166/problems/majority-frequency-characters/
 *
 * You are given a string s consisting of lowercase English letters.
 *
 * The frequency group for a value k is the set of characters that appear exactly k times in s.
 *
 * The majority frequency group is the frequency group that contains the largest number of distinct characters.
 *
 * Return a string containing all characters in the majority frequency group, in any order.
 * If two or more frequency groups tie for that largest size, pick the group whose frequency k is larger.
 */

public class Solution {

    public String majorityFrequencyGroup(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (var c : s.toCharArray()) freq.merge(c, 1, Integer::sum);

        Map<Integer, StringBuilder> group = new HashMap<>();
        for (var kv : freq.entrySet()) group.computeIfAbsent(kv.getValue(), k -> new StringBuilder()).append(kv.getKey());

        String ans = ""; int currFreq = 0;
        for (var kv : group.entrySet()) {
            if (kv.getValue().length() > ans.length()) {
                ans = kv.getValue().toString(); currFreq = kv.getKey();
            } else if (kv.getValue().length() == ans.length() && kv.getKey() > currFreq) {
                ans = kv.getValue().toString(); currFreq = kv.getKey();
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
