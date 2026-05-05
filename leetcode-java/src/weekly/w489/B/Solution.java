package weekly.w489.B;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Q2. First Element with Unique Frequency
 *
 * https://leetcode.cn/contest/weekly-contest-489/problems/first-element-with-unique-frequency/
 *
 * You are given an integer array nums.
 *
 * Return an integer denoting the first element (scanning from left to right) in nums whose frequency is unique.
 * That is, no other integer appears the same number of times in nums. If there is no such element, return -1.
 */

public class Solution {

    public int firstUniqueFreq(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Set<Integer>> fn = new HashMap<>();
        for (var v : nums) {
            int newFreq = freq.merge(v, 1, Integer::sum);
            if (newFreq != 1) fn.get(newFreq - 1).remove(v);
            fn.computeIfAbsent(newFreq, f -> new HashSet<>()).add(v);
        }

        for (var v : nums) {
            int f = freq.get(v);
            if (fn.get(f).size() == 1) return v;
        }
        return -1;
    }

    public static void main(String[] args) {
    }

}
