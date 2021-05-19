package problem.p350intersectionoftwoarraysii;

import common.Checker;

import java.util.Arrays;

/**
 * 350. Intersection of Two Arrays II
 *
 * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
 *
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must appear as many times as it shows
 * in both arrays and you may return the result in any order.
 */

public class Solution {

    public int[] intersect(int[] nums1, int[] nums2) {
        int[] counts = new int[1001];
        int sz1 = nums1.length, sz2 = nums2.length, cnt = 0;
        for (int i = 0; i < sz1; i++) {
            counts[nums1[i]]++;
        }

        int[] rs = new int[Math.min(sz1, sz2)];
        for (int i = 0; i < sz2; i++) {
            if (counts[nums2[i]] > 0) {
                rs[cnt++] = nums2[i];
                counts[nums2[i]]--;
            }
        }
        return Arrays.copyOfRange(rs, 0, cnt);
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().intersect(new int[]{1,2,2,1}, new int[]{2,2}), new int[]{2,2});
        assert Checker.check(new Solution().intersect(new int[]{4,9,5}, new int[]{9,4,9,8,4}), new int[]{4,9});
    }

}
