package problem.p1968arraywithelementsnotequaltoaverageofneighbors;

import common.PrettyPrinter;

import java.util.Arrays;

/**
 * 1968. Array With Elements Not Equal to Average of Neighbors
 *
 * https://leetcode.cn/problems/array-with-elements-not-equal-to-average-of-neighbors/
 *
 * You are given a 0-indexed array nums of distinct integers. You want to rearrange the elements
 * in the array such that every element in the rearranged array is not equal to
 * the average of its neighbors.
 *
 * More formally, the rearranged array should have the property such that for every i
 * in the range 1 <= i < nums.length - 1, (nums[i-1] + nums[i+1]) / 2 is not equal to nums[i].
 *
 * Return any rearrangement of nums that meets the requirements.
 */

public class Solution {

    public int[] rearrangeArray(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length - 1;
        for (int i = 1; i < n; i += 2) {
            swap(nums, i, i + 1);
        }
        return nums;
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void main(String[] args) {
        assert check(new Solution().rearrangeArray(new int[]{1,5,2,6,3,7,4,8}));
        assert check(new Solution().rearrangeArray(new int[]{1,3,5,7}));
        assert check(new Solution().rearrangeArray(new int[]{10,7,5,4,3}));
        assert check(new Solution().rearrangeArray(new int[]{1,2,3}));
        assert check(new Solution().rearrangeArray(new int[]{1,2,3,4,5}));
        assert check(new Solution().rearrangeArray(new int[]{6,2,0,9,7}));
    }

    private static boolean check(int[] array) {
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i] * 2 == array[i - 1] + array[i + 1]) {
                PrettyPrinter.println(array);
                return false;
            }
        }
        return true;
    }

}
