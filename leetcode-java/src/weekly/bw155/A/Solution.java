package weekly.bw155.A;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 3527. Find the Most Common Response
 *
 * https://leetcode.cn/contest/biweekly-contest-155/problems/find-the-most-common-response/
 *
 * You are given a 2D string array responses where each responses[i] is an array of strings
 * representing survey responses from the ith day.
 *
 * Return the most common response across all days after removing duplicate responses
 * within each responses[i]. If there is a tie, return the lexicographically smallest response.
 */

public class Solution {

    public String findCommonResponse(List<List<String>> responses) {
        Map<String, Integer> m = new HashMap<>();
        for (var resp : responses) {
            for (var v : new HashSet<>(resp)) m.merge(v, 1, Integer::sum);
        }

        String ans = ""; int freq = 0;
        for (var kv : m.entrySet()) {
            if (kv.getValue() > freq) {
                ans = kv.getKey(); freq = kv.getValue();
            } else if (kv.getValue() == freq && kv.getKey().compareTo(ans) < 0) {
                ans = kv.getKey();
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
