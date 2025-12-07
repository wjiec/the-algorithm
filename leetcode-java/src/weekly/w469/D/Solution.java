package weekly.w469.D;

import common.Tag;

/**
 * Q4. Number of ZigZag Arrays II
 *
 * https://leetcode.cn/contest/weekly-contest-469/problems/number-of-zigzag-arrays-ii/
 *
 * You are given three integers n, l, and r.
 *
 * A ZigZag array of length n is defined as follows:
 *
 * Each element lies in the range [l, r].
 * No two adjacent elements are equal.
 * No three consecutive elements form a strictly increasing or strictly decreasing sequence.
 * Return the total number of valid ZigZag arrays.
 *
 * Since the answer may be large, return it modulo 109 + 7.
 *
 * A sequence is said to be strictly increasing if each element is strictly greater than its previous one (if exists).
 *
 * A sequence is said to be strictly decreasing if each element is strictly smaller than its previous one (if exists).
 */

public class Solution {

    private static final int MOD = 1_000_000_007;

    @Tag("矩阵快速幂")
    public int zigZagArrays(int n, int l, int r) {
        int k = r - l + 1;
        long[][] mat = new long[2 * k][2 * k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < i; j++) mat[i][k + j] = 1;
            for (int j = i + 1; j < k; j++) mat[k + i][j] = 1;
        }

        long[][] f1 = new long[2 * k][1];
        for (int i = 0; i < f1.length; i++) f1[i][0] = 1;

        long[][] fn = powMul(mat, n - 1, f1); long ans = 0;
        for (var v : fn) ans += v[0];
        System.out.println(ans);
        return (int) (ans % MOD);
    }

    // a ^ k * b
    private long[][] powMul(long[][] a, int k, long[][] b) {
        long[][] ans = b;
        for (; k > 0; k >>= 1) {
            if ((k & 1) != 0) {
                ans = mul(a, ans);
            }
            a = mul(a, a);
        }
        return ans;
    }

    private long[][] mul(long[][] a, long[][] b) {
        long[][] ans = new long[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int k = 0; k < a[i].length; k++) {
                if (a[i][k] == 0) continue;

                for (int j = 0; j < b[k].length; j++) {
                    ans[i][j] = (ans[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().zigZagArrays(3, 4, 5) == 2;
        assert new Solution().zigZagArrays(3, 1, 3) == 10;
    }

}
