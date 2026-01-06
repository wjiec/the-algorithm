package weekly.w471.B;

import java.util.HashMap;
import java.util.Map;

/**
 * Q2. Longest Balanced Substring I
 *
 * https://leetcode.cn/contest/weekly-contest-471/problems/longest-balanced-substring-i/
 *
 * You are given a string s consisting of lowercase English letters.
 *
 * A substring of s is called balanced if all distinct characters in the substring appear the same number of times.
 *
 * Return the length of the longest balanced substring of s.
 */

public class Solution {

    public int longestBalanced(String s) {
        int ans = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - ans; i++) {
            Map<Character, Integer> freq = new HashMap<>();
            Map<Integer, Integer> freqMap = new HashMap<>();

            for (int j = i; j < chars.length; j++) {
                int newFreq = freq.merge(chars[j], 1, Integer::sum);
                if (newFreq != 1) freqMap.merge(newFreq - 1, -1, (a, b) -> a + b == 0 ? null : a + b);
                freqMap.merge(newFreq, 1, Integer::sum);
                if (freqMap.size() == 1) ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
