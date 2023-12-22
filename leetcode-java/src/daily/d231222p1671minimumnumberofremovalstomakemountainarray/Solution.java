package daily.d231222p1671minimumnumberofremovalstomakemountainarray;

import java.util.Arrays;

/**
 * 1671. Minimum Number of Removals to Make Mountain Array
 *
 * https://leetcode.cn/problems/minimum-number-of-removals-to-make-mountain-array
 *
 * You may recall that an array arr is a mountain array if and only if:
 *
 * arr.length >= 3
 * There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 *
 * Given an integer array nums, return the minimum number of elements to remove to make nums a mountain array.
 */

public class Solution {

    public int minimumMountainRemovals(int[] nums) {
        int[] prefix = lis(nums);
        int[] suffix = reverse(lis(reverse(nums)));

        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (prefix[i] > 1 && suffix[i] > 1) {
                ans = Math.max(ans, prefix[i] + suffix[i] - 1);
            }
        }
        return nums.length - ans;
    }

    private int[] lis(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

    private int[] reverse(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[nums.length - i - 1] = nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumMountainRemovals(new int[]{23,47,63,72,81,99,88,55,21,33,32}) == 1;
    }

}
