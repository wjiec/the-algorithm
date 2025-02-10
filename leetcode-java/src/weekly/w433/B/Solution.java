package weekly.w433.B;

import java.util.Arrays;

import static ability.Ability.Math.pow;

/**
 * 3428. Maximum and Minimum Sums of at Most Size K Subsequences
 *
 * https://leetcode.cn/contest/weekly-contest-433/problems/maximum-and-minimum-sums-of-at-most-size-k-subsequences/
 *
 * You are given an integer array nums and a positive integer k. Return the sum of the
 * maximum and minimum elements of all subsequences of nums with at most k elements.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 */

/** @noinspection DuplicatedCode*/
public class Solution {

    private static final int MAX_N = 100_001;
    private static final int MOD = 1_000_000_007;
    private static final long[] fac = new long[MAX_N];
    static {
        fac[0] = fac[1] = 1;
        for (int i = 2; i < MAX_N; i++) fac[i] = (fac[i - 1] * i) % MOD;
    }

    private static final long[] inv = new long[MAX_N];
    static {
        // 在 MOD 下的乘法逆元 inv_a = 1 / a = pow(a, MOD - 2, MOD)
        //  - 对于阶乘 a * b * c 的逆元是 1 / (a * b * c) = pow(a * b * c, MOD - 2, MOD)
        //      - 递推 a * b 的逆元是 1 / (a * b) = 1 / (a * b * c) * c
        inv[MAX_N - 1] = pow(fac[MAX_N - 1], MOD - 2, MOD);
        for (int i = MAX_N - 1; i > 0; i--) inv[i - 1] = (inv[i] * i) % MOD;
    }

    private int comb(int n, int c) {
        if (n < c) return 0;
        return (int) ((((fac[n] * inv[c]) % MOD) * inv[n - c]) % MOD);
    }

    public int minMaxSums(int[] nums, int k) {
        // 因为是取的子序列里的最小值和最大值, 所以排序之后我们按照所选的数字在
        // 原数组中取对应的数字即可. 使用贡献法求解
        Arrays.sort(nums);

        long ans = 0; int n = nums.length;
        for (var v : nums) ans = (ans + 2L * v) % MOD;

        for (int i = 2; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                // 当前数作为最小值, 就需要从后面的 n - i + 1 个任意选择 k 个
                ans = (ans + (long) nums[j] * comb(n - j - 1, i - 1)) % MOD;
                // 当前数作为最大值, 就需要从前面的 i 个任意选择 k 个作为另外的数字
                ans = (ans + (long) nums[j] * comb(j, i - 1)) % MOD;
            }
        }

        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().minMaxSums(new int[]{1,2,3}, 2) == 24;
        assert new Solution().minMaxSums(new int[]{5,0,6}, 1) == 22;
        assert new Solution().minMaxSums(new int[]{1,1,1}, 2) == 12;
    }

}
