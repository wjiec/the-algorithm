package weekly.w494.C;

import java.util.Arrays;

/**
 * Q3. Minimum Removals to Achieve Target XOR
 *
 * https://leetcode.cn/contest/weekly-contest-494/problems/minimum-removals-to-achieve-target-xor/
 *
 * You are given an integer array nums and an integer target.
 *
 * You may remove any number of elements from nums (possibly zero).
 *
 * Return the minimum number of removals required so that the bitwise XOR of the
 * remaining elements equals target. If it is impossible to achieve target, return -1.
 *
 * The bitwise XOR of an empty array is 0.
 */

public class Solution {

    private static final int INF = 42;

    public int minRemovals(int[] nums, int target) {
        int n = nums.length, mx = 0;
        for (var v : nums) mx = Math.max(mx, v);

        int B = 32 - Integer.numberOfLeadingZeros(mx);
        if (B < 32 - Integer.numberOfLeadingZeros(target)) return -1;

        int[][] freq = new int[B][n + 1];
        for (int i = 0; i < B; i++) {
            for (int j = 0; j < nums.length; j++) {
                freq[i][j + 1] = freq[i][j] + ((nums[j] >> i) & 1);
            }
        }

        // 要求最后数组的异或和为 target, 也就是对于 target 的每一个二进制位
        //  - 如果为 1 的话, 所选择出来的元素在这个位置置位的数量要为奇数
        //  - 如果为 0 的话, 所选择出来的元素在这个位置置位的数量要为偶数
        int[][] memo = new int[n + 1][1 << B];
        for (var r : memo) Arrays.fill(r, -1);

        int ans = minRemovals(nums, nums.length - 1, freq, memo, target);
        return ans >= INF ? -1 : ans;
    }

    private int minRemovals(int[] nums, int i, int[][] freq, int[][] memo, int target) {
        if (i < 0) return target == 0 ? 0 : INF;
        if (memo[i][target] >= 0) return memo[i][target];
        for (int j = 0; j < freq.length; j++) {
            if (((target >> j) & 1) == 0) continue;
            if (freq[j][i + 1] == 0) return INF;
        }

        // 在 [0, i] 里, 我们最少删除多少个数可以组成 target?
        return memo[i][target] = Math.min(
            minRemovals(nums, i - 1, freq, memo, target ^ nums[i]), // 保留当前数
            minRemovals(nums, i - 1, freq, memo, target) + 1 // 移除当前数
        );
    }

    public static void main(String[] args) {
        assert new Solution().minRemovals(new int[]{0,0}, 3) == -1;

        assert new Solution().minRemovals(new int[]{1,2,3}, 2) == 1;
        assert new Solution().minRemovals(new int[]{2,4}, 1) == -1;
        assert new Solution().minRemovals(new int[]{7}, 7) == 0;
    }

}
