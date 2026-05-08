package weekly.bw177.D;

import static ability.Ability.Math.pow;

/**
 * Q4. Sum of K-Digit Numbers in a Range
 *
 * https://leetcode.cn/contest/biweekly-contest-177/problems/sum-of-k-digit-numbers-in-a-range/
 *
 * You are given three integers l, r, and k.
 *
 * Consider all possible integers consisting of exactly k digits, where each digit is chosen
 * independently from the integer range [l, r] (inclusive).
 *
 * If 0 is included in the range, leading zeros are allowed.
 *
 * Return an integer representing the sum of all such numbers.
 * Since the answer may be very large, return it modulo 1e9 + 7.
 */

public class Solution {

    // 1 <= k <= 109
    public int sumOfNumbers(int l, int r, int k) {
        final int MOD = 1_000_000_007;
        // 每一个数字都可以表示为 k_1*1ek + k_2*1e(k-1) + k_3*1e(k-2) + ... + k_n*1e0 的形式
        //  - 对于这个数字, k_1, k_2, k_3 ... k_n 的取值范围是 [l, r]
        //
        // k 位数字, 每个位有 [l, r] 一共 n = r - l + 1 种选择, 一共就是 n ^ k 个数字
        //
        // 枚举当前填的是第 i 位, 则有 n ^ (k - 1) 种可能, 贡献为 [l, r] * (n ^ (k - 1)) * 1e(k - i)
        //
        // 总和相加也就是 [l, r] * (n ^ (k - 1)) * 1ek + [l, r] * (n ^ (k - 1)) * 1e(k - 1)
        //            = [l, r] * (n ^ (k - 1)) * (1ek + 1e(k-1) + ... + 1e1)
        //
        // n ^ (k - 1) 可以使用快速幂求得
        // 1ek + 1e(k-1) + ... + 1e1 也就是 11111...111 一共 k 个 1 组成, 要求这个数的模数
        //  - 111...111 等于 (1e(k+1) - 1) / 9
        //  - 除以 9 可以转换为 * 9_inv(逆元)
        //
        // 根据费马小定理, 9 在模 Mod 的逆元为 9 ^ (Mod - 2) % Mod
        long p10 = pow(10, k, MOD);
        long inv9 = pow(9, MOD - 2, MOD);

        long n = r - l + 1, nk = pow(n, k - 1, MOD);

        long ans = n * (l + r) / 2;
        ans = (ans * nk) % MOD;
        ans = (ans * ((p10 - 1 + MOD) % MOD)) % MOD;
        ans = (ans * inv9) % MOD;
        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().sumOfNumbers(1, 2, 2) == 66;
        assert new Solution().sumOfNumbers(0, 1, 3) == 444;
        assert new Solution().sumOfNumbers(5, 5, 10) == 555555520;
    }

}
