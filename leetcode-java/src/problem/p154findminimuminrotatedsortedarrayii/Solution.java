package problem.p154findminimuminrotatedsortedarrayii;

/**
 * 154. Find Minimum in Rotated Sorted Array II
 *
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 *
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 *
 * For example, the array nums = [0,1,4,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,4] if it was rotated 4 times.
 * [0,1,4,4,5,6,7] if it was rotated 7 times.
 *
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results
 * in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.
 *
 * You must decrease the overall operation steps as much as possible.
 */

public class Solution {

    public int findMin(int[] nums) {
        for (int i = 1, l = nums.length; i < l; i++) {
            if (nums[i] < nums[i - 1]) return nums[i];
        }
        return nums[0];
    }

    public static void main(String[] args) {
        assert new Solution().findMin(new int[]{1,3,5}) == 1;
        assert new Solution().findMin(new int[]{2,2,2,0,1}) == 0;
    }

}
