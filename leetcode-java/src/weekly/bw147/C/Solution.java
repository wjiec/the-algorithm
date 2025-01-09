package weekly.bw147.C;

import java.util.Arrays;

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

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int longestSubsequence(int[] nums) {
        int mi = Integer.MAX_VALUE, mx = Integer.MIN_VALUE;
        for (var v : nums) { mi = Math.min(mi, v); mx = Math.max(mx, v); }

        // 计算得到数组中的最大绝对差
        int mxa = mx - mi;

        // 定义状态 dp[i][j] 表示以 curr = nums[i] 为结尾, 同时与前一个位置 p 的绝对差是 j 的方案数
        //  - 这意味着位置 p 的值是 curr + p 或者 curr - p
        //
        // 根据题意, 我们可以从 dp[p][j], dp[p][j + 1], ... dp[p][mxa] 的位置转移过来
        int[][] dp = new int[nums.length][mxa + 1];
        // 我们需要记录下每个数值对应的最后位置
        int[] lastIndex = new int[mx + 1]; Arrays.fill(lastIndex, -1);
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            // 枚举每一个可能的绝对差
            for (int j = 0; j <= mxa; j++) {
                dp[i][j] = 1; // 自己作为一个单独的子序列
                if (curr - j >= 0 && lastIndex[curr - j] >= 0) dp[i][j] = Math.max(dp[i][j], dp[lastIndex[curr - j]][j] + 1);
                if (curr + j <= mx && lastIndex[curr + j] >= 0) dp[i][j] = Math.max(dp[i][j], dp[lastIndex[curr + j]][j] + 1);
            }

            // 由于我们是从 dp[i][j] 枚举到 dp[i][mxa] 找到最大值, 所以这里直接对已经计算完成的状态进行预处理得到后缀最大值
            for (int j = mxa - 1; j >= 0; j--) dp[i][j] = Math.max(dp[i][j], dp[i][j + 1]);
            // 保存最后出现的元素的位置
            lastIndex[curr] = i;
        }

        // 在所有的得到的答案里的最大值即是答案
        int ans = 0;
        for (var row : dp) for (var v : row) ans = Math.max(ans, v);
        return ans;
    }

    private static class Optimization1 {
        public int longestSubsequence(int[] nums) {
            int mi = Integer.MAX_VALUE, mx = Integer.MIN_VALUE;
            for (var v : nums) { mi = Math.min(mi, v); mx = Math.max(mx, v); }

            // 计算得到数组中的最大绝对差
            int mxa = mx - mi;

            // 我们直接定义状态 dp[i][j] 表示以 curr = nums[i] 为结尾, 同时与前一个位置 p 的绝对差 >= j 的方案数
            //  - 把 curr 作为单独的子序列, 长度为 1
            //  - 可以从前一个数的 dp[p][j] 转移过来
            //  - 由于我们计算的是 >= j 的方案数, 所以也可以直接从 dp[i][j + 1] 转移过来(相当于直接把后缀最大值的计算直接内嵌到循环中)
            // 以上几种转移方式取最大值
            int[][] dp = new int[nums.length][mxa + 2];
            // 我们需要记录下每个数值对应的最后位置
            int[] lastIndex = new int[mx + 1]; Arrays.fill(lastIndex, -1);
            for (int i = 0; i < nums.length; i++) {
                int curr = nums[i];
                for (int j = mxa; j >= 0; j--) {
                    dp[i][j] = Math.max(1, dp[i][j + 1]);
                    if (curr - j >= 0 && lastIndex[curr - j] >= 0) dp[i][j] = Math.max(dp[i][j], dp[lastIndex[curr - j]][j] + 1);
                    if (curr + j <= mx && lastIndex[curr + j] >= 0) dp[i][j] = Math.max(dp[i][j], dp[lastIndex[curr + j]][j] + 1);
                }

                lastIndex[curr] = i;
            }

            // 在所有的得到的答案里的最大值即是答案
            int ans = 0;
            for (var row : dp) for (var v : row) ans = Math.max(ans, v);
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Optimization1().longestSubsequence(new int[]{16,6,3}) == 3;
        assert new Optimization1().longestSubsequence(new int[]{6,5,3,4,2,1}) == 4;
        assert new Optimization1().longestSubsequence(new int[]{10,20,10,19,10,20}) == 5;

        assert new Solution().longestSubsequence(new int[]{16,6,3}) == 3;
        assert new Solution().longestSubsequence(new int[]{6,5,3,4,2,1}) == 4;
        assert new Solution().longestSubsequence(new int[]{10,20,10,19,10,20}) == 5;
    }

}
