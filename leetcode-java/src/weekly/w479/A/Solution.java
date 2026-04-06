package weekly.w479.A;

import common.Checker;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Q1. Sort Integers by Binary Reflection
 *
 * https://leetcode.cn/contest/weekly-contest-479/problems/sort-integers-by-binary-reflection/
 *
 * You are given an integer array nums.
 *
 * The binary reflection of a positive integer is defined as the number
 * obtained by reversing the order of its binary digits (ignoring any leading zeros)
 * and interpreting the resulting binary number as a decimal.
 *
 * Sort the array in ascending order based on the binary reflection of each element.
 * If two different numbers have the same binary reflection, the smaller original number should appear first.
 *
 * Return the resulting sorted array.
 */

public class Solution {

    public int[] sortByReflection(int[] nums) {
        int[] reversed = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            reversed[i] = Integer.parseInt(new StringBuilder(Integer.toBinaryString(nums[i])).reverse().toString(), 2);
        }

        Integer[] sorted = IntStream.range(0, nums.length).boxed().toArray(Integer[]::new);
        Arrays.sort(sorted, (a, b) -> {
            if (reversed[a] == reversed[b]) {
                return nums[a] - nums[b];
            }
            return reversed[a] - reversed[b];
        });

        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[sorted[i]];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().sortByReflection(new int[]{8,2}), new int[]{2,8});
    }

}
