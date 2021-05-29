package problem.p643maximumaveragesubarrayi;

/**
 * 643. Maximum Average Subarray I
 *
 * https://leetcode-cn.com/problems/maximum-average-subarray-i/
 *
 * You are given an integer array nums consisting of n elements, and an integer k.
 *
 * Find a contiguous subarray whose length is equal to k that has the
 * maximum average value and return this value.
 *
 * Any answer with a calculation error less than 10-5 will be accepted.
 */

public class Solution {

    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length, ans = 0;
        for (int i = 0; i < k; i++) {
            ans += nums[i];
        }

        for (int i = k, sum = ans; i < n; i++) {
            sum += nums[i] - nums[i - k];
            ans = Math.max(ans, sum);
        }

        return (double) ans / k;
    }

    public static void main(String[] args) {
        assert new Solution().findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4) == 12.75;
        assert new Solution().findMaxAverage(new int[]{5}, 1) == 5;
    }

}
