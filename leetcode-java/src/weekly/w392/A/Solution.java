package weekly.w392.A;

/**
 * 3105. Longest Strictly Increasing or Strictly Decreasing Subarray
 *
 * https://leetcode.cn/contest/weekly-contest-392/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/
 *
 * You are given an array of integers nums. Return the length of the longest subarray of nums
 * which is either strictly increasing or strictly decreasing.
 */

public class Solution {

    public int longestMonotonicSubarray(int[] nums) {
        int ans = 1, curr = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) curr++;
            else curr = 1;
            ans = Math.max(ans, curr);
        }

        curr = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) curr++;
            else curr = 1;
            ans = Math.max(ans, curr);
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
