package weekly.bw147.C;

/**
 * 3409. Longest Subsequence With Decreasing Adjacent Difference
 *
 * https://leetcode.cn/contest/biweekly-contest-147/problems/longest-subsequence-with-decreasing-adjacent-difference/
 *
 * You are given an array of integers nums.
 *
 * Your task is to find the length of the longest subsequence seq of nums, such that the absolute
 * differences between consecutive elements form a non-increasing sequence of integers.
 *
 * In other words, for a subsequence seq0, seq1, seq2, ..., seqm of nums,
 * |seq1 - seq0| >= |seq2 - seq1| >= ... >= |seqm - seqm - 1|.
 *
 * Return the length of such a subsequence.
 */

public class Solution {

    public int longestSubsequence(int[] nums) {
        int mi = Integer.MAX_VALUE, mx = Integer.MIN_VALUE;
        for (var v : nums) { mi = Math.min(mi, v); mx = Math.max(mx, v); }

        // 如果已经找到子序列的 seq0 和 seq1, 对于 seq2 的要求可以计算得到
        //  - 已知: abs = |seq0 - seq1|
        //  - 可得: seq1 - abs <= seq2 <= seq1 + abs
        //
        // 枚举每个位置在不同 abs 下的所有可能答案

        return 1;
    }

    private int dfs(int[] nums, int i, int l, int r) {
        if (i == nums.length) return 0;

        int ans = dfs(nums, i + 1, l, r);
        for (int j = i; j < nums.length; j++) {
            int curr = nums[j];
            if (l <= curr && curr <= r) {

                ans = Math.max(ans, dfs(nums, j + 1, l, r));
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestSubsequence(new int[]{16,6,3}) == 3;
        assert new Solution().longestSubsequence(new int[]{6,5,3,4,2,1}) == 4;
        assert new Solution().longestSubsequence(new int[]{10,20,10,19,10,20}) == 5;
    }

}
