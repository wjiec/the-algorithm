package com.hasakk.binary;

public class SuperMultiply {

    // 大数乘法, 返回 (a * b) % mod 的值, 其中 a 或者 b 都是 10^18 范围
    //
    // proof:
    //  b = (x1 * 2^k) + (x2 * 2^(k-1)) + ... + (xn * 2^(0))  [x1, x2, ..., xn = 0|1]
    //  a * b = a * ((x1 * 2^k) + (x2 * 2^(k-1)) + ... + (xn * 2^(0)))
    //        = (a * x1 * 2^k) + (a * x2 * 2^(k-1)) + ... + (a * xn * 2^(0))
    //
    //  a * x1 * 2^k = a * x1 * 2^(k-1) * 2
    //               = x1 * ((a * 2^(k-1)) * 2)
    public static long mul(long a, long b, int mod) {
        long ans = 0; a %= mod; b %= mod;
        for (int i = 0; b >= (1L << i); i++) {
            if ((b & (1L << i)) != 0) {
                ans = (ans + a) % mod;
            }
            a = (a * 2) % mod;
        }
        return ans;
    }

}
