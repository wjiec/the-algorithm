package weekly.w333.A;

import java.util.ArrayList;
import java.util.List;

/**
 * 2570. Merge Two 2D Arrays by Summing Values
 *
 * https://leetcode.cn/problems/merge-two-2d-arrays-by-summing-values/
 *
 * You are given two 2D integer arrays nums1 and nums2.
 *
 * nums1[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
 * nums2[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
 * Each array contains unique ids and is sorted in ascending order by id.
 *
 * Merge the two arrays into one array that is sorted in ascending
 * order by id, respecting the following conditions:
 *
 * Only ids that appear in at least one of the two arrays should be included in the resulting array.
 *
 * Each id should be included only once and its value should be the sum of the values of this id
 * in the two arrays. If the id does not exist in one of the two arrays then its value in that
 * array is considered to be 0.
 *
 * Return the resulting array. The returned array must be sorted in ascending order by id.
 */

public class Solution {

    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        List<int[]> ans = new ArrayList<>();
        int idx1 = 0, idx2 = 0, n1 = nums1.length, n2 = nums2.length;
        while (idx1 < n1 && idx2 < n2) {
            if (nums1[idx1][0] == nums2[idx2][0]) {
                ans.add(new int[]{nums1[idx1][0], nums1[idx1][1] + nums2[idx2][1]});
                idx1++; idx2++;
            } else if (nums1[idx1][0] < nums2[idx2][0]) {
                ans.add(new int[]{nums1[idx1][0], nums1[idx1][1]});
                idx1++;
            } else {
                ans.add(new int[]{nums2[idx2][0], nums2[idx2][1]});
                idx2++;
            }
        }

        while (idx1 < n1) ans.add(nums1[idx1++]);
        while (idx2 < n2) ans.add(nums2[idx2++]);

        return ans.toArray(new int[0][]);
    }

    public static void main(String[] args) {
    }

}
