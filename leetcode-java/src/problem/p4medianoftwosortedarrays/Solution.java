package problem.p4medianoftwosortedarrays;

import common.Checker;

/**
 * 4. Median of Two Sorted Arrays
 *
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/
 *
 * Given two sorted arrays nums1 and nums2 of size m and n respectively,
 * return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 */

public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length;
        if (n % 2 == 1) return indexOf(nums1, nums2, n / 2 + 1);

        double a = indexOf(nums1, nums2, n / 2);
        double b = indexOf(nums1, nums2, n / 2 + 1);
        return (a + b) / 2.0;
    }

    private int indexOf(int[] s1, int[] s2, int k) {
        int n1 = s1.length, n2 = s2.length, l1 = 0, l2 = 0;
        while (true) {
            if (l1 == n1) return s2[l2 + k - 1];
            if (l2 == n2) return s1[l1 + k - 1];
            if (k == 1) return Math.min(s1[l1], s2[l2]);

            int mid1 = Math.min(l1 + k / 2, n1) - 1;
            int mid2 = Math.min(l2 + k / 2, n2) - 1;
            if (s1[mid1] <= s2[mid2]) {
                k -= mid1 - l1 + 1;
                l1 = mid1 + 1;
            } else {
                k -= mid2 - l2 + 1;
                l2 = mid2 + 1;
            }
        }
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findMedianSortedArrays(new int[]{1,3}, new int[]{2}), 2.0);
        assert Checker.check(new Solution().findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}), 2.5);

        assert Checker.check(new Solution().findMedianSortedArrays(new int[]{1,2,3,4}, new int[]{}), 2.5);
        assert Checker.check(new Solution().findMedianSortedArrays(new int[]{}, new int[]{1,2,3,4}), 2.5);
        assert Checker.check(new Solution().findMedianSortedArrays(new int[]{1,2}, new int[]{2,4}), 2.0);
        assert Checker.check(new Solution().findMedianSortedArrays(new int[]{1,3}, new int[]{2,4}), 2.5);
        assert Checker.check(new Solution().findMedianSortedArrays(new int[]{1}, new int[]{2,3,4,5}), 3.0);
        assert Checker.check(new Solution().findMedianSortedArrays(new int[]{1,2,3,4}, new int[]{5}), 3.0);
        assert Checker.check(new Solution().findMedianSortedArrays(new int[]{1,3,5}, new int[]{2,4,6}), 3.5);
    }

}
