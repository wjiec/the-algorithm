package weekly.w349.A;

import java.util.TreeSet;

/**
 * 2733. Neither Minimum nor Maximum
 *
 * https://leetcode.cn/contest/weekly-contest-349/problems/neither-minimum-nor-maximum/
 *
 * Given an integer array nums containing distinct positive integers, find and return any number
 * from the array that is neither the minimum nor the maximum value
 * in the array, or -1 if there is no such number.
 *
 * Return the selected integer.
 */

public class Solution {

    public int findNonMinOrMax(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (var v : nums) set.add(v);
        if (set.size() >= 3) return set.higher(set.first());
        return -1;
    }

    public static void main(String[] args) {
    }

}
