package weekly.w372.C;

/**
 * 2939. Maximum Xor Product
 *
 * https://leetcode.cn/contest/weekly-contest-372/problems/maximum-xor-product/
 *
 * Given three integers a, b, and n, return the maximum value of (a XOR x) * (b XOR x) where 0 <= x < 2n.
 *
 * Since the answer may be too large, return it modulo 109 + 7.
 *
 * Note that XOR is the bitwise XOR operation.
 */

public class Solution {

    public int maximumXorProduct(long a, long b, int n) {
        if (a < b) return maximumXorProduct(b, a, n);

        long mask = (1L << n) - 1;
        long ax = a & ~mask, bx = b & ~mask;
        a &= mask; b &= mask;

        long left = a ^ b, one = mask ^ left;
        ax |= one; bx |= one;

        if (left > 0 && ax == bx) {
            long hi = 1L << (63 - Long.numberOfLeadingZeros(left));
            ax |= hi; left ^= hi;
        }
        bx |= left;

        final long MOD = 1_000_000_007;
        return (int) (ax % MOD * (bx % MOD) % MOD);
    }

    public static void main(String[] args) {
        assert new Solution().maximumXorProduct(53449611838892L,712958946092406L, 6) == 231850918;
    }

}
