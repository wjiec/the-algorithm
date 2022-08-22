package problem.p1577numberofwayswheresquareofnumberisequaltoproductoftwonumbers;

import java.util.HashMap;
import java.util.Map;

/**
 * 1577. Number of Ways Where Square of Number Is Equal to Product of Two Numbers
 *
 * https://leetcode.cn/problems/number-of-ways-where-square-of-number-is-equal-to-product-of-two-numbers/
 *
 * Given two arrays of integers nums1 and nums2, return the number of triplets
 * formed (type 1 and type 2) under the following rules:
 *
 * Type 1: Triplet (i, j, k) if nums1[i]2 == nums2[j] * nums2[k] where 0 <= i < nums1.length and 0 <= j < k < nums2.length.
 * Type 2: Triplet (i, j, k) if nums2[i]2 == nums1[j] * nums1[k] where 0 <= i < nums2.length and 0 <= j < k < nums1.length.
 */

public class Solution {

    public int numTriplets(int[] nums1, int[] nums2) {
        Map<Integer, Integer> a = new HashMap<>();
        for (var n : nums1) a.merge(n, 1, Integer::sum);

        Map<Integer, Integer> b = new HashMap<>();
        for (var n : nums2) b.merge(n, 1, Integer::sum);

        return triplets(a, b) + triplets(b, a);
    }

    private int triplets(Map<Integer, Integer> a, Map<Integer, Integer> b) {
        int ans = 0;
        for (var v1 : a.keySet()) {
            int c1 = a.get(v1);
            long square = (long) v1 * v1;

            for (var v2 : b.keySet()) {
                if (square % v2 == 0 && square / v2 <= Integer.MAX_VALUE) {
                    int v3 = (int) (square / v2);
                    if (v2 == v3) {
                        int c2 = b.get(v2);
                        ans += c1 * c2 * (c2 - 1) / 2;
                    } else if (v2 < v3 && b.containsKey(v3)) {
                        ans += c1 * b.get(v2) * b.get(v3);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numTriplets(new int[]{7,4}, new int[]{5,2,8,9}) == 1;
        assert new Solution().numTriplets(new int[]{1,1}, new int[]{1,1,1}) == 9;
        assert new Solution().numTriplets(new int[]{7,7,8,3}, new int[]{1,2,9,7}) == 2;
        assert new Solution().numTriplets(new int[]{4,7,9,11,23}, new int[]{3,5,1024,12,18}) == 0;
    }

}
