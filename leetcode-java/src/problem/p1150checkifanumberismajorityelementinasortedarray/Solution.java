package problem.p1150checkifanumberismajorityelementinasortedarray;

/**
 * 1150. Check If a Number Is Majority Element in a Sorted Array
 *
 * https://leetcode-cn.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array/
 *
 * Given an integer array nums sorted in non-decreasing order and an integer target,
 * return true if target is a majority element, or false otherwise.
 *
 * A majority element in an array nums is an element that appears more than nums.length / 2 times in the array.
 */

public class Solution {

    public boolean isMajorityElement(int[] nums, int target) {
        int l = binarySearchLeft(nums, target);
        return l >= 0 && l + nums.length / 2 < nums.length && nums[l + nums.length / 2] == target;
    }

    private int binarySearchLeft(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l < nums.length && nums[l] == target ? l : -1;
    }

    public static void main(String[] args) {
        assert new Solution().isMajorityElement(new int[]{438885258, 438885258}, 438885258);

        assert new Solution().isMajorityElement(new int[]{2,4,5,5,5,5,5,6,6}, 5);
        assert !new Solution().isMajorityElement(new int[]{10,100,101,101}, 101);
    }

}
