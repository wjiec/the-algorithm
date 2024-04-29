package weekly.w395.A;

import java.util.Arrays;

/**
 * 3131. Find the Integer Added to Array I
 *
 * https://leetcode.cn/contest/weekly-contest-395/problems/find-the-integer-added-to-array-i/
 *
 * You are given two arrays of equal length, nums1 and nums2.
 *
 * Each element in nums1 has been increased (or decreased in the case of negative) by an
 * integer, represented by the variable x.
 *
 * As a result, nums1 becomes equal to nums2. Two arrays are considered equal when they
 * contain the same integers with the same frequencies.
 *
 * Return the integer x.
 */

public class Solution {

    public int addedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        return nums2[0] - nums1[0];
    }

    public static void main(String[] args) {
    }

}
