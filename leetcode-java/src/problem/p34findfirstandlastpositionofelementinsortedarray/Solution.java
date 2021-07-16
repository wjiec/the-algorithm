package problem.p34findfirstandlastpositionofelementinsortedarray;

import common.Checker;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 *
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * Given an array of integers nums sorted in ascending order,
 * find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 */

public class Solution {

    public int[] searchRange(int[] nums, int target) {
        int l = 0, r = nums.length, idx = -1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid;
            } else {
                idx = mid;
                break;
            }
        }

        if (idx != -1) {
            for (l = idx; l >= 0 && nums[l] == target; l--);
            for (r = idx; r < nums.length && nums[r] == target; r++);
            return new int[]{l + 1, r - 1};
        }
        return new int[]{-1,-1};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().searchRange(new int[]{5,7,7,8,8,10}, 8), new int[]{3,4});
        assert Checker.check(new Solution().searchRange(new int[]{5,7,7,8,8,10}, 6), new int[]{-1,-1});
        assert Checker.check(new Solution().searchRange(new int[]{}, 0), new int[]{-1,-1});
    }

}
