package weekly.w472.A;

import java.util.HashSet;
import java.util.Set;

/**
 * Q1. Smallest Missing Multiple of K
 *
 * https://leetcode.cn/contest/weekly-contest-472/problems/smallest-missing-multiple-of-k/
 *
 * Given an integer array nums and an integer k, return the smallest positive multiple of k that is missing from nums.
 *
 * A multiple of k is any positive integer divisible by k.
 */

public class Solution {

    public int missingMultiple(int[] nums, int k) {
        Set<Integer> s = new HashSet<>();
        for (var v : nums) s.add(v);

        int ans = k;
        while (s.contains(ans)) ans += k;
        return ans;
    }

    public static void main(String[] args) {
    }

}
