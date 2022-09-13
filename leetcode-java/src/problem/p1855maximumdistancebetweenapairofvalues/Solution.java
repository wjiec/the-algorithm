package problem.p1855maximumdistancebetweenapairofvalues;

/**
 * 1855. Maximum Distance Between a Pair of Values
 *
 * https://leetcode.cn/problems/maximum-distance-between-a-pair-of-values/
 *
 * You are given two non-increasing 0-indexed integer arrays nums1 and nums2.
 *
 * A pair of indices (i, j), where 0 <= i < nums1.length and 0 <= j < nums2.length, is valid
 * if both i <= j and nums1[i] <= nums2[j]. The distance of the pair is j - i.
 *
 * Return the maximum distance of any valid pair (i, j). If there are no valid pairs, return 0.
 *
 * An array arr is non-increasing if arr[i-1] >= arr[i] for every 1 <= i < arr.length.
 */

public class Solution {

    public int maxDistance(int[] nums1, int[] nums2) {
        int ans = 1, n = nums1.length;
        for (int i = 0; i < n; i++) {
            int idx = lower(nums2, nums1[i]);
            if (idx > i) ans = Math.max(ans, idx - i);
        }
        return ans - 1;
    }

    // 找到倒序数组中小于 target 的最大下标
    private int lower(int[] array, int target) {
        int l = 0, r = array.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (array[mid] >= target) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    private static class Optimization {
        public int maxDistance(int[] nums1, int[] nums2) {
            int ans = 0, n1 = nums1.length, n2 = nums2.length;
            for (int i = 0, j = 0; i < n1 && j < n2; i++) {
                while (j < n2 && nums2[j] >= nums1[i]) j++;
                ans = Math.max(ans, j - i - 1);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().maxDistance(new int[]{55,30,5,4,2}, new int[]{100,20,10,10,5}) == 2;
        assert new Solution().maxDistance(new int[]{2,2,2}, new int[]{10,10,1}) == 1;
        assert new Solution().maxDistance(new int[]{30,29,19,5}, new int[]{25,25,25,25,25}) == 2;

        assert new Optimization().maxDistance(new int[]{55,30,5,4,2}, new int[]{100,20,10,10,5}) == 2;
        assert new Optimization().maxDistance(new int[]{2,2,2}, new int[]{10,10,1}) == 1;
        assert new Optimization().maxDistance(new int[]{30,29,19,5}, new int[]{25,25,25,25,25}) == 2;
    }

}
