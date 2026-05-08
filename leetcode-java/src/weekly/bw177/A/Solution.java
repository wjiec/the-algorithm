package weekly.bw177.A;

import java.util.HashMap;
import java.util.Map;

/**
 * Q1. Smallest Pair With Different Frequencies
 *
 * https://leetcode.cn/contest/biweekly-contest-177/problems/smallest-pair-with-different-frequencies/
 *
 * You are given an integer array nums.
 *
 * Consider all pairs of distinct values x and y from nums such that:
 *
 * x < y
 * x and y have different frequencies in nums.
 * Among all such pairs:
 *
 * Choose the pair with the smallest possible value of x.
 * If multiple pairs have the same x, choose the one with the smallest possible value of y.
 *
 * Return an integer array [x, y]. If no valid pair exists, return [-1, -1].
 */

public class Solution {

    public int[] minDistinctFreqPair(int[] nums) {
        int[] freq = new int[101];
        for (var v : nums) freq[v]++;

        for (int x = 1; x <= 100; x++) {
            if (freq[x] == 0) continue;
            for (int y = x + 1; y <= 100; y++) {
                if (freq[y] == 0) continue;
                if (freq[x] != freq[y]) return new int[]{x, y};
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
    }

}
