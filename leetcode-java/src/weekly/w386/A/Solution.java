package weekly.w386.A;

import java.util.HashMap;
import java.util.Map;

/**
 * 3046. Split the Array
 *
 * https://leetcode.cn/contest/weekly-contest-386/problems/split-the-array/
 *
 * You are given an integer array nums of even length. You have to split the array
 * into two parts nums1 and nums2 such that:
 *
 * nums1.length == nums2.length == nums.length / 2.
 * nums1 should contain distinct elements.
 * nums2 should also contain distinct elements.
 *
 * Return true if it is possible to split the array, and false otherwise.
 */

public class Solution {

    public boolean isPossibleToSplit(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var v : nums) if (map.merge(v, 1, Integer::sum) > 2) return false;
        return true;
    }

    public static void main(String[] args) {
    }

}
