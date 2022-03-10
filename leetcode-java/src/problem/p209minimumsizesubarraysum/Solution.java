package problem.p209minimumsizesubarraysum;

/**
 * 209. Minimum Size Subarray Sum
 *
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 *
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr]
 * of which the sum is greater than or equal to target.
 *
 * If there is no such subarray, return 0 instead.
 */

public class Solution {

    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length, l = 0, ans = nums.length + 1, sum = 0;
        for (int r = 0; r < n; r++) {
            sum += nums[r];
            while (sum >= target) {
                ans = Math.min(ans, r - l + 1);
                sum -= nums[l++];
            }
        }
        return ans > nums.length ? 0 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().minSubArrayLen(7, new int[]{2,3,1,2,4,3}) == 2;
        assert new Solution().minSubArrayLen(4, new int[]{1,4,4}) == 1;
        assert new Solution().minSubArrayLen(11, new int[]{1,1,1,1,1,1,1,1}) == 0;
    }

}
