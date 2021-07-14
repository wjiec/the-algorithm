package daily.d210714p1818minimumabsolutesumdifference;

import java.util.Arrays;

/**
 * 1818. Minimum Absolute Sum Difference
 *
 * https://leetcode-cn.com/problems/minimum-absolute-sum-difference/
 *
 * You are given two positive integer arrays nums1 and nums2, both of length n.
 *
 * The absolute sum difference of arrays nums1 and nums2 is defined as the sum of |nums1[i] - nums2[i]|
 * for each 0 <= i < n (0-indexed).
 *
 * You can replace at most one element of nums1 with
 * any other element in nums1 to minimize the absolute sum difference.
 *
 * Return the minimum absolute sum difference after replacing at most one element in the array nums1.
 * Since the answer may be large, return it modulo 109 + 7.
 *
 * |x| is defined as:
 *
 * x if x >= 0, or
 * -x if x < 0.
 */

public class Solution {

    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int ans = 0, mod = (int) (1e9 + 7), l = nums1.length, replace = 0;

        int[] sorted = new int[l];
        System.arraycopy(nums1, 0, sorted, 0, l);
        Arrays.sort(sorted);

        for (int i = 0; i < l; i++) {
            int abs = Math.abs(nums1[i] - nums2[i]);
            ans = (ans + abs) % mod;

            int idx = binarySearch(sorted, nums2[i]);
            if (idx < l) {
                replace = Math.max(replace, abs - (sorted[idx] - nums2[i]));
            }
            if (idx > 0) {
                replace = Math.max(replace, abs - (nums2[i] - sorted[idx - 1]));
            }
        }

        return (ans - replace + mod) % mod;
    }

    private int binarySearch(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;
        if (arr[hi] < target) return arr.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        assert new Solution().minAbsoluteSumDiff(new int[]{1,28,21}, new int[]{9,21,20}) == 9;

        assert new Solution().minAbsoluteSumDiff(new int[]{1,7,5}, new int[]{2,3,5}) == 3;
        assert new Solution().minAbsoluteSumDiff(new int[]{2,4,6,8,10}, new int[]{2,4,6,8,10}) == 0;
        assert new Solution().minAbsoluteSumDiff(new int[]{1,10,4,4,2,7}, new int[]{9,3,5,1,7,4}) == 20;
    }

}
