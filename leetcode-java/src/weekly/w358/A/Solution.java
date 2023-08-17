package weekly.w358.A;

/**
 * 2815. Max Pair Sum in an Array
 *
 * https://leetcode.cn/contest/weekly-contest-358/problems/max-pair-sum-in-an-array/
 *
 * You are given a 0-indexed integer array nums. You have to find the maximum sum of a
 * pair of numbers from nums such that the maximum digit in both numbers are equal.
 *
 * Return the maximum sum or -1 if no such pair exists.
 */

public class Solution {

    public int maxSum(int[] nums) {
        int ans = -1, n = nums.length;
        for (int i = 0; i < n; i++) {
            int ix = 0, iv = nums[i];
            for (; iv != 0; iv /= 10) ix = Math.max(ix, iv % 10);

            for (int j = i + 1; j < n; j++) {
                int jx = 0, jv = nums[j];
                for (; jv != 0; jv /= 10) jx = Math.max(jx, jv % 10);
                if (ix == jx) ans = Math.max(ans, nums[i] + nums[j]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
