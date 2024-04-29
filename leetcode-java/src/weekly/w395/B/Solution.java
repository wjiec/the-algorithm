package weekly.w395.B;

import java.util.Arrays;

/**
 * 3132. Find the Integer Added to Array II
 *
 * https://leetcode.cn/contest/weekly-contest-395/problems/find-the-integer-added-to-array-ii/
 *
 * You are given two integer arrays nums1 and nums2.
 *
 * From nums1 two elements have been removed, and all other elements have been
 * increased (or decreased in the case of negative) by an integer, represented
 * by the variable x.
 *
 * As a result, nums1 becomes equal to nums2. Two arrays are considered equal
 * when they contain the same integers with the same frequencies.
 *
 * Return the minimum possible integer x that achieves this equivalence.
 */

public class Solution {

    private final int INF = Integer.MAX_VALUE;

    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        for (int i = 0; i < nums1.length; i++) {
            for (int j = i + 1; j < nums1.length; j++) {
                int d = diff(nums1, nums2, i, j);
                if (d != INF) return d;
            }
        }
        return INF;
    }

    private int diff(int[] a1, int[] a2, int a, int b) {
        int d = INF;
        for (int i = 0, j = 0; j < a2.length; i++, j++) {
            while (i == a || i == b) i++;
            if (d == INF) d = a2[j] - a1[i];
            else if (a2[j] - a1[i] != d) return INF;
        }
        return d;
    }

    public static void main(String[] args) {
    }

}
