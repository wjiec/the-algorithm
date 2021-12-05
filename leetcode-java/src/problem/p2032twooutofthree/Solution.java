package problem.p2032twooutofthree;

import java.util.ArrayList;
import java.util.List;

/**
 * 2032. Two Out of Three
 *
 * https://leetcode-cn.com/problems/two-out-of-three/
 *
 * Given three integer arrays nums1, nums2, and nums3, return a distinct array containing
 * all the values that are present in at least two out of the three arrays.
 *
 * You may return the values in any order.
 */

public class Solution {

    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        byte[] map = new byte[101];
        for (var n : nums1) map[n] |= 1; // 1 << 1
        for (var n : nums2) map[n] |= 2; // 1 << 2
        for (var n : nums3) map[n] |= 4; // 1 << 4

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0 && map[i] != 1 && map[i] != 2 && map[i] != 4) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().twoOutOfThree(new int[]{1,1,3,2}, new int[]{2,3}, new int[]{3}));
        System.out.println(new Solution().twoOutOfThree(new int[]{3,1}, new int[]{2,3}, new int[]{1,2}));
        System.out.println(new Solution().twoOutOfThree(new int[]{1,2,2}, new int[]{4,3,3}, new int[]{5}));
    }

}
