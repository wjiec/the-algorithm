package weekly.bw176.B;

import java.util.HashMap;
import java.util.Map;

/**
 * Q2. Number of Prefix Connected Groups
 *
 * https://leetcode.cn/contest/biweekly-contest-176/problems/number-of-prefix-connected-groups/
 *
 * You are given an array of strings words and an integer k.
 *
 * Two words a and b at distinct indices are prefix-connected if a[0..k-1] == b[0..k-1].
 *
 * A connected group is a set of words such that each pair of words is prefix-connected.
 *
 * Return the number of connected groups that contain at least two words, formed from the given words.
 *
 * Note:
 *
 * Words with length less than k cannot join any group and are ignored.
 * Duplicate strings are treated as separate words.
 */

public class Solution {

    public int prefixConnected(String[] words, int k) {
        Map<String, Integer> g = new HashMap<>();
        for (var word : words) {
            if (word.length() < k) continue;
            g.merge(word.substring(0, k), 1, Integer::sum);
        }

        int ans = 0;
        for (var v : g.values()) if (v >= 2) ans++;
        return ans;
    }

    public static void main(String[] args) {
    }

}
