package com.hasakk.binary;

public class FastPower {

    // 快速幂, 返回 (a ^ b) % p 的值
    //
    // proof:
    //  a = (a0 * 2^k0) + (a1 * 2^k1) + ... + (an * 2^kn)           [a0, a1, ..., an ∈ 0,1]
    //  x ^ a = x ^ ((a0 * 2^k0) + (a1 * 2^k1) + ... + (an * 2^kn))
    //        = (x ^ (a0 * 2^k0)) * (x ^ (a1 * 2^k1)) + ... + (x ^ (an * 2^kn))         (1)
    //
    // a1 * 2^k1 = a1 * 2^(k1 - 1) * 2          [a0, a1 = 1]
    //           = 2 * (a0 * 2^k0)
    //
    // x ^ (a1 * 2^k1) = x ^ (a0 * 2^k0 * 2)
    //                 = (x ^ (a0 * 2^k0)) * (x ^ (a0 * 2^k0))                          (2)
    public static long pow(long a, long b, long mod) {
        long ans = 1;
        for (; b != 0; b >>= 1) {
            if ((b & 1) != 0) {
                ans = (ans * a) % mod; // (1)
            }
            a = (a * a) % mod; // (2)
        }
        return ans;
    }

    // 快速幂, 返回 (a ^ b) % p 的值, b 数组表示每一个数位
    //
    // proof:
    // x ^ (abc) = (x ^ (a * 100)) * (a ^ (b * 10)) + (a ^ c)
    //           = (x ^ (a * 10 * 01)) * (a ^ (b * 10)) + (a ^ (c * 1))
    //           = (x ^ (a * 10) * (a ^ b)) ^ 10 * (a ^ z)
    //
    // x ^ (abcd) = (x ^ (abc)) ^ 10 * (a ^ d)
    public static long pow(long a, int[] b, long mod) {
        long ans = 1;
        for (var x : b) {
            ans = (pow(ans, 10, mod) * pow(a, x, mod)) % mod;
        }
        return ans;
    }

}
