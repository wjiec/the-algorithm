package weekly.bw96.A;

/**
 * 2540. Minimum Common Value
 *
 * https://leetcode.cn/problems/minimum-common-value/
 *
 * Given two integer arrays nums1 and nums2, sorted in non-decreasing order,
 * return the minimum integer common to both arrays. If there is no common
 * integer amongst nums1 and nums2, return -1.
 *
 * Note that an integer is said to be common to nums1 and nums2 if both arrays
 * have at least one occurrence of that integer.
 */

public class Solution {

    public int getCommon(int[] nums1, int[] nums2) {
        for (int a = 0, b = 0; a < nums1.length && b < nums2.length; ) {
            if (nums1[a] == nums2[b]) return nums1[a];
            if (nums1[a] < nums2[b]) a++; else b++;
        }
        return -1;
    }

    public static void main(String[] args) {
    }

}
