package daily.d210915p162findpeakelement;

/**
 * 162. Find Peak Element
 *
 * https://leetcode-cn.com/problems/find-peak-element/
 *
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given an integer array nums, find a peak element, and return its index.
 * If the array contains multiple peaks, return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * You must write an algorithm that runs in O(log n) time.
 */

public class Solution {

    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < nums[mid + 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        assert new Solution().findPeakElement(new int[]{1,2,3,1}) == 2;
        assert new Solution().findPeakElement(new int[]{1,2,1,3,5,6,4}) == 5;
    }

}
