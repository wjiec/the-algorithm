package weekly.w477.D;

import common.Tag;

/**
 * Q4. Number of Effective Subsequences
 *
 * https://leetcode.cn/contest/weekly-contest-477/problems/number-of-effective-subsequences/
 *
 * You are given an integer array nums.
 *
 * The strength of the array is defined as the bitwise OR of all its elements.
 *
 * A subsequence is considered effective if removing that subsequence strictly
 * decreases the strength of the remaining elements.
 *
 * Return the number of effective subsequences in nums. Since the answer may be large, return it modulo 1e9 + 7.
 *
 * The bitwise OR of an empty array is 0.
 */

public class Solution {

    private static final int MOD = 1_000_000_007, MAX_N = 1_000_001;
    private static final int[] pow2 = new int[MAX_N];
    static { pow2[0] = 1; }
    static { for (int i = 1; i < MAX_N; i++) pow2[i] = (pow2[i - 1] << 1) % MOD; }

    @Tag({"高维前缀和", "SOS DP"})
    public int countEffective(int[] nums) {
        // 所有数的按位或可以得到一个最大的数 x, 我们需要找到所有子序列的按位或 < x 的数
        //  - 可以转换成所有子序列的数量 - 按位或等于 x 的子序列数量
        //      - 所有子序列的数量 = 2 ^ n 个
        //
        // 问题变成从数组中找到子序列使得按位或等于 x 的方案有多少
        //  - "恰好"类型的问题可以转换成"至多"问题
        //
        // 将问题转换为从数组中找到子序列使得按位或为 x 的子集
        //  - 也就是找到数组中的数是 x 的子集的元素, 那么这些数的任意子序列按位或都是 <= x 的
        int x = 0; for (var v : nums) x |= v;

        // 计算所需的位数以及 dp 数组
        //  - 计算完成后 dp[s] 就表示在 nums 中有多少个元素是 s 的子集
        int w = 32 - Integer.numberOfLeadingZeros(x), u = 1 << w;
        int[] dp = new int[u]; for (var v : nums) dp[v]++;
        for (int i = 0; i < w; i++) { // 执行状态转移
            if ((x & (1 << i)) == 0) continue;

            int b = 1 << i;
            for (int s = b; s < u; s = (s + 1) | b) {
                dp[s] += dp[s ^ b];
            }
        }

        // 计算 nums 中有多少个子序列
        int ans = pow2[nums.length], s = x;
        // 枚举 x 的子集并去掉这些数
        do {
            var sp2 = pow2[dp[s]];
            ans = (ans + (Integer.bitCount(x ^ s) % 2 == 1 ? 1 : -1) * sp2) % MOD;
        } while ((s = (s - 1) & x) != x);
        return (ans + MOD) % MOD;
    }

    public static void main(String[] args) {
        assert new Solution().countEffective(new int[]{1,2,3}) == 3;
        assert new Solution().countEffective(new int[]{7,4,6}) == 4;
        assert new Solution().countEffective(new int[]{8,8}) == 1;
    }

}
