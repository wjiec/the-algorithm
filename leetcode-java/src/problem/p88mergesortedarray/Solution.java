package problem.p88mergesortedarray;

import java.util.Arrays;

/**
 * 88. Merge Sorted Array
 *
 * https://leetcode-cn.com/problems/merge-sorted-array/
 *
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 *
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has a size equal to m + n such that
 * it has enough space to hold additional elements from nums2.
 */

public class Solution {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int l = m - 1, r = n - 1, i = nums1.length, max;
        while (l >= 0 || r >= 0) {
            if (l == -1) {
                max = nums2[r--];
            } else if (r == -1) {
                max = nums1[l--];
            } else if (nums1[l] > nums2[r]) {
                max = nums1[l--];
            } else {
                max = nums2[r--];
            }

            nums1[--i] = max;
        }
    }

    public static void main(String[] args) {
        int[] v0 = new int[]{1,3,4,0,0};
        new Solution().merge(v0,3, new int[]{2,5}, 2);
        System.out.println(Arrays.toString(v0));

        int[] v1 = new int[]{1,3,4,0,0,0};
        new Solution().merge(v1,3, new int[]{2,4,5}, 3);
        System.out.println(Arrays.toString(v1));
    }

}
