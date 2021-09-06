package daily.d210906p704binarysearch;

/**
 * 704. Binary Search
 *
 * https://leetcode-cn.com/problems/binary-search/
 *
 * Given an array of integers nums which is sorted in ascending order,
 * and an integer target, write a function to search target in nums.
 *
 * If target exists, then return its index. Otherwise, return -1.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 */

public class Solution {

    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) {
                l = mid + 1;
            } else r = mid - 1;
        }
        return nums[l] == target ? l : -1;
    }

    public static void main(String[] args) {
        assert new Solution().search(new int[]{-1,0,3,5,9,12}, 9) == 4;
        assert new Solution().search(new int[]{-1,0,3,5,9,12}, 2) == -1;
    }

}
