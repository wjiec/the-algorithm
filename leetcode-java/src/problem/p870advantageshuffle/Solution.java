package problem.p870advantageshuffle;

import common.Checker;
import common.TODO;

import java.util.*;

/**
 * 870. Advantage Shuffle
 *
 * https://leetcode.cn/problems/advantage-shuffle/
 *
 * You are given two integer arrays nums1 and nums2 both of the same length.
 * The advantage of nums1 with respect to nums2 is the number of indices i for which nums1[i] > nums2[i].
 *
 * Return any permutation of nums1 that maximizes its advantage with respect to nums2.
 */

public class Solution {

    @TODO(tips = "田忌赛马")
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int[] a = nums1.clone(), b = nums2.clone();
        Arrays.sort(a); Arrays.sort(b);

        Map<Integer, Deque<Integer>> assigned = new HashMap<>();
        for (int v : nums2) assigned.put(v, new ArrayDeque<>());

        Deque<Integer> remains = new ArrayDeque<>();
        for (int i = 0, j = 0; i < a.length; i++) {
            if (a[i] > b[j]) assigned.get(b[j++]).add(a[i]);
            else remains.add(a[i]);
        }

        int[] ans = new int[nums2.length];
        for (int i = 0; i < nums2.length; i++) {
            if (assigned.get(nums2[i]).size() > 0) {
                ans[i] = assigned.get(nums2[i]).pop();
            } else ans[i] = remains.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().advantageCount(new int[]{2,7,11,15}, new int[]{1,10,4,11}), new int[]{2,11,7,15});
        assert Checker.check(new Solution().advantageCount(new int[]{12,24,8,32}, new int[]{13,25,32,11}), new int[]{24,32,8,12});
    }

}
