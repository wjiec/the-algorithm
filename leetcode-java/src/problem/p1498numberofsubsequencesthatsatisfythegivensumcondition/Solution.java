package problem.p1498numberofsubsequencesthatsatisfythegivensumcondition;

import java.util.Arrays;

/**
 * 1498. Number of Subsequences That Satisfy the Given Sum Condition
 *
 * https://leetcode.cn/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/
 *
 * You are given an array of integers nums and an integer target.
 *
 * Return the number of non-empty subsequences of nums such that the sum of the
 * minimum and maximum element on it is less or equal to target.
 *
 * Since the answer may be too large, return it modulo 109 + 7.
 */

public class Solution {

    public int numSubseq(int[] nums, int target) {
        int MOD = 1_000_000_007, n = nums.length;
        Arrays.sort(nums);

        int[] rel = new int[n]; rel[0] = 1;
        for (int i = 1; i < n; i++) {
            rel[i] = (rel[i - 1] << 1) % MOD;
        }

        int ans = 0;
        for (int l = 0, r = n - 1; l <= r; ) {
            if (nums[l] + nums[r] <= target) {
                ans = (ans + rel[r - l]) % MOD;
                l++;
            } else r--;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numSubseq(new int[]{3,5,6,7}, 9) == 4;
        assert new Solution().numSubseq(new int[]{3,3,6,8}, 10) == 6;
        assert new Solution().numSubseq(new int[]{2,3,3,4,6,7}, 12) == 61;
    }

}
