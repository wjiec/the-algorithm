package problem.p674longestcontinuousincreasingsubsequence;

/**
 * 674. Longest Continuous Increasing Subsequence
 *
 * https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/
 *
 * Given an unsorted array of integers nums, return the length of the
 * longest continuous increasing subsequence (i.e. subarray).
 * The subsequence must be strictly increasing.
 *
 * A continuous increasing subsequence is defined by two indices l and r (l < r) such that
 * it is [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] and for each l <= i < r, nums[i] < nums[i + 1].
 */

public class Solution {

    public int findLengthOfLCIS(int[] nums) {
        int ans = 1, n = nums.length, cnt = 1;
        if (n == 0) return 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                cnt++;
            } else {
                ans = Math.max(ans, cnt);
                cnt = 1;
            }
        }
        return Math.max(ans, cnt);
    }

    public static void main(String[] args) {
        assert new Solution().findLengthOfLCIS(new int[]{1,3,5,4,7}) == 3;
        assert new Solution().findLengthOfLCIS(new int[]{2,2,2,2,2}) == 1;
    }

}
