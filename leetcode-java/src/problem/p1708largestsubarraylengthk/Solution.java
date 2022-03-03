package problem.p1708largestsubarraylengthk;

import java.util.Arrays;

/**
 * 1708. Largest Subarray Length K
 *
 * https://leetcode-cn.com/problems/largest-subarray-length-k/
 *
 * An array A is larger than some array B if for the first index i where A[i] != B[i], A[i] > B[i].
 *
 * For example, consider 0-indexing:
 *
 * [1,3,2,4] > [1,2,2,4], since at index 1, 3 > 2.
 * [1,4,4,4] < [2,1,1,1], since at index 0, 1 < 2.
 * A subarray is a contiguous subsequence of the array.
 *
 * Given an integer array nums of distinct integers, return the largest subarray of nums of length k.
 */

public class Solution {

    public int[] largestSubarray(int[] nums, int k) {
        int index = 0, max = 0;
        for (int i = 0, n = nums.length - k; i <= n; i++) {
            if (nums[i] > max) {
                index = i;
                max = nums[i];
            }
        }

        int[] ans = new int[k];
        System.arraycopy(nums, index,ans, 0, k);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().largestSubarray(new int[]{1, 4, 5, 2, 3}, 3)));
        System.out.println(Arrays.toString(new Solution().largestSubarray(new int[]{1, 4, 5, 2, 3}, 4)));
        System.out.println(Arrays.toString(new Solution().largestSubarray(new int[]{1, 4, 5, 2, 3}, 1)));
    }

}
