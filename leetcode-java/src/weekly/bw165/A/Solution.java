package weekly.bw165.A;

import java.util.HashSet;
import java.util.Set;

/**
 * Q1. Smallest Absent Positive Greater Than Average
 *
 * https://leetcode.cn/contest/biweekly-contest-165/problems/smallest-absent-positive-greater-than-average/
 *
 * You are given an integer array nums.
 *
 * Return the smallest absent positive integer in nums such that it is strictly
 * greater than the average of all elements in nums.
 *
 * The average of an array is defined as the sum of all its elements divided by the number of elements.
 */

public class Solution {

    public int smallestAbsent(int[] nums) {
        int sum = 0;
        Set<Integer> s = new HashSet<>();
        for (var v : nums) { sum += v; s.add(v); }
        int avg = Math.max(sum / nums.length + 1, 1);

        while (s.contains(avg)) avg++;
        return avg;
    }

    public static void main(String[] args) {
    }

}
