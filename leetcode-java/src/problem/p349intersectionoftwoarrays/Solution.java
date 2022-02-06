package problem.p349intersectionoftwoarrays;

import common.Checker;

/**
 * 349. Intersection of Two Arrays
 *
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/
 *
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must be unique and you may return the result in any order.
 */

public class Solution {

    public int[] intersection(int[] nums1, int[] nums2) {
        byte[] map = new byte[1001];
        for (int item : nums1) {
            map[item] = 1;
        }

        int cnt = 0;
        for (int value : nums2) {
            if (map[value] == 1) {
                cnt++;
                map[value] = 2;
            }
        }

        int[] val = new int[cnt];
        for (int i = 0, j = 0; i < map.length; i++) {
            if (map[i] == 2) {
                val[j++] = i;
            }
        }
        return val;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().intersection(new int[]{1,2,2,1}, new int[]{2,2}), new int[]{2});
        assert Checker.check(new Solution().intersection(new int[]{4,9,5}, new int[]{9,4,9,8,4}), new int[]{9,4});
    }

}
