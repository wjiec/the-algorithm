package weekly.bw178.A;

/**
 * Q1. First Unique Even Element
 *
 * https://leetcode.cn/contest/biweekly-contest-178/problems/first-unique-even-element/
 *
 * You are given an integer array nums.
 *
 * Return an integer denoting the first even integer (earliest by array index) that appears
 * exactly once in nums. If no such integer exists, return -1.
 *
 * An integer x is considered even if it is divisible by 2.
 */

public class Solution {

    public int firstUniqueEven(int[] nums) {
        int[] freq = new int[101];
        for (var v: nums) freq[v]++;

        for (int v : nums) {
            if ((v & 1) == 0 && freq[v] == 1) return v;
        }
        return -1;
    }

    public static void main(String[] args) {
    }

}
