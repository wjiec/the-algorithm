package problem.p1865findingpairswithacertainsum;

import java.util.HashMap;
import java.util.Map;

/**
 * 1865. Finding Pairs With a Certain Sum
 *
 * https://leetcode.cn/problems/finding-pairs-with-a-certain-sum/
 *
 * You are given two integer arrays nums1 and nums2. You are tasked to
 * implement a data structure that supports queries of two types:
 *
 * Add a positive integer to an element of a given index in the array nums2.
 * Count the number of pairs (i, j) such that nums1[i] + nums2[j] equals a given
 * value (0 <= i < nums1.length and 0 <= j < nums2.length).
 *
 * Implement the FindSumPairs class:
 *
 * FindSumPairs(int[] nums1, int[] nums2) Initializes the FindSumPairs object with two integer arrays nums1 and nums2.
 * void add(int index, int val) Adds val to nums2[index], i.e., apply nums2[index] += val.
 * int count(int tot) Returns the number of pairs (i, j) such that nums1[i] + nums2[j] == tot.
 */

public class Solution {

    private static class FindSumPairs {
        private final int[] s1, s2;
        private final Map<Integer, Integer> map = new HashMap<>();
        public FindSumPairs(int[] nums1, int[] nums2) {
            s1 = nums1; s2 = nums2;
            for (var n : nums2) map.merge(n, 1, Integer::sum);
        }

        public void add(int index, int val) {
            int x = s2[index]; s2[index] += val;
            map.merge(x, 1, (old, v) -> old - v);
            map.merge(x + val, 1, Integer::sum);
        }

        public int count(int tot) {
            int ans = 0;
            for (var v : s1) {
                ans += map.getOrDefault(tot - v, 0);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        FindSumPairs findSumPairs = new FindSumPairs(new int[]{1, 1, 2, 2, 2, 3}, new int[]{1, 4, 5, 2, 5, 4});
        assert findSumPairs.count(7) == 8;
        findSumPairs.add(3, 2);
        assert findSumPairs.count(8) == 2;
        assert findSumPairs.count(4) == 1;
        findSumPairs.add(0, 1);
        findSumPairs.add(1, 1);
        assert findSumPairs.count(7) == 11;
    }

}
