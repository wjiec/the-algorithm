package problem.p1201uglynumberiii;

/**
 * 1201. Ugly Number III
 *
 * https://leetcode.cn/problems/ugly-number-iii/
 *
 * An ugly number is a positive integer that is divisible by a, b, or c.
 *
 * Given four integers n, a, b, and c, return the nth ugly number.
 */

public class Solution {

    public int nthUglyNumber(int n, int a, int b, int c) {
        long x = (long) a / gcd(a, b) * b;
        long y = (long) a / gcd(a, c) * c;
        long z = (long) c / gcd(b, c) * b;
        long w = x / gcd(x, c) * c;

        long l = 0, r = (long) 1e15;
        while (l < r) {
            long m = l + (r - l) / 2;
            if (m / a + m / b + m / c - m / x - m / y - m / z + m / w < n) {
                l = m + 1;
            } else r = m;
        }
        return (int) l;
    }

    private long gcd(long a, long b) {
        return a % b == 0 ? b : gcd(b, a % b);
    }

    public static void main(String[] args) {
        assert new Solution().nthUglyNumber(3, 2, 3, 5) == 4;
        assert new Solution().nthUglyNumber(4, 2, 3, 4) == 6;
        assert new Solution().nthUglyNumber(5, 2, 11, 13) == 10;
        assert new Solution().nthUglyNumber(1000000000, 2, 217983653, 336916467) == 1999999984;
    }

}
