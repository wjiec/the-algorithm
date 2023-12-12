package weekly.bw119.A;

import java.util.HashMap;
import java.util.Map;

/**
 * 2956. Find Common Elements Between Two Arrays
 *
 * https://leetcode.cn/contest/biweekly-contest-119/problems/find-common-elements-between-two-arrays/
 *
 * You are given two 0-indexed integer arrays nums1 and nums2 of sizes n and m, respectively.
 *
 * Consider calculating the following values:
 *
 * The number of indices i such that 0 <= i < n and nums1[i] occurs at least once in nums2.
 * The number of indices i such that 0 <= i < m and nums2[i] occurs at least once in nums1.
 *
 * Return an integer array answer of size 2 containing the two values in the above order.
 */

public class Solution {

    @SuppressWarnings("DuplicatedCode")
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        Map<Integer, Integer> m1 = new HashMap<>();
        for (var v : nums1) m1.merge(v, 1, Integer::sum);

        Map<Integer, Integer> m2 = new HashMap<>();
        for (var v : nums2) m2.merge(v, 1, Integer::sum);

        int c1 = 0, c2 = 0;
        for (var v : nums1) if (m2.containsKey(v)) c1++;
        for (var v : nums2) if (m1.containsKey(v)) c2++;

        return new int[]{c1, c2};
    }

    public static void main(String[] args) {
    }

}
