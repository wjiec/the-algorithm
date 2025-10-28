package weekly.w466.A;

/**
 * Q1. Minimum Operations to Equalize Array
 *
 * https://leetcode.cn/contest/weekly-contest-466/problems/minimum-operations-to-equalize-array/
 *
 * You are given an integer array nums of length n.
 *
 * In one operation, choose any subarray nums[l...r] (0 <= l <= r < n) and replace each element
 * in that subarray with the bitwise AND of all elements.
 *
 * Return the minimum number of operations required to make all elements of nums equal.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public int minOperations(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) return 1;
        }
        return 0;
    }

    private static class Optimization {
        public int minOperations(int[] nums) {
            for (int i = 1, v = nums[0]; i < nums.length; i++) {
                if (nums[i] != v) return 1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
    }

}
