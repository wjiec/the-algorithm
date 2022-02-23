package problem.p153findminimuminrotatedsortedarray;

/**
 * 153. Find Minimum in Rotated Sorted Array
 *
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 *
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 *
 * For example, the array nums = [0,1,2,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 *
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in
 * the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 *
 * You must write an algorithm that runs in O(log n) time.
 */

public class Solution {

    public int findMin(int[] nums) {
        int l = 0, r = nums.length, s = nums[0], e = nums[r - 1];
        if (r == 1 || s <= e) return s;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < s) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }

    public static void main(String[] args) {
        assert new Solution().findMin(new int[]{3,4,5,1,2}) == 1;
        assert new Solution().findMin(new int[]{4,5,6,7,0,1,2}) == 0;
        assert new Solution().findMin(new int[]{11,13,15,17}) == 11;
        assert new Solution().findMin(new int[]{1,2,3,4,5}) == 1;
        assert new Solution().findMin(new int[]{5,1,2,3,4}) == 1;
        assert new Solution().findMin(new int[]{4,5,1,2,3}) == 1;
        assert new Solution().findMin(new int[]{3,4,5,1,2}) == 1;
        assert new Solution().findMin(new int[]{2,3,4,5,1}) == 1;
    }

}
