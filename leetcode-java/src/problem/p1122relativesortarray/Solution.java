package problem.p1122relativesortarray;

import common.Checker;

/**
 * 1122. Relative Sort Array
 *
 * https://leetcode-cn.com/problems/relative-sort-array/
 *
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 *
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.
 * Elements that do not appear in arr2 should be placed at the end of arr1 in ascending order.
 */

public class Solution {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] count = new int[1001];
        for (var n : arr1) count[n]++;

        int idx = 0;
        int[] ans = new int[arr1.length];
        for (var n : arr2) {
            for (; count[n] > 0; count[n]--) {
                ans[idx++] = n;
            }
        }

        for (int i = 0; i < count.length; i++) {
            for (; count[i] > 0; count[i]--) {
                ans[idx++] = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().relativeSortArray(
            new int[]{2,3,1,3,2,4,6,7,9,2,19}, new int[]{2,1,4,3,9,6}
        ), new int[]{2,2,2,1,4,3,3,9,6,7,19});
    }

}
