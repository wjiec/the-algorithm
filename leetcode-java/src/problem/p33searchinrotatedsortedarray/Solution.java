package problem.p33searchinrotatedsortedarray;

/**
 * 33. Search in Rotated Sorted Array
 *
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 *
 * There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 *
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the possible rotation and an integer target, return the
 * index of target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 */

public class Solution {

    public int search(int[] nums, int target) {
        if (nums.length == 1) return nums[0] == target ? 0 : -1;
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) return mid;
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[nums.length - 1]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().search(new int[]{3,4,5,6,1,2}, 2) == 5;
        assert new Solution().search(new int[]{3,5,1}, 1) == 2;
        assert new Solution().search(new int[]{3,1}, 3) == 0;

        assert new Solution().search(new int[]{4,5,6,7,0,1,2}, 0) == 4;
        assert new Solution().search(new int[]{4,5,6,7,0,1,2}, 3) == -1;
        assert new Solution().search(new int[]{1}, 0) == -1;

        assert new Solution().search(new int[]{0,1,2,3,4,5,6,7}, 0) == 0;
        assert new Solution().search(new int[]{0,1,2,3,4,5,6,7}, 7) == 7;
        assert new Solution().search(new int[]{0,1,2,3,4,5,6,7}, 4) == 4;
        assert new Solution().search(new int[]{0,1,2,3,4,5,6,7}, 5) == 5;

        assert new Solution().search(new int[]{7,0,1,2,3,4,5,6}, 5) == 6;
    }

}
