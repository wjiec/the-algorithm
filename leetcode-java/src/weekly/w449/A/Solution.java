package weekly.w449.A;

import java.util.HashMap;
import java.util.Map;

/**
 * Q1. Minimum Deletions for At Most K Distinct Characters
 *
 * https://leetcode.cn/contest/weekly-contest-449/problems/minimum-deletions-for-at-most-k-distinct-characters/
 *
 * You are given a string s consisting of lowercase English letters, and an integer k.
 *
 * Your task is to delete some (possibly none) of the characters in the string so that the
 * number of distinct characters in the resulting string is at most k.
 *
 * Return the minimum number of deletions required to achieve this.
 */

public class Solution {

    public int minDeletion(String s, int k) {
        Map<Character, Integer> m = new HashMap<>();
        for (var c : s.toCharArray()) m.merge(c, 1, Integer::sum);

        int ans = 0;
        while (m.size() > k) {
            char curr = m.keySet().stream().findFirst().get();
            for (var kv : m.entrySet()) {
                if (kv.getValue() < m.get(curr)) {
                    curr = kv.getKey();
                }
            }

            ans += m.remove(curr);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
