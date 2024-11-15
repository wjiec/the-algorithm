package weekly.w423.C;

/**
 * 3351. Sum of Good Subsequences
 *
 * https://leetcode.cn/contest/weekly-contest-423/problems/sum-of-good-subsequences/
 *
 * You are given an integer array nums. A good subsequence is defined as a subsequence of nums
 * where the absolute difference between any two consecutive elements in the subsequence is exactly 1.
 *
 * Return the sum of all possible good subsequences of nums.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 *
 * Note that a subsequence of size 1 is considered good by definition.
 */

public class Solution {

    public int sumOfGoodSubsequences(int[] nums) {
        final int MOD = 1_000_000_007;
        // 考虑使用当前索引的值 v 时, 可以选的方案是前面所有 = v / v - 1 / v + 1 进行转移(相加)
        //  - 现在问题就变成如何表示前面所有的最后一个值的状态
        //  - 考虑使用有多少个, 并且总和为多少的方式
        long[] cnt = new long[100_100];
        long[] sum = new long[100_100];
        for (var v : nums) {
            v += 1;

            long curr = v - 1;
            // 统计前值为 v - 1 的情况
            curr = (curr + cnt[v - 1] * (v - 1) + sum[v - 1]) % MOD;
            // 统计前值为 v + 1 的情况
            curr = (curr + cnt[v + 1] * (v - 1) + sum[v + 1]) % MOD;
            // 叠加 cnt[v] 的出现次数
            cnt[v] = (cnt[v] + cnt[v - 1] + cnt[v + 1] + 1) % MOD;
            // 叠加 sum[v] 的值
            sum[v] = (sum[v] + curr) % MOD;
        }

        long ans = 0;
        for (var v : sum) ans = (ans + v) % MOD;
        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().sumOfGoodSubsequences(new int[]{1,2,1}) == 14;
        assert new Solution().sumOfGoodSubsequences(new int[]{3,4,5}) == 40;
    }

}
