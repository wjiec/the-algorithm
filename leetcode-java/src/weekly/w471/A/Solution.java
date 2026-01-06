package weekly.w471.A;

import java.util.HashMap;
import java.util.Map;

/**
 * Q1. Sum of Elements With Frequency Divisible by K
 *
 * https://leetcode.cn/contest/weekly-contest-471/problems/sum-of-elements-with-frequency-divisible-by-k/
 *
 * You are given an integer array nums and an integer k.
 *
 * Return an integer denoting the sum of all elements in nums whose
 * frequency is divisible by k, or 0 if there are no such elements.
 *
 * Note: An element is included in the sum exactly as many times as it appears
 * in the array if its total frequency is divisible by k.
 */

public class Solution {

    public int sumDivisibleByK(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        for (var v : nums) m.merge(v, 1, Integer::sum);

        int ans = 0;
        for (var kv : m.entrySet()) {
            if (kv.getValue() % k == 0) ans += kv.getKey() * kv.getValue();
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
