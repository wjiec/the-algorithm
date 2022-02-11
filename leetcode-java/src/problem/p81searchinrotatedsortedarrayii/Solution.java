package problem.p81searchinrotatedsortedarrayii;

/**
 * 81. Search in Rotated Sorted Array II
 *
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
 *
 * There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
 *
 * Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 *
 * For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
 *
 * Given the array nums after the rotation and an integer target,
 * return true if target is in nums, or false if it is not in nums.
 *
 * You must decrease the overall operation steps as much as possible.
 */

public class Solution {

    public boolean search(int[] nums, int target) {
        if (nums.length == 1) return nums[0] == target;
        int l = 0, r = nums.length, n = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) return true;
            if (nums[l] == nums[mid] && nums[mid] == nums[r - 1]) {
                ++l; --r;
                continue;
            }

            if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) r = mid;
                else l = mid + 1;
            } else {
                if (target <= nums[n - 1] && nums[mid] < target) l = mid + 1;
                else r = mid;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().search(new int[]{2,5,6,0,0,1,2}, 0);
        assert !new Solution().search(new int[]{2,5,6,0,0,1,2}, 3);

        assert new Solution().search(new int[]{3,1,2,3,3,3,3}, 2);
    }

}
