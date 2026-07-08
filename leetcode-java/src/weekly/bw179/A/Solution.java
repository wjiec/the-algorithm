package weekly.bw179.A;

/**
 * Q1. Minimum Absolute Difference Between Two Values
 *
 * https://leetcode.cn/contest/biweekly-contest-179/problems/minimum-absolute-difference-between-two-values/
 *
 * You are given an integer array nums consisting only of 0, 1, and 2.
 *
 * A pair of indices (i, j) is called valid if nums[i] == 1 and nums[j] == 2.
 *
 * Return the minimum absolute difference between i and j among all valid pairs. If no valid pair exists, return -1.
 *
 * The absolute difference between indices i and j is defined as abs(i - j).
 */

public class Solution {

    public int minAbsoluteDifference(int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0, p1 = -1, p2 = -1; i < nums.length; i++) {
            switch (nums[i]) {
                case 1 -> {
                    if (p2 != -1) ans = Math.min(ans, Math.abs(p2 - i));
                    p1 = i;
                }
                case 2 -> {
                    if (p1 != -1) ans = Math.min(ans, Math.abs(p1 - i));
                    p2 = i;
                }
            }
        }
        return ans >= nums.length ? -1 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().minAbsoluteDifference(new int[]{1,0,0,2,0,1}) == 2;
        assert new Solution().minAbsoluteDifference(new int[]{1,2}) == 1;
    }

}
