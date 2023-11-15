package weekly.w371.C;

/**
 * 2934. Minimum Operations to Maximize Last Elements in Arrays
 *
 * https://leetcode.cn/contest/weekly-contest-371/problems/minimum-operations-to-maximize-last-elements-in-arrays/
 *
 * You are given two 0-indexed integer arrays, nums1 and nums2, both having length n.
 *
 * You are allowed to perform a series of operations (possibly none).
 *
 * In an operation, you select an index i in the range [0, n - 1] and swap the values of nums1[i] and nums2[i].
 *
 * Your task is to find the minimum number of operations required to satisfy the following conditions:
 *
 * nums1[n - 1] is equal to the maximum value among all elements of
 * nums1, i.e., nums1[n - 1] = max(nums1[0], nums1[1], ..., nums1[n - 1]).
 *
 * nums2[n - 1] is equal to the maximum value among all elements of
 * nums2, i.e., nums2[n - 1] = max(nums2[0], nums2[1], ..., nums2[n - 1]).
 *
 * Return an integer denoting the minimum number of operations needed to meet
 * both conditions, or -1 if it is impossible to satisfy both conditions.
 */

public class Solution {

    public int minOperations(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int kp = minOperations(nums1, nums1[n - 1], nums2, nums2[n - 1]);
        int sp = minOperations(nums1, nums2[n - 1], nums2, nums1[n - 1]);
        if (sp != -1) sp++;
        if (kp == -1 && sp == -1) return -1;
        if (kp == -1) return sp;
        if (sp == -1) return kp;
        return Math.min(kp, sp);
    }

    private int minOperations(int[] nums1, int mx1, int[] nums2, int mx2) {
        int swaps = 0, n = nums1.length;
        for (int i = 0; i < n - 1; i++) {
            int v1 = nums1[i], v2 = nums2[i];
            if (v1 > mx1 || v2 > mx2) {
                if (v2 > mx1 || v1 > mx2) return -1;
                swaps++;
            }
        }
        return swaps;
    }

    public static void main(String[] args) {
    }

}
