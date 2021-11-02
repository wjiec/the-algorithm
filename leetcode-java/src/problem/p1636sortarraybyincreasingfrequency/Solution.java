package problem.p1636sortarraybyincreasingfrequency;

import common.Checker;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 1636. Sort Array by Increasing Frequency
 *
 * https://leetcode-cn.com/problems/sort-array-by-increasing-frequency/
 *
 * Given an array of integers nums, sort the array in increasing order based on the frequency of the values.
 *
 * If multiple values have the same frequency, sort them in decreasing order.
 *
 * Return the sorted array.
 */

public class Solution {

    public int[] frequencySort(int[] nums) {
        int[] cnt = new int[201];
        for (var n : nums) cnt[n + 100]++;
        for (int i = 0; i < nums.length; i++) nums[i] = cnt[nums[i] + 100] * 1000 + (100 - nums[i]);
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) nums[i] = 100 - (nums[i] % 1000);
        return nums;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().frequencySort(new int[]{1,1,2,2,2,3}), new int[]{3,1,1,2,2,2});
        assert Checker.check(new Solution().frequencySort(new int[]{2,3,1,3,2}), new int[]{1,3,3,2,2});
        assert Checker.check(new Solution().frequencySort(new int[]{-1,1,-6,4,5,-6,1,4,1}), new int[]{5,-1,4,4,-6,-6,1,1,1});
    }

}
