package weekly.bw161.D;

import java.util.Arrays;

/**
 * Q4. Number of Integers With Popcount-Depth Equal to K I
 *
 * https://leetcode.cn/contest/biweekly-contest-161/problems/number-of-integers-with-popcount-depth-equal-to-k-i/
 *
 * You are given two integers n and k.
 *
 * For any positive integer x, define the following sequence:
 *
 * p0 = x
 * pi+1 = popcount(pi) for all i >= 0, where popcount(y) is the
 * number of set bits (1's) in the binary representation of y.
 *
 * This sequence will eventually reach the value 1.
 *
 * The popcount-depth of x is defined as the smallest integer d >= 0 such that pd = 1.
 *
 * For example, if x = 7 (binary representation "111"). Then, the
 * sequence is: 7 → 3 → 2 → 1, so the popcount-depth of 7 is 3.
 *
 * Your task is to determine the number of integers in the range [1, n]
 * whose popcount-depth is exactly equal to k.
 *
 * Return the number of such integers.
 */

public class Solution {

    public long popcountDepth(long n, int k) {
        if (k == 0) return 1;
        int logN = 64 - Long.numberOfLeadingZeros(n);
        // f(x = 1) = 0, 但是我们计算的时候会得到 1, 所以需要去掉
        if (k == 1) return logN - 1;

        // 我们只需要 logN 个位数就可以满足所有的 n
        //  - 统计所有的 1 的位数的 位计数深度
        int[] depth = new int[logN + 1]; depth[1] = 1;
        for (int i = 2; i <= logN; i++) depth[i] = depth[Integer.bitCount(i)] + 1;

        // 获得 n 的所有数位
        int[] bits = new int[logN];
        for (int i = 0; i < logN; i++) bits[logN - i - 1] = (n & (1L << i)) != 0 ? 1 : 0;

        // 接下来就是枚举所有 depth[i] = k 的所有可选 1 的位数, 填入到 logN 个空位里, 检查有多少种填法
        long ans = 0;
        for (var row : memo) Arrays.fill(row, -1);
        for (int i = 1; i <= logN; i++) {
            if (depth[i] != k) continue;
            // 我们需要在 logN 个空位里填入 i 个 1, 且数值不能超过 n, 使用数位 dp 来解决
            ans += dp(bits, 0, i, true);
        }
        return ans;
    }

    private final long[][] memo = new long[64][64];

    private long dp(int[] bits, int i, int ones, boolean limited) {
        if (i == bits.length) return ones == 0 ? 1 : 0;
        if (!limited && memo[i][ones] != -1) return memo[i][ones];

        long ans = 0;
        int lower = 0, upper = Math.min(limited ? bits[i] : 1, ones);
        for (int j = lower; j <= upper; j++) {
            ans += dp(bits, i + 1, ones - j, limited && j == bits[i]);
        }
        return memo[i][ones] = ans;
    }

    public static void main(String[] args) {
        assert new Solution().popcountDepth(98881273812763L, 0) == 1;
        assert new Solution().popcountDepth(98881273812763L, 4) == 49644524800195L;
        assert new Solution().popcountDepth(4, 1) == 2;
        assert new Solution().popcountDepth(7, 2) == 3;
    }

}
