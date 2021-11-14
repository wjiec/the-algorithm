package problem.p1800maximumascendingsubarraysum;

/**
 * 1800. Maximum Ascending Subarray Sum
 *
 * https://leetcode-cn.com/problems/maximum-ascending-subarray-sum/
 *
 * Given an array of positive integers nums, return the maximum possible sum of an ascending subarray in nums.
 *
 * A subarray is defined as a contiguous sequence of numbers in an array.
 *
 * A subarray [numsl, numsl+1, ..., numsr-1, numsr] is ascending if for all i where l <= i < r, numsi < numsi+1.
 *
 * Note that a subarray of size 1 is ascending.
 */

public class Solution {

    public int maxAscendingSum(int[] nums) {
        int max = 0, curr = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                curr += nums[i];
            } else {
                max = Math.max(max, curr);
                curr = nums[i];
            }
        }
        return Math.max(max, curr);
    }

    public static void main(String[] args) {
        assert new Solution().maxAscendingSum(new int[]{3,6,10,1,8,9,9,8,9}) == 19;

        assert new Solution().maxAscendingSum(new int[]{10,20,30,5,10,50}) == 65;
        assert new Solution().maxAscendingSum(new int[]{10,20,30,40,50}) == 150;
        assert new Solution().maxAscendingSum(new int[]{12,17,15,13,10,11,12}) == 33;
        assert new Solution().maxAscendingSum(new int[]{100,10,1}) == 100;
    }

}
