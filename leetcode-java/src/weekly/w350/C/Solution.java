package weekly.w350.C;

import java.util.Arrays;

/**
 * 6893. Special Permutations
 *
 * https://leetcode.cn/contest/weekly-contest-350/problems/special-permutations/
 *
 * You are given a 0-indexed integer array nums containing n distinct positive integers.
 * A permutation of nums is called special if:
 *
 * For all indexes 0 <= i < n - 1, either nums[i] % nums[i+1] == 0 or nums[i+1] % nums[i] == 0.
 *
 * Return the total number of special permutations. As the answer could be large, return it modulo 109 + 7.
 */

@SuppressWarnings("unchecked")
public class Solution {

    private final static int MOD = 1_000_000_007;

    public int specialPerm(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans = (ans + dfs(nums, 1 << i, i)) % MOD;
        }
        return ans;
    }

    private final int[][] memo = new int[14][1 << 14];
    { for (var row : memo) Arrays.fill(row, -1); }

    private int dfs(int[] nums, int mask, int curr) {
        if (mask + 1 == 1 << nums.length) return 1;
        if (memo[curr][mask] == -1) {
            int ans = 0;
            for (int i = 0; i < nums.length; i++) {
                if ((mask & (1 << i)) == 0 && (nums[i] % nums[curr] == 0 || nums[curr] % nums[i] == 0)) {
                    ans = (ans + dfs(nums, mask | (1 << i), i)) % MOD;
                }
            }
            memo[curr][mask] = ans;
        }
        return memo[curr][mask];
    }

    public static void main(String[] args) {
        assert new Solution().specialPerm(new int[]{2, 3, 6}) == 2;
        assert new Solution().specialPerm(new int[]{1, 4, 3}) == 2;
        assert new Solution().specialPerm(new int[]{31, 93}) == 2;
    }

}
