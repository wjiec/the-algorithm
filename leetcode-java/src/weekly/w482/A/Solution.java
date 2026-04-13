package weekly.w482.A;

/**
 * Q1. Maximum Score of a Split
 *
 * https://leetcode.cn/contest/weekly-contest-482/problems/maximum-score-of-a-split/
 *
 * You are given an integer array nums of length n.
 *
 * Choose an index i such that 0 <= i < n - 1.
 *
 * For a chosen split index i:
 *
 * Let prefixSum(i) be the sum of nums[0] + nums[1] + ... + nums[i].
 * Let suffixMin(i) be the minimum value among nums[i + 1], nums[i + 2], ..., nums[n - 1].
 * The score of a split at index i is defined as:
 *
 * score(i) = prefixSum(i) - suffixMin(i)
 *
 * Return an integer denoting the maximum score over all valid split indices.
 */

public class Solution {

    public long maximumScore(int[] nums) {
        int[] mins = new int[nums.length];
        mins[mins.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            mins[i] = Math.min(mins[i + 1], nums[i]);
        }

        long ans = Long.MIN_VALUE, sum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            sum += nums[i];
            ans = Math.max(ans, sum - mins[i + 1]);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
