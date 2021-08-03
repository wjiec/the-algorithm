package daily.d210803p581shortestunsortedcontinuoussubarray;

import java.util.Arrays;

/**
 * 581. Shortest Unsorted Continuous Subarray
 *
 * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
 *
 * Given an integer array nums, you need to find one continuous subarray that
 * if you only sort this subarray in ascending order,
 * then the whole array will be sorted in ascending order.
 *
 * Return the shortest such subarray and output its length.
 */

public class Solution {

    public int findUnsortedSubarray(int[] nums) {
        if (sorted(nums)) return 0;

        int[] sorted = new int[nums.length];
        System.arraycopy(nums, 0, sorted, 0, nums.length);
        Arrays.sort(sorted);

        int left = 0, right = nums.length - 1;
        while (sorted[left] == nums[left]) left++;
        while (sorted[right] == nums[right]) right--;

        return right - left + 1;
    }

    private boolean sorted(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().findUnsortedSubarray(new int[]{2,6,4,8,10,9,15}) == 5;
        assert new Solution().findUnsortedSubarray(new int[]{1,2,3,4}) == 0;
        assert new Solution().findUnsortedSubarray(new int[]{1}) == 0;
    }

}
