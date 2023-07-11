package weekly.w353.C;

/**
 * 2771. Longest Non-decreasing Subarray From Two Arrays
 *
 * https://leetcode.cn/contest/weekly-contest-353/problems/longest-non-decreasing-subarray-from-two-arrays/
 *
 * You are given two 0-indexed integer arrays nums1 and nums2 of length n.
 *
 * Let's define another 0-indexed integer array, nums3, of length n. For each index i in
 * the range [0, n - 1], you can assign either nums1[i] or nums2[i] to nums3[i].
 *
 * Your task is to maximize the length of the longest non-decreasing subarray in nums3
 * by choosing its values optimally.
 *
 * Return an integer representing the length of the longest non-decreasing subarray in nums3.
 *
 * Note: A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public int maxNonDecreasingLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        if (n == 1) return n;

        int[] end1 = new int[n], end2 = new int[n];
        end1[0] = end2[0] = 1;

        int ans = 0;
        for (int i = 1; i < n; i++) {
            end1[i] = end2[i] = 1;
            if (nums1[i] >= nums1[i - 1]) end1[i] = Math.max(end1[i], end1[i - 1] + 1);
            if (nums1[i] >= nums2[i - 1]) end1[i] = Math.max(end1[i], end2[i - 1] + 1);
            if (nums2[i] >= nums2[i - 1]) end2[i] = Math.max(end2[i], end2[i - 1] + 1);
            if (nums2[i] >= nums1[i - 1]) end2[i] = Math.max(end2[i], end1[i - 1] + 1);
            ans = Math.max(ans, Math.max(end1[i], end2[i]));
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
