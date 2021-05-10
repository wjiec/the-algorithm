package problem.p167twosumiiinputarrayissorted;

import common.Checker;

/**
 * 167. Two Sum II - Input array is sorted
 *
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
 *
 * Given an array of integers numbers that is already sorted in ascending order,
 * find two numbers such that they add up to a specific target number.
 *
 * Return the indices of the two numbers (1-indexed) as an integer array answer of size 2,
 * where 1 <= answer[0] < answer[1] <= numbers.length.
 *
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 */

public class Solution {

    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        for (; l < r; l++) {
            int v = target - numbers[l];
            for (; numbers[r] > v; r--);
            if (numbers[r] == v) {
                break;
            }
        }
        return new int[]{l + 1, r + 1};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().twoSum(new int[]{2,7,11,15}, 9), new int[]{1,2});
        assert Checker.check(new Solution().twoSum(new int[]{2,3,4}, 6), new int[]{1,3});
        assert Checker.check(new Solution().twoSum(new int[]{-1,0}, -1), new int[]{1,2});
        assert Checker.check(new Solution().twoSum(new int[]{-1,0,1}, 0), new int[]{1,3});
    }

}
